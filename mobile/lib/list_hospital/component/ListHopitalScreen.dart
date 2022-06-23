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
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: Colors.white,
      appBar: AppBar(
        title: Container(
          padding: const EdgeInsets.fromLTRB(40, 0, 0, 0),
          child: const Text(
            "Danh sách bệnh viện",
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
            itemCount: listHospital.length,
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
                                  listHospital[index].name,
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
                                    Icons.location_on,
                                    size: 20,
                                    color: Colors.pink.shade400,
                                  ),
                                ),
                                Text(
                                  listHospital[index].address,
                                  style: const TextStyle(fontSize: 15),
                                ),
                              ],
                            )
                          ],
                        ),
                        const Spacer(),
                        Row(
                          children: [
                            Text(
                              "Bài đăng",
                              style: TextStyle(
                                fontSize: 12,
                                color: Colors.pink.shade400,
                              ),
                            ),
                            Icon(
                              Icons.arrow_forward,
                              size: 25,
                              color: Colors.pink.shade400,
                            )
                          ],
                        )
                      ],
                    ),
                  ),
                  onTap: () => {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => ListPostFindDonateScreen(
                                id: listHospital[index].id))),
                  },
                ),
              );
            }),
      ),
    );
  }
}

class Hospital {
  late int id;
  late String name;
  late String address;

  Hospital(this.id, this.name, this.address);
}
