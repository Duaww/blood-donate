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

  getListDonator(token, pageble) {
    const LIST_DONATOR_URI = AUTH_BASE_API_URI + "/donator";
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    return axios.get(LIST_DONATOR_URI, { headers: headers, params: pageble });
  }

  lockAccount(token, donatorId) {
    const ADMIN_URI = AUTH_BASE_API_URI + "/admin";
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    let requetsBody = {
      donatorId: donatorId,
    };
    return axios.post(ADMIN_URI, requetsBody, {
      headers: headers,
    });
  }
}

export default new AdminService();
