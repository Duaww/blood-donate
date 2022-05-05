import axios from "axios";

const AUTH_BASE_API_URI = "http://localhost:8080/api/auth";

class LoginService {

    login(account) {
        return axios.post(AUTH_BASE_API_URI, account);
    }

    logout(token) {
        const LOGOUT_URI = AUTH_BASE_API_URI + "/logout";
        const author = "Token " + token;
        const headers = {
            'Authorization': author,
            'Content-Type': 'text/plain'
        };
        return axios.post(LOGOUT_URI, token, { headers: headers });
    }
}

export default new LoginService();