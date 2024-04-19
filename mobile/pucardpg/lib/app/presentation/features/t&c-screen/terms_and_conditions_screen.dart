
import 'package:digit_components/digit_components.dart';
import 'package:digit_components/widgets/atoms/digit_toaster.dart';
import 'package:digit_components/widgets/digit_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:pucardpg/app/domain/entities/litigant_model.dart';
import 'package:pucardpg/app/presentation/widgets/back_button.dart';
import 'package:pucardpg/app/presentation/widgets/help_button.dart';
import 'package:pucardpg/config/mixin/app_mixin.dart';

class TermsAndConditionsScreen extends StatefulWidget with AppMixin{

  UserModel userModel = UserModel();

  TermsAndConditionsScreen({super.key, required this.userModel});

  @override
  TermsAndConditionsScreenState createState() => TermsAndConditionsScreenState();

}

class TermsAndConditionsScreenState extends State<TermsAndConditionsScreen> {

  bool firstChecked = false;

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text(""),
          centerTitle: true,
          actions: [
            IconButton(onPressed: () {}, icon: const Icon(Icons.notifications))
          ],
          leading: IconButton(
            onPressed: () {},
            icon: const Icon(Icons.menu),
          ),
        ),
        body: Column(
          children: [
            Expanded(
              child: SingleChildScrollView(
                child: Column(
                  children: [
                    const SizedBox(height: 10,),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        DigitBackButton(
                          onPressed: (){
                            Navigator.of(context).pop();
                          },
                        ),
                        DigitHelpButton()
                      ],
                    ),
                    DigitCard(
                      padding: const EdgeInsets.all(20),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text("Registration", style: widget.theme.text20W400Rob()?.apply(fontStyle: FontStyle.italic),),
                          const SizedBox(height: 20,),
                          Text("Terms and Conditions", style: widget.theme.text32W700RobCon()?.apply(),),
                          const SizedBox(height: 20,),
                          DigitCheckboxTile(
                            value: firstChecked,
                            label: "I agree to Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                            onChanged: (val){
                              setState(() {
                                firstChecked = !firstChecked;
                              });
                            },
                          ),
                          // const SizedBox(height: 20,),
                        ],
                      ),
                    ),
                    // Expanded(child: Container(),),
                  ],
                ),
              ),
            ),
            DigitElevatedButton(
                onPressed: () {
                  if (firstChecked == false) {
                    DigitToast.show(context,
                      options: DigitToastOptions(
                        "Select all Terms and Conditions",
                        true,
                        widget.theme.theme(),
                      ),
                    );
                    return;
                  }
                  Navigator.pushNamed(context, '/SuccessScreen');
                },
                child: Text('Submit',  style: widget.theme.text20W700()?.apply(color: Colors.white, ),)
            ),
          ],
        )
    );

  }

}