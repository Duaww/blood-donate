import "./MyProfile.css";
import React, { useEffect, useState } from "react";
import MyProfileService from "./MyProfileService";
import { useNavigate } from "react-router-dom";
import LoginService from "../login/LoginService";
import { ListPostComponent } from "../post-find-donator/ListPostComponent";

export const MyProfileComponent = () => {
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [token, setToken] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const getMyProfile = () => {
      const token = localStorage.getItem("token");
      if (token == null) {
        navigate("/");
        return;
      }
      // const token = location["state"]["token"];
      setToken(token);
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

  function createPost() {
    navigate("/create-post");
  }

  function listDonated() {
    navigate("/donated");
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
    <div className="margin-bottom-30px">
      <h2 className="margin-top-15px">Tên bệnh viện : {name}</h2>
      <h2 className="margin-top-15px">Địa chỉ : {address}</h2>
      <div className="margin-top-15px margin-bottom-35px">
        <button
          className="choice-option margin-right-25px"
          onClick={() => createPost()}
        >
          Tạo bài đăng mới
        </button>
        <button
          className="choice-option margin-right-25px"
          onClick={() => listDonated()}
        >
          Danh sách người đã hiến máu
        </button>
        <button className="choice-option" onClick={() => logout()}>
          Đăng xuất
        </button>
      </div>
      <ListPostComponent />
    </div>
  );
};
