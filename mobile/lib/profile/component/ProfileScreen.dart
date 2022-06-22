import 'package:flutter/material.dart';
import 'package:mobile/constant/util.dart';
import 'package:mobile/history/component/HistoryScreen.dart';
import 'package:mobile/list_hospital/component/ListHopitalScreen.dart';
import 'package:mobile/login/service/LoginSerivce.dart';
import 'package:mobile/post_find_donate/component/PostFindDonateScreen.dart';
import 'package:mobile/profile/service/ProfileService.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _ProfileScreen();
  }
}

class _ProfileScreen extends State<ProfileScreen> {
  String username = "";
  String phone = "";
  int birthday = 0;
  String blood = "";
  String idCard = "";
  String email = "";
  String birthDatConverted = "";
  _ProfileScreen() {}

  @override
  void initState() {
    super.initState();
    ProfileService.getMyProfile()
        .then((res) => {
              Util.donatorId = res.data["id"],
              print(res),
              setState(() => {
                    username = res.data["name"],
                    phone = res.data["phone"],
                    birthday = res.data["birthday"],
                    blood = res.data["blood"],
                    idCard = res.data["idCard"],
                    email = res.data["email"],
                    birthDatConverted = convertTimeStampToDate(birthday)
                  }),
            })
        .catchError((error) => {
              print(error),
            });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Container(
          padding: const EdgeInsets.fromLTRB(50, 0, 0, 0),
          child: const Text(
            "Thông tin cá nhân",
            style: TextStyle(color: Colors.white),
          ),
        ),
        elevation: 0,
        backgroundColor: Colors.pink.shade400,
        leading: IconButton(
            onPressed: () {
              Navigator.pop(context);
            },
            icon: const Icon(
              Icons.arrow_back_ios,
              size: 20,
              color: Colors.white,
            )),
      ),
      body: Container(
        child: Column(
          children: [
            Center(
              child: Text(
                username,
                style: TextStyle(color: Colors.pink.shade400, fontSize: 50),
              ),
            ),
            Container(
              padding: const EdgeInsets.fromLTRB(10, 10, 10, 0),
              child: Column(
                children: [
                  makeInput(label: "Số điện thoại", textController: phone),
                  makeInput(
                      label: "Ngày sinh",
                      textController: convertTimeStampToDate(birthday)),
                  makeInput(label: "Nhóm máu", textController: blood),
                  makeInput(
                      label: "Số căn cước công dân", textController: idCard),
                  makeInput(label: "Email", textController: email),
                ],
              ),
            ),
            Container(
              padding: const EdgeInsets.fromLTRB(10, 0, 10, 10),
              child: Row(
                children: [
                  ElevatedButton(
                    style:
                        ElevatedButton.styleFrom(primary: Colors.pink.shade400),
                    child: const Text(
                      'Đăng xuất',
                      style: TextStyle(fontSize: 11),
                    ),
                    onPressed: (() => {
                          LoginService.logout(Util.token)
                              .then((res) => {
                                    Util.token = "",
                                    Navigator.pop(context),
                                  })
                              .catchError((error) => {
                                    print("have error"),
                                  }),
                        }),
                  ),
                  const Spacer(),
                  ElevatedButton(
                    style:
                        ElevatedButton.styleFrom(primary: Colors.pink.shade400),
                    child: const Text(
                      'Danh sách bệnh viện',
                      style: TextStyle(fontSize: 11),
                    ),
                    onPressed: (() => {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) =>
                                      const ListHospitalScreen())),
                        }),
                  ),
                  const Spacer(),
                  ElevatedButton(
                    style:
                        ElevatedButton.styleFrom(primary: Colors.pink.shade400),
                    child: const Text(
                      'Lịch sử hiến máu',
                      style: TextStyle(fontSize: 11),
                    ),
                    onPressed: (() => {
                          Navigator.push(
                              context,
                              MaterialPageRoute(
                                  builder: (context) => const HistoryScreen())),
                        }),
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }

  String convertTimeStampToDate(int timeStamp) {
    DateTime date = DateTime.fromMillisecondsSinceEpoch(timeStamp * 1000);
    String dateFormat = "";
    dateFormat = dateFormat +
        date.year.toString() +
        "-" +
        date.month.toString() +
        "-" +
        date.day.toString();
    return dateFormat;
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
        controller: TextEditingController(text: textController),
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
