import { useNavigate } from "react-router-dom";
import React, {useEffect ,useState } from "react";
import "./DonatedBlood.css";
import DonatedBloodService from "./DonatedBloodService";

export const DonatedBloodComponent = () => {
    
    const [token, setToken] = useState("");
    const [listDonator, setListDonator] = useState([]);
    const [nameFilter, setNameFilter] = useState("");
    const [bloodFilter, setBloodFilter] = useState("");
    const navigate = useNavigate();


    useEffect(() => {
        const checkAuth = () => {
            const token = localStorage.getItem('token');
            if (token == null) {
                navigate("/");
                return;
            }
            setToken(token);
            getListDonator(token);
        }
        checkAuth();
    }, []);
    

    function getListDonator(token) {
        const pageable = {
            page: 1,
            size: 10
        };
        DonatedBloodService.getListDonatorSeflHospital(token, pageable)
            .then((res) => {
                let listDonator = res.data.content;
                setListDonator(listDonator);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    
    function convertTimeStampToDate(timestamp) {
        return new Date(timestamp * 1000).toDateString();
    }

    function goBack() {
        navigate(-1);
    }

    function search() {
        let pageable = {
            page: 1,
            size: 10
        };
        let requestBody = {
            name: nameFilter,
            blood: bloodFilter.split(":")
        };

        console.log(requestBody);

        DonatedBloodService.getListDonatedWithFilter(token, pageable, requestBody)
            .then((res) => {
                let listDonator = res.data.content;
                setListDonator(listDonator);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    return (
        <div>
            listDonator
            <div>
                <label>Name:</label>
                <input type="text" id="" name="" value={nameFilter}  onChange={(e) => setNameFilter(e.target.value)} />
                <label>Blood group :</label>
                <select id="blood" value={bloodFilter} onChange={(e) => setBloodFilter(e.target.value)} >
                    <option value="O:A:B:AB" selected>All</option>
                    <option value="O">O</option>
                    <option value="A">A</option>
                    <option value="B">B</option>
                    <option value="AB">AB</option>
                </select>
                <input type="button" value="Search" onClick={() => search()} />
            </div>
            {listDonator.map((donator, index) => (
                <div key={index}>
                    <p>
                       name: {donator["donatorInfoDTO"]["name"]}
                    </p>
                     <p>
                        blood :  {donator["donatorInfoDTO"]["blood"]}
                    </p>
                     <p>
                        email : {donator["donatorInfoDTO"]["email"]}
                    </p>
                     <p>
                        id card : {donator["donatorInfoDTO"]["idCard"]}
                    </p>
                     <p>
                        phone : {donator["donatorInfoDTO"]["phone"]}
                    </p>
                    <span>birthday : {convertTimeStampToDate(donator["donatorInfoDTO"]["birthday"])}</span>
            
                    <span>--------------------</span>
                </div>
            ))}
            <input type="button" value="Back" onClick={() => goBack()} />
        </div>
    );
}