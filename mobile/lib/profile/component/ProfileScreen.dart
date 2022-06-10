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
  _ProfileScreen() {}

  @override
  void initState() {
    super.initState();
    ProfileService.getMyProfile()
        .then((res) => {
              Util.donatorId = res.data["id"],
              setState(() => {
                    username = res.data["name"],
                    phone = res.data["phone"],
                  }),
            })
        .catchError((error) => {
              print(error),
            });
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      child: Column(
        children: [
          Text(username),
          Text(phone),
          Container(
              child: ElevatedButton(
            child: const Text('Logout'),
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
          )),
          Container(
              child: ElevatedButton(
            child: const Text('List Hospital'),
            onPressed: (() => {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => ListHospitalScreen())),
                }),
          )),
          Container(
              child: ElevatedButton(
            child: const Text('History'),
            onPressed: (() => {
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => HistoryScreen())),
                }),
          ))
        ],
      ),
    );
  }
}
