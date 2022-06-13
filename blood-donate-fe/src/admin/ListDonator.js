import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import AdminService from "./AdminService";
import TokenService from "../jwt/TokenService";
import { Role } from "../common/Role";
export const ListDonatorComponent = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const [listDonator, setListDonator] = useState([]);
  useEffect(() => {
    checkAuth();
    getListDonator();
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

  function getListDonator() {
    var pageble = {
      page: 1,
      size: 10,
    };
    AdminService.getListDonator(token, pageble)
      .then((res) => {
        let listDonator = res.data.content;
        setListDonator(listDonator);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function lockAccount(donatorId) {
    AdminService.lockAccount(token, donatorId)
      .then((res) => {
        setListDonator(
          listDonator.filter((donator) => donator["id"] != donatorId)
        );
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div>
      listDonator
      {listDonator.map((donator, index) => (
        <div key={index}>
          <p>name : {donator["name"]}</p>
          <p>birthday : {donator["birthday"]}</p>
          <p>phone : {donator["phone"]}</p>
          <p>email : {donator["email"]}</p>
          <p>blood : {donator["blood"]}</p>
          <p>idCard : {donator["idCard"]}</p>
          <p>number donated : {donator["numDonated"]}</p>
          <p>numOfNotDonated : {donator["numOfNotDonated"]}</p>
          <input
            type="button"
            value="Lock account"
            onClick={() => lockAccount(donator["id"])}
          />
          <span>--------------------</span>
        </div>
      ))}
      <input type="button" value="Back" onClick={() => goBack()} />
    </div>
  );
};
