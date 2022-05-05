import axios from "axios";

const AUTH_BASE_API_URI = "http://localhost:8080/api/test/";

class MyProfileService {

    getMyProfile(token) {
        const PROFILE_URI = AUTH_BASE_API_URI + "my-profile";
        const author = "Token " + token;
        const headers = {
            'Authorization': author,
        }
        return axios.get(PROFILE_URI, { headers: headers });
    }

}


export default new MyProfileService();