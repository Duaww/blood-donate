import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./RegisterToDonate.css";
import RegisterToDonateService from "./RegisterToDonateService";
import DonatedBloodService from "../donated-blood/DonatedBloodService";

export const RegisterToDonateComponent = () => {
  const navigate = useNavigate();
  const [token, setToken] = useState("");
  const [listRegister, setListRegister] = useState([]);
  const [nameFilter, setNameFilter] = useState("");
  const [bloodFilter, setBloodFilter] = useState("O:A:B:AB");
  const [idCardFilter, setIdCardFilter] = useState("");

  const { key } = useParams();

  useEffect(() => {
    const checkAuth = () => {
      const token = localStorage.getItem("token");
      if (token == null) {
        navigate("/");
        return;
      }
      setToken(token);
      getListRegisterDonate(token, key);
    };
    checkAuth();
  }, []);

  function getListRegisterDonate(token, key) {
    const params = {
      page: 1,
      size: 10,
    };
    RegisterToDonateService.getListRegisterDonateByPostId(token, key, params)
      .then((res) => {
        // console.log(res.data)
        let listRegister = res.data.content;
        setListRegister(listRegister);
      })
      .catch((error) => {
        console.log(error);
      });
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
      idCard: idCardFilter,
    };

    console.log(requestBody);

    RegisterToDonateService.getListRegisterWithFilter(
      token,
      key,
      pageable,
      requestBody
    )
      .then((res) => {
        let listRegister = res.data.content;
        setListRegister(listRegister);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function convertTimeStampToDate(timestamp) {
    return new Date(timestamp * 1000).toISOString().split("T")[0];
  }

  function confirmDonated(idDonator) {
    let requestBody = {
      idDonator: idDonator,
      idPost: key,
    };
    DonatedBloodService.confirmDonated(token, requestBody)
      .then((res) => {
        document.getElementById(idDonator).style.backgroundColor = "grey";
        document.getElementById(idDonator).disabled = true;
        window.alert("Xác nhận thành công");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  // function updateNumNotDonated(idDonator) {
  //   DonatedBloodService.updateNumNotDonated(token, idDonator)
  //     .then((res) => {
  //       window.alert("UPDATE SUCESS");
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }

  return (
    <div>
      <h2>Danh sách người đã đăng kí tham gia hiến máu</h2>
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
        <label className="marign-right-5px">Số căn cước người hiến máu:</label>
        <input
          className="marign-right-15px text-search"
          type="text"
          id=""
          name=""
          placeholder="Nhập số căn cước"
          value={idCardFilter}
          onChange={(e) => setIdCardFilter(e.target.value)}
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
                <th className="">Thời gian đăng kí</th>
                <th className="">Xác nhận hiến máu</th>
              </tr>
            </thead>
            <tbody className="">
              {listRegister.map((donator, index) => (
                <tr key={index}>
                  <td className="">{index + 1}</td>

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
                    {convertTimeStampToDate(donator["timeRegister"])}
                  </td>
                  <td className="">
                    <button
                      id={donator["donatorInfoDTO"]["id"]}
                      disabled={donator["isDonated"] === true}
                      style={{
                        backgroundColor: donator["isDonated"]
                          ? "grey"
                          : "#1aa3ff",
                      }}
                      type=""
                      className="update-btn"
                      onClick={() =>
                        confirmDonated(donator["donatorInfoDTO"]["id"])
                      }
                    >
                      Đã hiến máu
                    </button>
                    {/* <button
                      type=""
                      className="delete-btn"
                      onClick={() =>
                        updateNumNotDonated(donator["donatorInfoDTO"]["id"])
                      }
                    >
                      Chưa hiến máu
                    </button> */}
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
