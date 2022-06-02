import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:mobile/constant/enviroment.dart';
import 'package:mobile/constant/util.dart';

class PostFindDonateService {
  static final Dio _dio = Dio();

  static final String _BASE_POST_URL = Enviroment.BASE_URL + "/api/post";
  static final String _BASE_REGISTER_URL =
      Enviroment.BASE_URL + "/api/register-post";

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
      throw Future.error(e.message);
    }
    return response;
  }

  static Future<Response> cancelToDonate(int idPost, int donatorId) async {
    Response response;
    var requestBody = {
      "postId": idPost,
      "donatorId": donatorId,
    };
    try {
      response = await _dio.post(
        _BASE_REGISTER_URL + "/cancel",
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
