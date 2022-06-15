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
    <div>
      <span>{name}</span>
      <span>{address}</span>
      <input
        type="button"
        value="Create new post"
        onClick={() => createPost()}
      />
      <input type="button" value="List Donated" onClick={() => listDonated()} />
      <input type="button" value="Logout" onClick={() => logout()} />
      <ListPostComponent />
    </div>
  );
};
