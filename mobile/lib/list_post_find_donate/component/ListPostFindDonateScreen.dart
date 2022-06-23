import 'package:flutter/material.dart';
import 'package:mobile/list_post_find_donate/service/ListPostFindDonateService.dart';
import 'package:mobile/post_find_donate/component/PostFindDonateScreen.dart';

class ListPostFindDonateScreen extends StatefulWidget {
  final int id;

  const ListPostFindDonateScreen({Key? key, required this.id})
      : super(key: key);

  @override
  State<ListPostFindDonateScreen> createState() =>
      _ListPostFindDonateScreenState(this.id);
}

class _ListPostFindDonateScreenState extends State<ListPostFindDonateScreen> {
  final int id;
  _ListPostFindDonateScreenState(this.id);

  List<PostFindDonate> listPostFindDonate = [];

  @override
  void initState() {
    super.initState();
    ListPostFindService.getListPostByIdHospital(id)
        .then((res) => {
              setState(
                () => {
                  for (int i = 0; i < res.data["content"].length; i++)
                    {
                      listPostFindDonate.add(PostFindDonate(
                          res.data["content"][i]["id"],
                          res.data["content"][i]["content"],
                          res.data["content"][i]["updateAt"],
                          res.data["content"][i]["createAt"])),
                    },
                },
              )
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
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Container(
          padding: const EdgeInsets.fromLTRB(40, 0, 0, 0),
          child: const Text(
            "Danh sách bài đăng",
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
        padding: const EdgeInsets.fromLTRB(0, 10, 0, 0),
        child: ListView.builder(
            itemCount: listPostFindDonate.length,
            itemBuilder: (BuildContext context, int index) {
              return Container(
                height: 330,
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
                          child: TextField(
                            enabled: false,
                            maxLines: 6, //or null
                            decoration: const InputDecoration.collapsed(
                                hintText: "Enter your text here"),
                            controller: TextEditingController(
                                text: listPostFindDonate[index].content),
                          ),
                        ),
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
                            "Đăng tải vào ngày : " +
                                convertTimeStampToDate(
                                    listPostFindDonate[index].createAt),
                            style: const TextStyle(
                                fontSize: 15, color: Colors.grey),
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
                            "Cập nhật vào ngày : " +
                                convertTimeStampToDate(
                                    listPostFindDonate[index].updateAt),
                            style: const TextStyle(
                                fontSize: 15, color: Colors.grey),
                          ),
                        ],
                      ),
                      const SizedBox(
                        height: 10,
                      ),
                      Container(
                        padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
                        child: MaterialButton(
                          minWidth: double.infinity,
                          onPressed: () => {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => PostFindDonateScreen(
                                        id: listPostFindDonate[index].id))),
                          },
                          color: Colors.pink.shade400,
                          shape: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(40)),
                          child: const Text(
                            'Chi tiết bài đăng',
                            style: TextStyle(
                              color: Colors.white,
                            ),
                          ),
                        ),
                      )
                    ],
                  ),
                ),
              );
            }),
      ),
    );
  }
}

class PostFindDonate {
  late int id;

  late String content;

  late int updateAt;

  late int createAt;

  PostFindDonate(this.id, this.content, this.updateAt, this.createAt);
}
