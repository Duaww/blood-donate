import 'package:jwt_decode/jwt_decode.dart';

const USER = "TINH";

class TokenSerivce {
  static Map<String, dynamic> decode(String token) {
    return Jwt.parseJwt(token)[USER];
  }
}
