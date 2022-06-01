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

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView.builder(
          itemCount: listHistory.length,
          itemBuilder: (BuildContext context, int index) {
            return Container(
              height: 50,
              child: Center(
                child: Text(
                  listHistory[index].hopital.name +
                      "--" +
                      listHistory[index].timeDonanted.toString(),
                  style: const TextStyle(fontSize: 12),
                ),
              ),
            );
          }),
    );
  }
}

class History {
  late int id;
  late int timeDonanted;
  late Hospital hopital;

  History(this.id, this.timeDonanted, this.hopital);
}
