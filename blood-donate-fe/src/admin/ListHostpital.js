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

  function createNewHospital() {
    navigate("/create-hospital");
  }

  return (
    <div>
      <h2>Danh sách bệnh viện</h2>
      <input
        className="button-create"
        type="button"
        value="Tạo tài khoản cho bệnh viện"
        onClick={() => createNewHospital()}
      />
      <div>
        <div className="body">
          <div class=""></div>
          <table className="table">
            <thead className="">
              <tr className="">
                <th className="">No</th>
                <th className="">Tên Bệnh viện</th>
                <th className="">Địa chỉ</th>
              </tr>
            </thead>
            <tbody className="">
              {listHospital.map((hospital, index) => (
                <tr key={index}>
                  <td className="">{index + 1}</td>
                  <td className="">{hospital["name"]}</td>
                  <td className="">{hospital["address"]}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};
