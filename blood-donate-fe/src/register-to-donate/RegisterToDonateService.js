import axios from "axios";

const REGISTER_POST_BASE_URI = "http://localhost:8080/api/register-post";

class RegisterToDonateService {

    getListRegisterDonateByPostId(token, postId, params) {
        const LIST_REGISTER__BY_POST_URI = REGISTER_POST_BASE_URI + "/" + postId;
        
        const author = "Token " + token;
        const headers = {
            'Authorization': author
        };
        return axios.get(LIST_REGISTER__BY_POST_URI, { headers: headers, params: params });
    }

    getListRegisterWithFilter(token, postId, params, filter) {

        const LIST_REGISTER__BY_POST_URI = REGISTER_POST_BASE_URI + "/" + postId;
        const author = "Token " + token;
        const headers = {
            'Authorization': author
        };
        return axios.post(LIST_REGISTER__BY_POST_URI, filter, { headers: headers, params: params });
    }

}

export default new RegisterToDonateService();