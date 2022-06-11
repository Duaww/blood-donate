import axios from "axios";

const AUTH_BASE_API_URI = "http://localhost:8080/api";

class AdminService {
  createNewHospital(token, requetsBody) {
    const CREATE_HOSPITAL_URI = AUTH_BASE_API_URI + "/user/create-hospital";
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    return axios.post(CREATE_HOSPITAL_URI, requetsBody, { headers: headers });
  }

  getListHospital(token, pageble) {
    const LIST_HOSPITAL_URI = AUTH_BASE_API_URI + "/hospital";
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    return axios.get(LIST_HOSPITAL_URI, { headers: headers, params: pageble });

  }
}

export default new AdminService();
