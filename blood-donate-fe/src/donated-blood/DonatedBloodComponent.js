import { useNavigate } from "react-router-dom";
import React, { useEffect, useState } from "react";
import "./DonatedBlood.css";
import DonatedBloodService from "./DonatedBloodService";

export const DonatedBloodComponent = () => {
  const [token, setToken] = useState("");
  const [listDonator, setListDonator] = useState([]);
  const [nameFilter, setNameFilter] = useState("");
  const [bloodFilter, setBloodFilter] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const checkAuth = () => {
      const token = localStorage.getItem("token");
      if (token == null) {
        navigate("/");
        return;
      }
      setToken(token);
      getListDonator(token);
    };
    checkAuth();
  }, []);

  function getListDonator(token) {
    const pageable = {
      page: 1,
      size: 10,
    };
    DonatedBloodService.getListDonatorSeflHospital(token, pageable)
      .then((res) => {
        let listDonator = res.data.content;
        setListDonator(listDonator);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function convertTimeStampToDate(timestamp) {
    return new Date(timestamp * 1000).toISOString().split("T")[0];
  }

  function goBack() {
    navigate(-1);
  }

  function search() {
    let pageable = {
      page: 1,
      size: 10,
    };
    let requestBody = {
      name: nameFilter,
      blood: bloodFilter.split(":"),
    };

    console.log(requestBody);

    DonatedBloodService.getListDonatedWithFilter(token, pageable, requestBody)
      .then((res) => {
        let listDonator = res.data.content;
        setListDonator(listDonator);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  return (
    <div>
      <h2>Danh sách người đã hiến máu tại bệnh viện</h2>
      <div className="div-filter">
        <label className="marign-right-5px">Tên người hiến máu:</label>
        <input
          className="marign-right-15px text-search"
          type="text"
          id=""
          name=""
          placeholder="Nhập tên muốn tìm kiếm"
          value={nameFilter}
          onChange={(e) => setNameFilter(e.target.value)}
        />
        <label className="marign-right-5px">Blood group :</label>

        <select
          className="marign-right-15px select-option"
          id="blood"
          value={bloodFilter}
          onChange={(e) => setBloodFilter(e.target.value)}
        >
          <option className="marign-right-15px" value="O:A:B:AB" selected>
            All
          </option>
          <option value="O">O</option>
          <option value="A">A</option>
          <option value="B">B</option>
          <option value="AB">AB</option>
        </select>

        <input
          className="search-button marign-right-15px"
          type="button"
          value="Search"
          onClick={() => search()}
        />
      </div>
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
                <th className="">Actions</th>
              </tr>
            </thead>
            <tbody className="">
              {listDonator.map((donator, index) => (
                <tr key={index}>
                  <td className="">{index}</td>

                  <td className="">{donator["donatorInfoDTO"]["name"]}</td>
                  <td className="">
                    {convertTimeStampToDate(
                      donator["donatorInfoDTO"]["birthday"]
                    )}
                  </td>
                  <td className="">{donator["donatorInfoDTO"]["blood"]}</td>
                  <td className="">{donator["donatorInfoDTO"]["idCard"]}</td>
                  <td className="">{donator["donatorInfoDTO"]["email"]}</td>
                  <td className="">{donator["donatorInfoDTO"]["phone"]}</td>
                  <td className="">
                    <button type="" className="update-btn">
                      Đã hiến máu
                    </button>
                    <button type="" className="delete-btn">
                      Chưa hiến máu
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <div className="back">
        <input
          className="button-back"
          type="button"
          value="Trở về"
          onClick={() => goBack()}
        />
      </div>
    </div>
  );
};
