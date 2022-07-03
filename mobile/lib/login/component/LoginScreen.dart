// ignore_for_file: unnecessary_new

import 'package:flutter/material.dart';
import 'package:mobile/constant/role.dart';
import 'package:mobile/constant/util.dart';
import 'package:mobile/jwt/TokenService.dart';
import 'package:mobile/login/service/LoginSerivce.dart';
import 'package:mobile/profile/component/ProfileScreen.dart';
import 'package:mobile/sign_up/component/SignUpScreen.dart';

import '../../post_find_donate/component/PostFindDonateScreen.dart';

class LoginScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _LoginScreen();
  }
}

class _LoginScreen extends State<LoginScreen> {
  TextEditingController nameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  String token = "";
  String roleOfUser = "";

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Padding(
        padding: const EdgeInsets.all(10),
        child: ListView(
          children: <Widget>[
            Container(
                alignment: Alignment.center,
                padding: const EdgeInsets.all(10),
                child: Text(
                  'Đăng nhập',
                  style: TextStyle(
                      color: Colors.pink.shade400,
                      fontWeight: FontWeight.w500,
                      fontSize: 30),
                )),
            Container(
              padding: const EdgeInsets.all(10),
              child: TextField(
                controller: nameController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Tài khoản',
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.fromLTRB(10, 10, 10, 10),
              child: TextField(
                obscureText: true,
                controller: passwordController,
                decoration: const InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Mật khẩu',
                ),
              ),
            ),
            Container(
                height: 50,
                padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
                child: ElevatedButton(
                  style:
                      ElevatedButton.styleFrom(primary: Colors.pink.shade400),
                  child: const Text('Đăng nhập'),
                  onPressed: (() => {
                        // print(LoginService.BASE_AUTH_URL),
                        LoginService.login(
                                nameController.text, passwordController.text)
                            .then((res) => {
                                  Util.token = res.data,
                                  token = res.data,
                                  roleOfUser =
                                      TokenSerivce.decode(token)["authorities"]
                                          [0],
                                  if (roleOfUser ==
                                      Role.ROLE_DONATOR
                                          .toString()
                                          .split(".")
                                          .last)
                                    {
                                      if (Util.postIdNotification != "")
                                        {
                                          Navigator.push(
                                              context,
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      ProfileScreen())),
                                          Navigator.push(
                                              context,
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      (PostFindDonateScreen(
                                                          id: int.parse(Util
                                                              .postIdNotification))))),
                                        }
                                      else
                                        {
                                          Navigator.push(
                                              context,
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      const ProfileScreen())),
                                        }
                                    }
                                  else
                                    {
                                      showModal(context, 'Không có quyền',
                                          'Bạn không có quyền đăng nhập'),
                                      // print("you not have role to login here"),
                                    }
                                })
                            .catchError((onError) => {
                                  showModal(context, 'Đăng nhập thất bại',
                                      'Tài khoản của bạn bị sai mật khẩu hoặc đã bị khóa'),
                                  // print("username or password incorrect"),
                                }),
                      }),
                )),
            Row(
              children: <Widget>[
                const Text('Bạn đã có tài khoản chưa ?'),
                TextButton(
                  child: Text(
                    'Đăng kí',
                    style: TextStyle(
                      fontSize: 20,
                      color: Colors.pink.shade400,
                    ),
                  ),
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => SignUpScreen()));
                  },
                )
              ],
              mainAxisAlignment: MainAxisAlignment.center,
            ),
          ],
        ));
  }

  Future<dynamic> showModal(
      BuildContext context, String title, String content) {
    return showDialog(
        context: context,
        builder: (BuildContext buiderContext) => new AlertDialog(
              title: new Text(title),
              content: new Text(content),
              actions: <Widget>[
                new IconButton(
                    icon: const Icon(Icons.close),
                    onPressed: () {
                      Navigator.pop(context);
                    })
              ],
            ));
  }
}
