import 'dart:convert';

import 'package:dio/dio.dart';

import '../../constant/enviroment.dart';

class SignUpService {
  static final Dio _dio = Dio();
  static final String _BASE_USER_URL = Enviroment.BASE_URL + "/api/user";

  static Future<Response> signup(requestBody) async {
    Response response;
    try {
      response = await _dio.post(_BASE_USER_URL,
          data: jsonEncode(requestBody),
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
