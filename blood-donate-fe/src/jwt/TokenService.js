import jwt_decode from "jwt-decode";

const USER = "TINH";

class TokenService {

    decode(token) {
        return jwt_decode(token)[USER];
    }

}

export default new TokenService();

