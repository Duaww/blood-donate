import axios from "axios";

const BASE_DONATED_URI = "http://localhost:8080/api/donated";

class DonatedBloodService {
  getListDonatorSeflHospital(token, pageable) {
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    return axios.get(BASE_DONATED_URI, { headers: headers, params: pageable });
  }

  getListDonatedWithFilter(token, pageable, filter) {
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    return axios.post(BASE_DONATED_URI, filter, {
      headers: headers,
      params: pageable,
    });
  }

  confirmDonated(token, requestBody) {
    const CONFIRM_DONATED = BASE_DONATED_URI + "/confirm-donated";
    const author = "Token " + token;
    const headers = {
      Authorization: author,
    };
    return axios.post(CONFIRM_DONATED, requestBody, { headers: headers });
  }

  // updateNumNotDonated(token, idDonator) {
  //   const UPDATE_NUM_NOT_DONATED =
  //     "http://localhost:8080/api/donator/update-num-not-donated/" + idDonator;
  //   const author = "Token " + token;
  //   const headers = {
  //     Authorization: author,
  //   };
  //   return axios.get(UPDATE_NUM_NOT_DONATED, { headers: headers });
  // }
}

export default new DonatedBloodService();
