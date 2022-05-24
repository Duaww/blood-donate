import axios from "axios";


const POST_BASE_API_URI = "http://localhost:8080/api/post/"

class PostFindDonatorService {

    getListMyPost(token, params) {
        const MY_LIST_POST_URI = POST_BASE_API_URI + "my-post";

        const author = "Token " + token;
        const headers = {
            'Authorization': author
        };
        return axios.get(MY_LIST_POST_URI, { headers: headers, params: params });
    }

    createNewPost(token, postInfo) {
        const author = "Token " + token;
        const headers = {
            'Authorization': author,
            'Content-Type': 'application/json'
        };
        return axios.post(POST_BASE_API_URI, postInfo, { headers: headers });
    }

    getPostDetail(token, key) {
        const author = "Token " + token;
        const headers = {
            'Authorization': author
        };

        const DETAIL_POST_URI = POST_BASE_API_URI + "detail/" + key;
        return axios.get(DETAIL_POST_URI, { headers: headers });
    }

    updatePost(token, key, updateForm) {
        const author = "Token " + token;
        const headers = {
            'Authorization': author,
            'Content-Type': 'application/json'
        };
        const UPDATE_POST_URI = POST_BASE_API_URI + key;
        return axios.put(UPDATE_POST_URI, updateForm, { headers: headers });
    }

    deletePost(token, key) {
        const author = "Token " + token;
        const headers = {
            'Authorization': author
        };
        const DELETE_POST_URI = POST_BASE_API_URI + key;
        return axios.delete(DELETE_POST_URI, { headers: headers });
    }
}

export default new PostFindDonatorService();