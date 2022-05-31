import 'package:flutter/material.dart';
import 'package:mobile/list_hospital/service/ListHospitalService.dart';
import 'package:mobile/list_post_find_donate/component/ListPostFindDonateScreen.dart';

class ListHospitalScreen extends StatefulWidget {
  const ListHospitalScreen({Key? key}) : super(key: key);

  @override
  State<ListHospitalScreen> createState() => _ListHospitalScreenState();
}

class _ListHospitalScreenState extends State<ListHospitalScreen> {
  List<Hospital> listHospital = [];

  @override
  void initState() {
    super.initState();
    ListHospitalService.getListHospital()
        .then((res) => {
              setState(
                () => {
                  for (int i = 0; i < res.data["content"].length; i++)
                    {
                      listHospital.add(Hospital(
                          res.data["content"][i]["id"],
                          res.data["content"][i]["name"],
                          res.data["content"][i]["address"])),
                    },
                },
              ),
            })
        .catchError((error) => {
              print(error),
            });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView.builder(
          itemCount: listHospital.length,
          itemBuilder: (BuildContext context, int index) {
            return Container(
              height: 50,
              child: Center(
                  child: ElevatedButton(
                onPressed: (() => {
                      Navigator.push(
                          context,
                          MaterialPageRoute(
                              builder: (context) => ListPostFindDonateScreen(
                                  id: listHospital[index].id))),
                    }),
                child: Text(
                  listHospital[index].name + listHospital[index].address,
                  style: const TextStyle(fontSize: 12),
                ),
              )),
            );
          }),
    );
  }
}

class Hospital {
  late int id;
  late String name;
  late String address;

  Hospital(this.id, this.name, this.address);
}
