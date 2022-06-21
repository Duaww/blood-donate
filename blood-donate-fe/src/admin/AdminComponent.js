import "./Admin.css";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import LoginService from "../login/LoginService";
import TokenService from "../jwt/TokenService";
import { Role } from "../common/Role";
import { ListHospitalComponent } from "./ListHostpital";
import { ListDonatorComponent } from "./ListDonator";

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

  const [currentTab, setCurrentTab] = useState("1");
  const tabs = [
    {
      id: 1,
      tabTitle: "Danh sách bệnh viện",
      content: <ListHospitalComponent />,
    },
    {
      id: 2,
      tabTitle: "Danh sách người hiến máu",
      content: <ListDonatorComponent />,
    },
  ];

  const handleTabClick = (e) => {
    setCurrentTab(e.target.id);
  };

  return (
    <div className="container1">
      <div className="tabs">
        {tabs.map((tab, i) => (
          <button
            key={i}
            id={tab.id}
            disabled={currentTab === `${tab.id}`}
            onClick={handleTabClick}
            className="fs-25px"
          >
            {tab.tabTitle}
          </button>
        ))}
      </div>
      <div className="content">
        {tabs.map((tab, i) => (
          <div key={i}>
            {currentTab === `${tab.id}` && (
              <div>
                <p>{tab.content}</p>
              </div>
            )}
          </div>
        ))}
      </div>
      <div className="back">
        <input
          className="button-back"
          type="button"
          value="Đăng xuất"
          onClick={() => logout()}
        />
      </div>
    </div>
  );
};
