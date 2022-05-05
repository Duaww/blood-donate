import { useNavigate } from "react-router-dom";
import { useLocation } from 'react-router-dom';
import React, {useEffect ,useState } from "react";
import "./ListPost.css";

export const ListPostComponent = () => {

    const [token, setToken] = useState("");
    const navigate = useNavigate();
    const location = useLocation();
    // console.log(location);

      
    useEffect(() => {
        const checkAuth = () => {
            if (location["state"] == null) {
                navigate("/");
                return;
            }
            setToken(location["state"]["token"]);
        };
        checkAuth();
    }, []);


    function goBack() {
        navigate(-1);
    }

    return (
        <div>
            ListPost
            <input type="button" value="Back" onClick={() => goBack()} />
        </div>
    );
}