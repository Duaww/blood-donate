// File generated by FlutterFire CLI.
// ignore_for_file: lines_longer_than_80_chars, avoid_classes_with_only_static_members
import 'package:firebase_core/firebase_core.dart' show FirebaseOptions;
import 'package:flutter/foundation.dart'
    show defaultTargetPlatform, kIsWeb, TargetPlatform;

/// Default [FirebaseOptions] for use with your Firebase apps.
///
/// Example:
/// ```dart
/// import 'firebase_options.dart';
/// // ...
/// await Firebase.initializeApp(
///   options: DefaultFirebaseOptions.currentPlatform,
/// );
/// ```
class DefaultFirebaseOptions {
  static FirebaseOptions get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return android;
      case TargetPlatform.iOS:
        return ios;
      case TargetPlatform.macOS:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for macos - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      case TargetPlatform.windows:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for windows - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      case TargetPlatform.linux:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for linux - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      default:
        throw UnsupportedError(
          'DefaultFirebaseOptions are not supported for this platform.',
        );
    }
  }

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: 'AIzaSyB3kMnPWX03sRE3b4JZYIgZgku8NnS2_KQ',
    appId: '1:187635518502:web:3523e452a2cc723e93aae7',
    messagingSenderId: '187635518502',
    projectId: 'blood-donator-message',
    authDomain: 'blood-donator-message.firebaseapp.com',
    storageBucket: 'blood-donator-message.appspot.com',
    measurementId: 'G-4GJT80H4XH',
  );

  static const FirebaseOptions android = FirebaseOptions(
    apiKey: 'AIzaSyALmcbgvUdmw8EXO1hsxx_KEGcyRTv88NA',
    appId: '1:187635518502:android:0cd68cff6643fce893aae7',
    messagingSenderId: '187635518502',
    projectId: 'blood-donator-message',
    storageBucket: 'blood-donator-message.appspot.com',
  );

  static const FirebaseOptions ios = FirebaseOptions(
    apiKey: 'AIzaSyBvGkGRdiOzZArFxMlAFKFOIcDzIQ1JSKU',
    appId: '1:187635518502:ios:b17672939ed37c9893aae7',
    messagingSenderId: '187635518502',
    projectId: 'blood-donator-message',
    storageBucket: 'blood-donator-message.appspot.com',
    iosClientId: '187635518502-plqap8np99ntq7l1evqk7l1jcrhnd1no.apps.googleusercontent.com',
    iosBundleId: 'com.example.mobile',
  );
}
