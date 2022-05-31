import 'package:dio/dio.dart';

import '../../constant/enviroment.dart';
import '../../constant/util.dart';

class ListPostFindService {
  static final Dio _dio = Dio();
  static final String _BASE_POST_URL = Enviroment.BASE_URL + "/api/post";
  static Future<Response> getListPostByIdHospital(idHospital) async {
    Response response;

    var pageable = {
      "page": 0,
      "size": 10,
    };
    try {
      response = await _dio.get(
        _BASE_POST_URL + "/" + idHospital.toString(),
        queryParameters: pageable,
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
