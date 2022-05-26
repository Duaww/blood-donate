import { useNavigate } from "react-router-dom";
import React, {useEffect ,useState } from "react";
import "./DonatedBlood.css";
import DonatedBloodService from "./DonatedBloodService";

export const DonatedBloodComponent = () => {
    
    const [token, setToken] = useState("");
    const [listDonator, setListDonator] = useState([]);
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
        console.log(token)
        DonatedBloodService.getListDonatorSeflHospital(token, pageable)
            .then((res) => {
                let listDonator = res.data.content;
                setListDonator(listDonator);
                console.log(listDonator);
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

    return (
        <div>
            listDonator
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