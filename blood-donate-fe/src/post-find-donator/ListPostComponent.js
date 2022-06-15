import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import React, { useEffect, useState } from "react";
import "./ListPost.css";
import PostFindDonatorService from "./PostFindDonatorService";

export const ListPostComponent = () => {
  const [token, setToken] = useState("");
  const [listPost, setListPost] = useState([]);
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const checkAuth = () => {
      const token = localStorage.getItem("token");
      if (token == null) {
        navigate("/");
        return;
      }
      setToken(token);
      getListMyPost(token);
    };
    checkAuth();
  }, []);

  function getListMyPost(token) {
    const params = {
      page: 1,
      size: 10,
    };
    PostFindDonatorService.getListMyPost(token, params)
      .then((res) => {
        // console.log(res.data.content);
        let listPost = res.data.content;
        setListPost(listPost);
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function getInforPost(key) {
    navigate(`/detail/${key}`);
  }

  function convertTimeStampToDate(timestamp) {
    return new Date(timestamp * 1000).toISOString().split("T")[0];
  }

  return (
    <div>
      <div>
        Danh sách bài đăng
        {listPost.map((post, index) => (
          // <div className="row text-align-center" key={index}>
          //   <p>{post.content}</p>
          //   <span>{convertTimeStampToDate(post.deadlineRegister)}</span>
          //   <br />
          //   <input
          //     type="button"
          //     value="Detail"
          //     onClick={() => getInforPost(post.id)}
          //   />
          // </div>
          <div class="blog-card">
            <div class="description">
              <h1>Bài đăng thông báo sự kiện hiến máu</h1>
              <h2>
                Đăng tải vào ngày : {convertTimeStampToDate(post.createAt)}
              </h2>
              <h2>
                Hạn đăng ký đến ngày :{" "}
                {convertTimeStampToDate(post.deadlineRegister)}
              </h2>
              <p>{post.content}</p>
              <p class="read-more">
                <a href="" onClick={() => getInforPost(post.id)}>
                  Detail
                </a>
              </p>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
