import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AdminService from "./AdminService";

export const CreateHospitalComponent = (props) => {
  const [username, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [hospitalName, setHospitalName] = useState("");
  const [address, setAddress] = useState("");
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  console.log(token);

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

  return (
    <div>
      <form>
        <div className="input-container">
          <label>Account</label>
          <input
            type="text"
            name="username"
            value={username}
            onChange={(e) => setUserName(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <label>Hospital name </label>
          <input
            type="text"
            name="hospitalName"
            value={hospitalName}
            onChange={(e) => setHospitalName(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <label>Address </label>
          <input
            type="text"
            name="address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <label>Password </label>
          <input
            type="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <div className="input-container">
          <label>Confirm Password </label>
          <input
            type="password"
            name="password"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            required
          />
        </div>
        <div className="button-container">
          <input
            type="button"
            value="Create Hopital"
            onClick={() => createHospital()}
          />
        </div>
      </form>
    </div>
  );
};
