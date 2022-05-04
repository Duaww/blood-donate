import axios from "axios";

const AUTH_BASE_API_URI = "http://localhost:8080/api/auth";

class LoginService {

    login(account) {
        return axios.post(AUTH_BASE_API_URI, account);
    }
}

export default new LoginService();