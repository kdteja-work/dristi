package org.pucar.dristi.web.controllers;

import org.egov.common.contract.response.ResponseInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.pucar.dristi.service.CaseService;
import org.pucar.dristi.service.WitnessService;
import org.pucar.dristi.util.ResponseInfoFactory;
import org.pucar.dristi.web.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
* API tests for CaseApiController
*/
@SpringBootTest
public class CaseApiControllerTest {

    @Autowired
    private CaseApiController caseApiController;
    @MockBean
    private CaseService caseService;
    @MockBean
    private WitnessService witnessService;
    @Mock
    private ResponseInfoFactory responseInfoFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void caseV1CreatePostSuccess() {
        // Mocking request body
        CaseRequest caseRequest = new CaseRequest(); // Create a mock CaseRequest object

        // Mocking caseService.createCase method to return a CourtCase object
        CourtCase courtCase = new CourtCase(); // Create a mock CourtCase object
        when(caseService.createCase(caseRequest)).thenReturn(courtCase);

        // Mocking responseInfoFactory.createResponseInfoFromRequestInfo method to return a ResponseInfo object
        ResponseInfo responseInfo = new ResponseInfo(); // Create a mock ResponseInfo object
        when(responseInfoFactory.createResponseInfoFromRequestInfo(any(), any())).thenReturn(responseInfo);

        // Call the method under test
        ResponseEntity<CaseResponse> responseEntity = caseApiController.caseV1CreatePost(caseRequest);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(courtCase, responseEntity.getBody().getCases().get(0));
    }

    @Test
    public void caseV1ExistsPostSuccess() {
        // Mocking request body
        CaseExistsRequest caseRequest = new CaseExistsRequest(); // Create a mock CaseRequest object

        // Mocking caseService.createCase method to return a CourtCase object
        CaseExists caseExists = new CaseExists(); // Create a mock CourtCase object
        when(caseService.existCases(caseRequest)).thenReturn(List.of(caseExists));

        // Mocking responseInfoFactory.createResponseInfoFromRequestInfo method to return a ResponseInfo object
        ResponseInfo responseInfo = new ResponseInfo(); // Create a mock ResponseInfo object
        when(responseInfoFactory.createResponseInfoFromRequestInfo(any(), any())).thenReturn(responseInfo);

        // Call the method under test
        ResponseEntity<CaseExistsResponse> responseEntity = caseApiController.caseV1ExistsPost(caseRequest);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(caseExists, responseEntity.getBody().getCriteria().get(0));
    }

    @Test
    public void caseV1SearchPostSuccess() {
        // Mocking request body
        CaseSearchRequest caseRequest = new CaseSearchRequest(); // Create a mock CaseRequest object
        caseRequest.setCriteria(List.of(CaseCriteria.builder().cnrNumber("cnrNumber").build()));
        // Mocking caseService.createCase method to return a CourtCase object
        CourtCase courtCase = new CourtCase(); // Create a mock CourtCase object
        caseService.searchCases(caseRequest);

        // Mocking responseInfoFactory.createResponseInfoFromRequestInfo method to return a ResponseInfo object
        ResponseInfo responseInfo = new ResponseInfo(); // Create a mock ResponseInfo object
        when(responseInfoFactory.createResponseInfoFromRequestInfo(any(), any())).thenReturn(responseInfo);

        // Call the method under test
        ResponseEntity<CaseListResponse> responseEntity = caseApiController.caseV1SearchPost(caseRequest);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(caseRequest.getCriteria(), responseEntity.getBody().getCriteria());
    }
    @Test
    public void caseV1UpdatePostSuccess() {
        // Mocking request body
        CaseRequest caseRequest = new CaseRequest(); // Create a mock CaseRequest object

        // Mocking caseService.createCase method to return a CourtCase object
        CourtCase courtCase = new CourtCase(); // Create a mock CourtCase object
        when(caseService.updateCase(caseRequest)).thenReturn(courtCase);

        // Mocking responseInfoFactory.createResponseInfoFromRequestInfo method to return a ResponseInfo object
        ResponseInfo responseInfo = new ResponseInfo(); // Create a mock ResponseInfo object
        when(responseInfoFactory.createResponseInfoFromRequestInfo(any(), any())).thenReturn(responseInfo);

        // Call the method under test
        ResponseEntity<CaseResponse> responseEntity = caseApiController.caseV1UpdatePost(caseRequest);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(courtCase, responseEntity.getBody().getCases().get(0));
    }

    @Test
    public void caseWitnessV1CreatePostSuccess() {
        // Mocking request body
        WitnessRequest witnessRequest = new WitnessRequest(); // Create a mock CaseRequest object

        // Mocking caseService.createCase method to return a CourtCase object
        Witness witness = new Witness(); // Create a mock CourtCase object
        when(witnessService.registerWitnessRequest(witnessRequest)).thenReturn(witness);

        // Mocking responseInfoFactory.createResponseInfoFromRequestInfo method to return a ResponseInfo object
        ResponseInfo responseInfo = new ResponseInfo(); // Create a mock ResponseInfo object
        when(responseInfoFactory.createResponseInfoFromRequestInfo(any(), any())).thenReturn(responseInfo);

        // Call the method under test
        ResponseEntity<WitnessResponse> responseEntity = caseApiController.caseWitnessV1CreatePost(witnessRequest);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(witness, responseEntity.getBody().getWitnesses().get(0));
    }

    @Test
    public void caseWitnessV1SearchPostSuccess() {
        // Mocking request body
        WitnessSearchRequest witnessRequest = new WitnessSearchRequest(); // Create a mock CaseRequest object

        // Mocking caseService.createCase method to return a CourtCase object
        Witness witness = new Witness(); // Create a mock CourtCase object
        when(witnessService.searchWitnesses(witnessRequest)).thenReturn(List.of(witness));

        // Mocking responseInfoFactory.createResponseInfoFromRequestInfo method to return a ResponseInfo object
        ResponseInfo responseInfo = new ResponseInfo(); // Create a mock ResponseInfo object
        when(responseInfoFactory.createResponseInfoFromRequestInfo(any(), any())).thenReturn(responseInfo);

        // Call the method under test
        ResponseEntity<WitnessResponse> responseEntity = caseApiController.caseWitnessV1SearchPost(witnessRequest);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(witness, responseEntity.getBody().getWitnesses().get(0));
    }

    @Test
    public void caseWitnessV1UpdatePostSuccess() {
        // Mocking request body
        WitnessRequest witnessRequest = new WitnessRequest(); // Create a mock CaseRequest object

        // Mocking caseService.createCase method to return a CourtCase object
        Witness witness = new Witness(); // Create a mock CourtCase object
        when(witnessService.updateWitness(witnessRequest)).thenReturn(witness);

        // Mocking responseInfoFactory.createResponseInfoFromRequestInfo method to return a ResponseInfo object
        ResponseInfo responseInfo = new ResponseInfo(); // Create a mock ResponseInfo object
        when(responseInfoFactory.createResponseInfoFromRequestInfo(any(), any())).thenReturn(responseInfo);

        // Call the method under test
        ResponseEntity<WitnessResponse> responseEntity = caseApiController.caseWitnessV1UpdatePost(witnessRequest);

        // Verify the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(witness, responseEntity.getBody().getWitnesses().get(0));
    }

}
