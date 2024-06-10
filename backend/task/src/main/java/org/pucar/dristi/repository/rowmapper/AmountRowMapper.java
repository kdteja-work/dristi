package org.pucar.dristi.repository.rowmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.egov.common.contract.models.AuditDetails;
import org.egov.tracer.model.CustomException;
import org.postgresql.util.PGobject;
import org.pucar.dristi.web.models.Amount;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.*;

@Component
@Slf4j
public class AmountRowMapper implements ResultSetExtractor<Map<UUID, Amount>> {
    public Map<UUID, Amount> extractData(ResultSet rs) {
        Map<UUID, Amount> statuteSectionMap = new LinkedHashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            while (rs.next()) {
                String id = rs.getString("case_id");
                UUID uuid = UUID.fromString(id!=null ? id : "00000000-0000-0000-0000-000000000000");

                Long lastModifiedTime = rs.getLong("lastmodifiedtime");

                AuditDetails auditdetails = AuditDetails.builder()
                        .createdBy(rs.getString("createdby"))
                        .createdTime(rs.getLong("createdtime"))
                        .lastModifiedBy(rs.getString("lastmodifiedby"))
                        .lastModifiedTime(lastModifiedTime)
                        .build();
                Amount amount = Amount.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .build();

                PGobject pgObject = (PGobject) rs.getObject("additionalDetails");
                if (pgObject != null)
                    amount.setAdditionalDetails(objectMapper.readTree(pgObject.getValue()));

                statuteSectionMap.get(uuid);
            }
        } catch(CustomException e){
            throw e;
        } catch (Exception e) {
            log.error("Error occurred while processing Case ResultSet: {}", e.getMessage());
            throw new CustomException("ROW_MAPPER_EXCEPTION", "Error occurred while processing Case ResultSet: " + e.getMessage());
        }
        return statuteSectionMap;
    }

    public List<String> stringToList(String str){
        List<String> list = new ArrayList<>();
        if(str!=null){
            StringTokenizer st = new StringTokenizer("str",",");
            while (st.hasMoreTokens()) {
                list.add(st.nextToken());
            }
        }

        return list;
    }

}