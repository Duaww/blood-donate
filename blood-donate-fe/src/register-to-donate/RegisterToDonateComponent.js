import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./RegisterToDonate.css";
import RegisterToDonateService from './RegisterToDonateService';

export const RegisterToDonateComponent = () => {
    
    const navigate = useNavigate();
    const [token, setToken] = useState("");
    const [listRegister, setListRegister] = useState([]);
    const [nameFilter, setNameFilter] = useState("");
    const [bloodFilter, setBloodFilter] = useState("");
    const { key } = useParams();

    useEffect(() => {
        const checkAuth = () => {
            const token = localStorage.getItem('token');
            if (token == null) {
                navigate("/");
                return;
            }
            setToken(token);
            getListRegisterDonate(token, key);
        };
        checkAuth();
    }, [])

    function getListRegisterDonate(token, key) {
        const params = {
            page: 1,
            size: 10
        };
        RegisterToDonateService.getListRegisterDonateByPostId(token, key, params)
            .then((res) => {
                // console.log(res.data)
                let listRegister = res.data.content;
                setListRegister(listRegister);
            })
            .catch((error) => {
                console.log(error);
            })
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

        RegisterToDonateService.getListRegisterWithFilter(token, key, pageable, requestBody)
            .then((res) => {
                let listRegister = res.data.content;
                setListRegister(listRegister);
            })
            .catch((error) => {
                console.log(error);
            });
    }

    return (
        <div>
            listRegister
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
            {listRegister.map((register, index) => (
                <div key={index}>
                    <p>
                       name: {register["donatorInfoDTO"]["name"]}
                    </p>
                     <p>
                        blood :  {register["donatorInfoDTO"]["blood"]}
                    </p>
                     <p>
                        email : {register["donatorInfoDTO"]["email"]}
                    </p>
                     <p>
                        id card : {register["donatorInfoDTO"]["idCard"]}
                    </p>
                     <p>
                        phone : {register["donatorInfoDTO"]["phone"]}
                    </p>
                    <span>birthday : {convertTimeStampToDate(register["donatorInfoDTO"]["birthday"])}</span>
            
                    <span>--------------------</span>
                </div>
            ))}
            <input type="button" value="Back" onClick={() => goBack()} />
        </div>
    );
}