import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:mobile/constant/enviroment.dart';
import 'package:mobile/constant/util.dart';

class PostFindDonateService {
  static Dio _dio = new Dio();

  static String _BASE_POST_URL = Enviroment.BASE_URL + "/api/post";
  static String _BASE_REGISTER_URL = Enviroment.BASE_URL + "/api/register-post";

  static Future<Response> getDetailPost(key) async {
    Response response;
    try {
      response = await _dio.get(
        _BASE_POST_URL + "/detail/" + key,
        options: Options(
            contentType: Headers.jsonContentType,
            responseType: ResponseType.json,
            headers: {"Authorization": "Token " + Util.token}),
      );
    } on DioError catch (e) {
      throw Exception(e.response?.statusCode);
    }
    return response;
  }

  static Future<Response> registerToDonate(int postId, int donatorId) async {
    Response response;
    var requestBody = {
      "postId": postId,
      "donatorId": donatorId,
    };
    try {
      response = await _dio.post(
        _BASE_REGISTER_URL,
        data: jsonEncode(requestBody),
        options: Options(
            contentType: Headers.jsonContentType,
            responseType: ResponseType.json,
            headers: {"Authorization": "Token " + Util.token}),
      );
    } on DioError catch (e) {
      throw Exception(e.response?.statusCode);
    }
    return response;
  }
}
