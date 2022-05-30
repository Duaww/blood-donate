import 'package:flutter/material.dart';
import 'package:mobile/constant/util.dart';
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
                  Util.token = "",
                  Navigator.pop(context),
                }),
          )),
          Container(
              child: ElevatedButton(
            child: const Text('Post Detail'),
            onPressed: (() => {
                  Navigator.push(
                      context,
                      MaterialPageRoute(
                          builder: (context) => PostFindDonateScreen())),
                }),
          ))
        ],
      ),
    );
  }
}
