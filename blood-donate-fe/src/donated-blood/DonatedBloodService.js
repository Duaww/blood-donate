import axios from "axios";

const BASE_DONATED_URI = "http://localhost:8080/api/donated";

class DonatedBloodService {

    getListDonatorSeflHospital(token, pageable) {
        const author = "Token " + token;
        const headers = {
            'Authorization': author
        };
        return axios.get(BASE_DONATED_URI, { headers: headers, params: pageable });
    }

    getListDonatedWithFilter(token, pageable, filter) {
        const author = "Token " + token;
        const headers = {
            'Authorization': author
        };
        return axios.post(BASE_DONATED_URI, filter, { headers: headers, params: pageable });
    } 
}

export default new DonatedBloodService();