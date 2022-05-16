import 'dart:convert';
import 'dart:io';

import 'package:dio/dio.dart';
import 'package:mobile/constant/enviroment.dart';

class LoginService {
  static Dio _dio = new Dio();
  static String _BASE_AUTH_URL = Enviroment.BASE_URL + "/api/auth";

  static Future<Response> login(String username, String password) async {
    Response response;
    var loginForm = {
      "username": username,
      "password": password,
    };
    try {
      response = await _dio.post(_BASE_AUTH_URL,
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
