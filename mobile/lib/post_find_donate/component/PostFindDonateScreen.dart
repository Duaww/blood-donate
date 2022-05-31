import 'package:flutter/cupertino.dart';
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

  @override
  void initState() {
    super.initState();
    PostFindDonateService.getDetailPost(idPost.toString())
        .then((res) => {
              setState(() => {
                    content = res.data["content"],
                    deadlineRegister = res.data["deadlineRegister"],
                    createAt = res.data["createAt"],
                    updateAt = res.data["updateAt"]
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
      child: Column(children: [
        Text(content),
        Text(deadlineRegister.toString()),
        Text(createAt.toString()),
        Text(updateAt.toString()),
        Container(
            child: ElevatedButton(
          child: const Text('Register'),
          onPressed: (() => {
                PostFindDonateService.registerToDonate(idPost, Util.donatorId)
                    .then((res) => {
                          showModal(context, "Register donate", 'SUCCESS'),
                        })
                    .catchError((error) => {
                          showModal(context, "Register donate", 'FAILED'),
                        }),
              }),
        )),
        Container(
            child: ElevatedButton(
          child: const Text('Cancel Register'),
          onPressed: (() => {
                PostFindDonateService.cancelToDonate(idPost, Util.donatorId)
                    .then((res) => {
                          showModal(
                              context, "Cancel Register donate", 'SUCCESS'),
                        })
                    .catchError((error) => {
                          showModal(
                              context, "Cancel Register donate", 'FAILED'),
                        }),
              }),
        )),
        Container(
            child: ElevatedButton(
          child: const Text('Back'),
          onPressed: (() => {
                Navigator.pop(context),
              }),
        )),
      ]),
    );
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
                    icon: new Icon(Icons.close),
                    onPressed: () {
                      Navigator.pop(context);
                    })
              ],
            ));
  }
}
