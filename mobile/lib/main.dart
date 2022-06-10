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
  // If you're going to use other Firebase services in the background, such as Firestore,
  // make sure you call `initializeApp` before using other Firebase services.
  await Firebase.initializeApp();

  print("Handling a background message: ${message.messageId}");
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  static const String _title = 'Blood Donate App';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      navigatorKey: navigatorKey,
      debugShowCheckedModeBanner: false,
      title: _title,
      home: Scaffold(
        resizeToAvoidBottomInset: true,
        appBar: AppBar(title: const Text(_title)),
        body: LoginScreen(),
      ),
    );
  }
}
