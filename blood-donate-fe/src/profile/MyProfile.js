import "./MyProfile.css";
import { useLocation } from 'react-router-dom';
import React, { useEffect, useState } from "react";
import MyProfileService from "./MyProfileService";

export const MyProfile = () => {
    const [name, setName] = useState("");
    const [address, setAddress] = useState("");
    const location = useLocation();
    const token = location["state"]["token"];

    useEffect(() => {
        const getMyProfile = () => {
            MyProfileService.getMyProfile(token)
                .then((res) => {
                    setName(res.data.name);
                    setAddress(res.data.address);
                })
                .catch((error) => {
                    console.log(error);
                });
        };
        getMyProfile();
    }, []);

    return (
        <div>
            {name}
            <div>
                {address}
            </div>
        </div>
        
    );
}