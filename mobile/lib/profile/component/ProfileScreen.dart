import 'package:flutter/material.dart';
import 'package:mobile/profile/service/ProfileService.dart';

class ProfileScreen extends StatefulWidget {
  final String token;

  const ProfileScreen({Key? key, required this.token}) : super(key: key);

  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return _ProfileScreen(this.token);
  }
}

class _ProfileScreen extends State<ProfileScreen> {
  String token = "";
  String username = "";
  String phone = "";
  _ProfileScreen(String token) {
    this.token = token;
  }

  @override
  void initState() {
    super.initState();
    ProfileService.getMyProfile(token)
        .then((res) => {
              print(res.data),
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
            child: const Text('Back'),
            onPressed: (() => {
                  Navigator.pop(context),
                }),
          ))
        ],
      ),
    );
  }
}
