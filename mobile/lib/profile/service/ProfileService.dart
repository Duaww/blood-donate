import 'package:dio/dio.dart';
import 'package:mobile/constant/enviroment.dart';
import 'package:mobile/constant/util.dart';

class ProfileService {
  static Dio _dio = new Dio();
  static String _BASE_PROFILE_URL =
      Enviroment.BASE_URL + "/api/user/my-profile";

  static Future<Response> getMyProfile() async {
    Response response;
    try {
      response = await _dio.get(
        _BASE_PROFILE_URL,
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
