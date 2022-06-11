import 'package:flutter/material.dart';
import 'package:mobile/sign_up/service/SignUpService.dart';

class SignUpScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _SignUpScreen();
  }
}

class _SignUpScreen extends State<SignUpScreen> {
  TextEditingController usernameController = TextEditingController();
  TextEditingController fullnameController = TextEditingController();
  TextEditingController idCardController = TextEditingController();
  TextEditingController phoneController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController confirmPasswordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      resizeToAvoidBottomInset: true,
      backgroundColor: Colors.white,
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.white,
        leading: IconButton(
            onPressed: () {
              Navigator.pop(context);
            },
            icon: const Icon(
              Icons.arrow_back_ios,
              size: 20,
              color: Colors.black,
            )),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          child: SizedBox(
            height: MediaQuery.of(context).size.height,
            width: double.infinity,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  children: [
                    Column(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        const Text(
                          "Sign up",
                          style: TextStyle(
                            fontSize: 30,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const SizedBox(
                          height: 20,
                        ),
                        Text(
                          "Create an Account,Its free",
                          style: TextStyle(
                            fontSize: 15,
                            color: Colors.grey[700],
                          ),
                        ),
                        const SizedBox(
                          height: 30,
                        )
                      ],
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 40),
                      child: Column(
                        children: [
                          makeInput(
                              label: "Account",
                              textController: usernameController),
                          makeInput(
                              label: "Full Name",
                              textController: fullnameController),
                          makeInput(
                              label: "Id card",
                              textController: idCardController),
                          makeInput(
                              label: "Phone", textController: phoneController),
                          makeInput(
                              label: "Password",
                              textController: passwordController,
                              obsureText: true),
                          makeInput(
                              label: "Confirm password",
                              textController: confirmPasswordController,
                              obsureText: true)
                        ],
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(horizontal: 30),
                      child: Container(
                        padding: const EdgeInsets.only(top: 1, left: 3),
                        child: MaterialButton(
                          minWidth: double.infinity,
                          height: 30,
                          onPressed: () {},
                          color: Colors.redAccent,
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(40)),
                          child: TextButton(
                            onPressed: () {
                              register(context);
                            },
                            child: const Text(
                              'Sign up',
                            ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  register(BuildContext buildContext) {
    if (passwordController.text != confirmPasswordController.text) {
      showModal(context, "Wrong confirm password",
          "The confirm password is incorrect");
      return;
    }
    var requestBody = {
      "username": usernameController.text,
      "fullname": fullnameController.text,
      "idCard": idCardController.text,
      "phone": phoneController.text,
      "password": passwordController.text
    };
    SignUpService.signup(requestBody)
        .then((res) => {
              showModal(context, "REGISTER SUCCESS",
                  "Congratulations on your successful registration in the system"),
            })
        .catchError((error) => {
              showModal(context, "REGISTER FAIL", "REGISTER FAIL"),
            });
  }

  Future<dynamic> showModal(
      BuildContext context, String title, String content) {
    return showDialog(
        context: context,
        builder: (BuildContext buiderContext) => AlertDialog(
              title: Text(title),
              content: Text(content),
              actions: <Widget>[
                IconButton(
                    icon: const Icon(Icons.close),
                    onPressed: () {
                      Navigator.pop(context);
                    })
              ],
            ));
  }
}

Widget makeInput({label, textController, obsureText = false}) {
  return Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      Text(
        label,
        style: const TextStyle(
            fontSize: 15, fontWeight: FontWeight.w400, color: Colors.black87),
      ),
      // const SizedBox(
      //   height: 3,
      // ),
      TextField(
        controller: textController,
        obscureText: obsureText,
        decoration: const InputDecoration(
          contentPadding: EdgeInsets.symmetric(vertical: 0, horizontal: 10),
          enabledBorder: OutlineInputBorder(
            borderSide: BorderSide(
              color: Colors.grey,
            ),
          ),
          border:
              OutlineInputBorder(borderSide: BorderSide(color: Colors.grey)),
        ),
      ),
      const SizedBox(
        height: 30,
      )
    ],
  );
}