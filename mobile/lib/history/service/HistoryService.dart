import 'package:dio/dio.dart';

import '../../constant/enviroment.dart';
import '../../constant/util.dart';

class HistoryService {
  static final Dio _dio = Dio();
  static final String _BASE_HISTORY_URL = Enviroment.BASE_URL + "/api/donated";

  static Future<Response> getHistoryDonated() async {
    Response response;

    var pageable = {
      "page": 0,
      "size": 10,
    };
    try {
      response = await _dio.get(
        _BASE_HISTORY_URL + "/history",
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
