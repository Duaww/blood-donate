import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Role } from "../common/Role";
import TokenService from "../jwt/TokenService";
import AdminService from "./AdminService";

export const CreateHospitalComponent = (props) => {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [hospitalName, setHospitalName] = useState("");
  const [address, setAddress] = useState("");
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    checkAuth();
  }, []);

  function checkAuth() {
    if (token == null) {
      navigate("/");
      return;
    }
    let info = TokenService.decode(token);
    let authorizon = info["authorities"][0];
    if (authorizon != Role.ADMIN) {
      navigate("/auth-error");
      return;
    }
  }

  function createHospital() {
    if (
      username == "" ||
      password == "" ||
      confirmPassword == "" ||
      hospitalName == "" ||
      address == ""
    ) {
      window.alert("please fill full information");
      return;
    }

    if (password != confirmPassword) {
      window.alert("password and confirm password is incorrect");
      return;
    }

    let formCreate = {
      username: username,
      password: password,
      hospitalName: hospitalName,
      address: address,
    };
    AdminService.createNewHospital(token, formCreate)
      .then((res) => {
        console.log(res);
        navigate(-1);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function goBack() {
    navigate(-1);
  }

  return (
    <div>
      <div className="container">
        <div id="post">
          <h3>Tạo tài khoản bệnh viện</h3>
          <fieldset>
            <h4>Tài khoản:</h4>
            <input
              type="text"
              name="username"
              value={username}
              onChange={(e) => setUserName(e.target.value)}
            />
          </fieldset>
          <fieldset>
            <h4>Tên bệnh viện:</h4>
            <input
              type="text"
              name="hospitalName"
              value={hospitalName}
              onChange={(e) => setHospitalName(e.target.value)}
            />
          </fieldset>
          <fieldset>
            <h4>Địa chỉ:</h4>
            <input
              type="text"
              name="address"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
            />
          </fieldset>
          <fieldset>
            <h4>Mật khẩu:</h4>
            <input
              type="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </fieldset>
          <fieldset>
            <h4>Xác nhận mật khẩu:</h4>
            <input
              type="password"
              name="password"
              value={confirmPassword}
              oonChange={(e) => setConfirmPassword(e.target.value)}
            />
          </fieldset>

          <fieldset>
            <button
              className="button"
              onClick={() => createHospital()}
              id="post-submit"
            >
              Tạo tài khoản
            </button>
          </fieldset>
          <fieldset>
            <button
              className="button"
              id="post-submit"
              onClick={() => goBack()}
            >
              Trở về
            </button>
          </fieldset>
        </div>
      </div>
    </div>
  );
};
