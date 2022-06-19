import "./Login.css";
import React, { useState } from "react";
import LoginService from "./LoginService";
import TokenService from "../jwt/TokenService";
import { useNavigate } from "react-router-dom";
import { Role } from "../common/Role";

export const LoginComponent = (props) => {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  function login() {
    if (username == "" || password == "") {
      window.alert("please fill full information");
      return;
    }

    let account = { username: username, password: password };
    LoginService.login(account)
      .then((res) => {
        let token = res.data;
        let info = TokenService.decode(token);
        let authorizon = info["authorities"][0];
        if (authorizon == Role.DONATOR) {
          navigate("/auth-error");
        } else if (authorizon == Role.HOSPITAL) {
          localStorage.setItem("token", token);
          navigate("/my-profile");
        } else {
          localStorage.setItem("token", token);
          navigate("/admin");
        }
      })
      .catch((error) => {
        // let message = error.response.data;
        let statusCode = error.response.status;
        if (statusCode === 400 || statusCode === 401) {
          navigate("/auth-error");
        }
      });
  }

  return (
    <div class="parent clearfix">
      <div class="bg-illustration">
        <div class="burger-btn"></div>
      </div>
      <div class="login">
        <div class="container">
          <h1>
            Đăng nhập bằng tài khoản admin
            <br />
            hoặc tài khoản bệnh viện
          </h1>

          <div class="login-form">
            <form action="">
              <input
                type="text"
                placeholder="Tài khoản admin hoặc bệnh viện"
                name="username"
                value={username}
                onChange={(e) => setUserName(e.target.value)}
                required
              />
              <input
                type="password"
                placeholder="Mật khẩu"
                name="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
              <button
                className="button-login"
                type="button"
                value="Login"
                onClick={() => login()}
              >
                Đăng nhập
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};
