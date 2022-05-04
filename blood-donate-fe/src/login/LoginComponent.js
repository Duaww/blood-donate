import "./Login.css";
import React, { useState } from "react";
import LoginService from "./LoginService";
import TokenService from "../jwt/TokenService";
import {Link ,useNavigate } from "react-router-dom";
import { Role } from "../common/Role";

export const LoginComponent = (props) => {
    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    function login() {
        let account = { username: username, password: password };
        LoginService.login(account)
            .then((res) => {
                let token = res.data;
                let info = TokenService.decode(token);
                let authorizon = info["authorities"][0];
                if (authorizon != Role.HOSPITAL) {
                    navigate("/auth-error");
                } else {
                    navigate("/my-profile", {state: {token}});
                }
            })
            .catch((error) => {
                // let message = error.response.data;
                let statusCode = error.response.status;
                if (statusCode === 400) {
                    navigate("/auth-error");
                }
            });
    }

    return (
        <div className="form">
            <form>
                <div className="input-container">
                    <label>Username </label>
                    <input type="text" name="username" value={username} onChange={(e) => setUserName(e.target.value)} required />
                </div>
                <div className="input-container">
                    <label>Password </label>
                    <input type="password" name="password"  value={password} onChange={(e) => setPassword(e.target.value)} required />
                </div>
                <div className="button-container">
                    
                    <input type="button" value="Login" onClick={() => login()}/>
                </div>
            </form>
        </div>
    );
}