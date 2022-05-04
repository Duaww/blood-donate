import axios from "axios";

const AUTH_BASE_API_URI = "http://localhost:8080/api/test/";

class MyProfileService {

    getMyProfile(token) {
        const PROFILE_URI = AUTH_BASE_API_URI + "my-profile";
        const valueHeader = "Token " + token;
        return axios.get(PROFILE_URI, { headers: { "Authorization": valueHeader } });
    }

}


export default new MyProfileService();