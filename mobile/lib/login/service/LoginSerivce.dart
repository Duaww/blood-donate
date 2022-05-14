import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';

class LoginService {
  static Dio _dio = new Dio();
  static String BASE_AUTH_URL = "http://192.168.1.11:8080/api/auth";

  static Future<Response> login(String username, String password) async {
    Response response;
    var loginForm = {
      "username": username,
      "password": password,
    };
    try {
      response = await _dio.post(BASE_AUTH_URL,
          data: jsonEncode(loginForm),
          options: Options(
            contentType: Headers.jsonContentType,
            responseType: ResponseType.json,
          ));
    } on DioError catch (e) {
      throw Exception(e.response?.statusCode);
    }
    return response;
  }
}
