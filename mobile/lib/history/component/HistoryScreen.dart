import 'package:flutter/material.dart';
import 'package:mobile/history/service/HistoryService.dart';
import 'package:mobile/list_hospital/component/ListHopitalScreen.dart';

class HistoryScreen extends StatefulWidget {
  const HistoryScreen({Key? key}) : super(key: key);

  @override
  State<HistoryScreen> createState() => _HistoryScreenState();
}

class _HistoryScreenState extends State<HistoryScreen> {
  List<History> listHistory = [];

  @override
  void initState() {
    super.initState();
    HistoryService.getHistoryDonated()
        .then((res) => {
              setState(() => {
                    for (int i = 0; i < res.data["content"].length; i++)
                      {
                        listHistory.add(History(
                            res.data["content"][i]["id"],
                            res.data["content"][i]["timeDonated"],
                            Hospital(
                                res.data["content"][i]["hospitalInfoDTO"]["id"],
                                res.data["content"][i]["hospitalInfoDTO"]
                                    ["name"],
                                res.data["content"][i]["hospitalInfoDTO"]
                                    ["address"])))
                      }
                  })
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
    // return Container(
    //   child: ListView.builder(
    //       itemCount: listHistory.length,
    //       itemBuilder: (BuildContext context, int index) {
    //         return Container(
    //           height: 50,
    //           child: Center(
    //             child: Text(
    //               listHistory[index].hopital.name +
    //                   "--" +
    //                   listHistory[index].timeDonanted.toString(),
    //               style: const TextStyle(fontSize: 12),
    //             ),
    //           ),
    //         );
    //       }),
    // );
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Container(
          padding: const EdgeInsets.fromLTRB(40, 0, 0, 0),
          child: const Text(
            "Lịch sử hiến máu",
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
            itemCount: listHistory.length,
            itemBuilder: (BuildContext context, int index) {
              return Container(
                height: 80,
                padding: const EdgeInsets.fromLTRB(10, 10, 10, 10),
                child: GestureDetector(
                  child: Container(
                    padding: const EdgeInsets.fromLTRB(5, 5, 5, 5),
                    decoration: BoxDecoration(
                      border: Border.all(color: Colors.pink),
                      borderRadius: BorderRadius.circular(5),
                    ),
                    child: Row(
                      crossAxisAlignment: CrossAxisAlignment.center,
                      children: [
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
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
                                  listHistory[index].hopital.name,
                                  style: const TextStyle(fontSize: 15),
                                ),
                              ],
                            ),
                            const SizedBox(
                              height: 5,
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
                                  convertTimeStampToDate(
                                      listHistory[index].timeDonanted),
                                  style: const TextStyle(fontSize: 15),
                                ),
                              ],
                            )
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
              );
            }),
      ),
    );
  }
}

class History {
  late int id;
  late int timeDonanted;
  late Hospital hopital;

  History(this.id, this.timeDonanted, this.hopital);
}
