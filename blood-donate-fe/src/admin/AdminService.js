import axios from "axios";

const AUTH_BASE_API_URI = "http://localhost:8080/api/user";

class AdminService {
  createNewHospital(token, requetsBody) {
    const CREATE_HOSPITAL_URI = AUTH_BASE_API_URI + "/create-hospital";
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    return axios.post(CREATE_HOSPITAL_URI, requetsBody, { headers: headers });
  }
}

export default new AdminService();
