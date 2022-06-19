import "./CreatePost.css";
import { useNavigate } from "react-router-dom";
import React, { useEffect, useState } from "react";
import PostFindDonatorService from "./PostFindDonatorService";

export const CreatePostComponent = () => {
  const [token, setToken] = useState("");
  const [contentPost, setContentPost] = useState("");
  const [deadline, setDeadLine] = useState(0);
  const navigate = useNavigate();
  useEffect(() => {
    const checkAuth = () => {
      const token = localStorage.getItem("token");
      if (token == null) {
        navigate("/");
        return;
      }
      setToken(token);
    };
    checkAuth();
  }, []);

  function goBack() {
    navigate(-1);
  }

  function createNewPost() {
    if (contentPost == "" || deadline == 0) {
      window.alert("Xin hãy nhập đủ thông tin");
      return;
    }
    let postInfo = { content: contentPost, deadlineRegister: deadline };
    PostFindDonatorService.createNewPost(token, postInfo)
      .then((res) => {
        navigate("/my-profile");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function convertDateToTimeStamp(value) {
    return Date.parse(value) / 1000;
  }

  function convertTimeStampToDate(value) {
    let date = new Date(value * 1000);
    return date.toISOString().split("T")[0];
  }

  return (
    <div>
      <div className="container">
        <div id="contact">
          <h3>Tạo bài đăng mới</h3>
          <fieldset>
            <h4>Hạn đăng kí:</h4>
            <input
              type="date"
              value={convertTimeStampToDate(deadline)}
              onChange={(e) =>
                setDeadLine(convertDateToTimeStamp(e.target.value))
              }
              required
            />
          </fieldset>
          <fieldset>
            <textarea
              placeholder="Viết thông báo ở đây"
              tabIndex="5"
              value={contentPost}
              onChange={(e) => setContentPost(e.target.value)}
              required
            ></textarea>
          </fieldset>
          <fieldset>
            <button onClick={() => createNewPost()} id="contact-submit">
              Đăng bài
            </button>
          </fieldset>
          <fieldset>
            <button id="contact-submit" onClick={() => goBack()}>
              Trở về
            </button>
          </fieldset>
        </div>
      </div>
    </div>
  );
};
