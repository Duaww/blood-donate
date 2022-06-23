import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_messaging/firebase_messaging.dart';
import 'package:flutter/material.dart';
import 'package:mobile/constant/util.dart';
import 'package:mobile/firebase_options.dart';
import 'package:mobile/login/component/LoginScreen.dart';
import 'package:mobile/post_find_donate/component/PostFindDonateScreen.dart';

GlobalKey<NavigatorState> navigatorKey = GlobalKey<NavigatorState>();

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  FirebaseMessaging.onBackgroundMessage(_firebaseMessagingBackgroundHandler);
  FirebaseMessaging messaging = FirebaseMessaging.instance;
  NotificationSettings settings = await messaging.requestPermission(
      alert: true,
      announcement: false,
      badge: true,
      carPlay: false,
      criticalAlert: false,
      provisional: false,
      sound: true);

  print("User granted permission: ${settings.authorizationStatus}");
  String? tokenDevice = await FirebaseMessaging.instance.getToken(); //
  print(tokenDevice);
  FirebaseMessaging.onMessage.listen((RemoteMessage message) {
    print('Got a message in the foreground');
    print('message data : ${message.data}');

    if (message.notification != null) {
      print('message a notification : ${message.notification}');
    }
  });

  FirebaseMessaging.onMessageOpenedApp.listen((RemoteMessage message) {
    var data = message.data;
    Util.postIdNotification = data["postId"];
    print(Util.postIdNotification);
  });

  runApp(const MyApp());
}

Future<void> _firebaseMessagingBackgroundHandler(RemoteMessage message) async {
  await Firebase.initializeApp();
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  static const String _title = 'Ứng dụng hỗ trợ hiến máu';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      navigatorKey: navigatorKey,
      debugShowCheckedModeBanner: false,
      title: _title,
      home: Scaffold(
        resizeToAvoidBottomInset: true,
        appBar: AppBar(
          title: Container(
            padding: const EdgeInsets.fromLTRB(60, 0, 0, 0),
            child: const Text(
              _title,
              style: TextStyle(color: Colors.white),
            ),
          ),
          backgroundColor: Colors.pink.shade400,
        ),
        body: LoginScreen(),
      ),
    );
  }
}
