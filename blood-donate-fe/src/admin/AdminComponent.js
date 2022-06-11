import "./Admin.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import LoginService from "../login/LoginService";
import TokenService from "../jwt/TokenService";
import { Role } from "../common/Role";

export const AdminComponent = (props) => {
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

  function createNewHospital() {
    navigate("/create-hospital");
  }

  function listHospital() {
    navigate("/hospital");
  }

  function logout() {
    LoginService.logout(token)
      .then((res) => {
        localStorage.removeItem("token");
        navigate("/");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div>
      <input
        type="button"
        value="Create new Hospital"
        onClick={() => createNewHospital()}
      />
      <input
        type="button"
        value="List Hospital"
        onClick={() => listHospital()}
      />
      <input type="button" value="Logout" onClick={() => logout()} />
    </div>
  );
};
