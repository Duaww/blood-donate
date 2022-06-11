import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import AdminService from "./AdminService";
import TokenService from "../jwt/TokenService";
import { Role } from "../common/Role";

export const ListHospitalComponent = () => {
  const navigate = useNavigate();
  const [listHospital, setListHospital] = useState([]);
  const token = localStorage.getItem("token");

  useEffect(() => {
    checkAuth();
    getListHospital();
  }, []);

  function goBack() {
    navigate(-1);
  }

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

  function getListHospital() {
    var pageble = {
      page: 1,
      size: 10,
    };
    AdminService.getListHospital(token, pageble)
      .then((res) => {
        let listHospital = res.data.content;
        setListHospital(listHospital);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div>
      listHospital
      {listHospital.map((hospital, index) => (
        <div key={index}>
          <p>id: {hospital["id"]}</p>
          <p>name : {hospital["name"]}</p>
          <p>address : {hospital["address"]}</p>
          <span>--------------------</span>
        </div>
      ))}
      <input type="button" value="Back" onClick={() => goBack()} />
    </div>
  );
};
