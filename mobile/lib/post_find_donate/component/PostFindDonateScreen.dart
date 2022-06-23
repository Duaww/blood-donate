import 'package:flutter/material.dart';
import 'package:mobile/constant/util.dart';

import 'package:mobile/post_find_donate/service/PostFindDonateService.dart';

class PostFindDonateScreen extends StatefulWidget {
  final int id;
  const PostFindDonateScreen({Key? key, required this.id}) : super(key: key);
  @override
  State<StatefulWidget> createState() {
    return _PostFindDonateScreen(id);
  }
}

class _PostFindDonateScreen extends State<PostFindDonateScreen> {
  final int idPost;
  _PostFindDonateScreen(this.idPost);

  String content = "";
  int deadlineRegister = 0;
  int createAt = 0;
  int updateAt = 0;
  HospitalInfo hospital = HospitalInfo("", "");

  @override
  void initState() {
    super.initState();
    PostFindDonateService.getDetailPost(idPost.toString())
        .then((res) => {
              setState(() => {
                    content = res.data["content"],
                    deadlineRegister = res.data["deadlineRegister"],
                    createAt = res.data["createAt"],
                    updateAt = res.data["updateAt"],
                    hospital = HospitalInfo(res.data["hospitalInfo"]["name"],
                        res.data["hospitalInfo"]["address"])
                  }),
            })
        .catchError((error) => {
              print(error),
            });
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
              "Chi tiết bài đăng",
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
          height: 430,
          padding: const EdgeInsets.fromLTRB(10, 10, 10, 10),
          child: Container(
            padding: const EdgeInsets.fromLTRB(10, 10, 10, 10),
            decoration: BoxDecoration(
              border: Border.all(color: Colors.black),
              borderRadius: BorderRadius.circular(5),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const Text(
                  "Nội dung bài đăng",
                  style: TextStyle(fontSize: 15, color: Colors.redAccent),
                ),
                const SizedBox(
                  height: 10,
                ),
                Container(
                  decoration: BoxDecoration(
                    border: Border.all(color: Colors.pink.shade400),
                    borderRadius: BorderRadius.circular(5),
                  ),
                  child: Padding(
                      padding: const EdgeInsets.all(4.0),
                      child: Column(
                        children: [
                          TextField(
                            enabled: false,
                            maxLines: 6, //or null
                            decoration: const InputDecoration.collapsed(
                                hintText: "Enter your text here"),
                            controller: TextEditingController(text: content),
                          ),
                        ],
                      )),
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Container(
                      margin: const EdgeInsets.only(right: 5.0),
                      child: Icon(
                        Icons.timer,
                        size: 20,
                        color: Colors.pink.shade400,
                      ),
                    ),
                    Text(
                      "Đăng tải vào ngày : " + convertTimeStampToDate(createAt),
                      style: const TextStyle(fontSize: 15, color: Colors.grey),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Container(
                      margin: const EdgeInsets.only(right: 5.0),
                      child: Icon(
                        Icons.timer,
                        size: 20,
                        color: Colors.pink.shade400,
                      ),
                    ),
                    Text(
                      "Cập nhật vào ngày : " + convertTimeStampToDate(updateAt),
                      style: const TextStyle(fontSize: 15, color: Colors.grey),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Container(
                      margin: const EdgeInsets.only(right: 5.0),
                      child: Icon(
                        Icons.add_alarm,
                        size: 20,
                        color: Colors.pink.shade400,
                      ),
                    ),
                    Text(
                      "Hạn đăng kí : " +
                          convertTimeStampToDate(deadlineRegister),
                      style: const TextStyle(fontSize: 15, color: Colors.grey),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Container(
                      margin: const EdgeInsets.only(right: 5.0),
                      child: Icon(
                        Icons.local_hospital_outlined,
                        size: 20,
                        color: Colors.pink.shade400,
                      ),
                    ),
                    Text(
                      "Tên bệnh viện : " + hospital.name,
                      style: const TextStyle(fontSize: 15, color: Colors.grey),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Row(
                  children: [
                    Container(
                      margin: const EdgeInsets.only(right: 5.0),
                      child: Icon(
                        Icons.location_on,
                        size: 20,
                        color: Colors.pink.shade400,
                      ),
                    ),
                    Text(
                      "Địa chỉ : " + hospital.address,
                      style: const TextStyle(fontSize: 15, color: Colors.grey),
                    ),
                  ],
                ),
                const SizedBox(
                  height: 10,
                ),
                Container(
                  padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
                  child: Row(
                    children: [
                      const Spacer(),
                      ElevatedButton(
                        style: ElevatedButton.styleFrom(
                            primary: Colors.pink.shade400),
                        child: const Text(
                          'Đăng kí hiến máu',
                          style: TextStyle(fontSize: 11),
                        ),
                        onPressed: (() => {
                              registerToDonate(context),
                            }),
                      ),
                      const SizedBox(
                        width: 20,
                      ),
                      ElevatedButton(
                        style: ElevatedButton.styleFrom(
                            primary: Colors.pink.shade400),
                        child: const Text(
                          'Hủy đăng kí',
                          style: TextStyle(fontSize: 11),
                        ),
                        onPressed: (() => {
                              PostFindDonateService.cancelToDonate(
                                      idPost, Util.donatorId)
                                  .then((res) => {
                                        showModal(context, "Hủy đăng kí",
                                            'Thành công'),
                                      })
                                  .catchError((error) => {
                                        showModal(
                                            context, "Hủy đăng kí", 'Thất bại'),
                                      }),
                            }),
                      )
                    ],
                  ),
                )
              ],
            ),
          ),
        ));
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

  void registerToDonate(BuildContext buildContext) {
    int now = (DateTime.now().millisecondsSinceEpoch / 1000).round();
    if (now > deadlineRegister) {
      showModal(context, "Quá thời gian đăng kí",
          "Thời hạn đăng kí hiến máu đã hết, bạn không thể đăng kí nữa");
    } else {
      PostFindDonateService.registerToDonate(idPost, Util.donatorId)
          .then((res) => {
                showModal(context, "Đăng kí hiến máu", 'Thành công'),
              })
          .catchError((error) => {
                showModal(context, "Đăng kí hiến máu", 'Thất bại'),
              });
    }
  }
}

class HospitalInfo {
  late String name;
  late String address;

  HospitalInfo(this.name, this.address);
}
