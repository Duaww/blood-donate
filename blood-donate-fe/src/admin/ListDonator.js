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

  function convertTimeStampToDate(timestamp) {
    return new Date(timestamp * 1000).toISOString().split("T")[0];
  }

  return (
    <div>
      <h2>Danh sách người đã hiến máu tại bệnh viện</h2>
      <div>
        <div className="body">
          <div class=""></div>
          <table className="table">
            <thead className="">
              <tr className="">
                <th className="">No</th>
                <th className="">Tên</th>
                <th className="">Ngày sinh</th>
                <th className="">Nhóm máu</th>
                <th className="">Sô căn cước</th>
                <th className="">Email</th>
                <th className="">Số điện thoại</th>
                <th className="">Số lần hiến máu</th>
                <th className="">Số lần bỏ hiến máu</th>
                <th className="">Khóa tài khoản</th>
              </tr>
            </thead>
            <tbody className="">
              {listDonator.map((donator, index) => (
                <tr key={index}>
                  <td className="">{index + 1}</td>

                  <td className="">{donator["name"]}</td>
                  <td className="">
                    {convertTimeStampToDate(donator["birthday"])}
                  </td>
                  <td className="">{donator["blood"]}</td>
                  <td className="">{donator["idCard"]}</td>
                  <td className="">{donator["email"]}</td>
                  <td className="">{donator["phone"]}</td>
                  <td className="">{donator["numDonated"]}</td>
                  <td className="">{donator["numOfNotDonated"]}</td>
                  <td className="">
                    <input
                      className="delete-btn"
                      type="button"
                      value="Khóa"
                      onClick={() => lockAccount(donator["id"])}
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};
