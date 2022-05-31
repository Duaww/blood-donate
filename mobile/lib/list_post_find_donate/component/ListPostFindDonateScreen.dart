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
                          res.data["content"][i]["updateAt"])),
                    },
                },
              )
            })
        .catchError((error) => {
              print(error),
            });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView.builder(
          itemCount: listPostFindDonate.length,
          itemBuilder: (BuildContext context, int index) {
            return Container(
              height: 50,
              child: Center(
                  child: ElevatedButton(
                onPressed: (() => {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => PostFindDonateScreen(
                                  id: listPostFindDonate[index].id))),
                    }),
                child: Text(
                  listPostFindDonate[index].content +
                      listPostFindDonate[index].updateAt.toString(),
                  style: const TextStyle(fontSize: 12),
                ),
              )),
            );
          }),
    );
  }
}

class PostFindDonate {
  late int id;

  late String content;

  late int updateAt;

  PostFindDonate(this.id, this.content, this.updateAt);
}
