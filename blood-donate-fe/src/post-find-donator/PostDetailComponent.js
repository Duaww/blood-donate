import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./PostDetail.css";
import PostFindDonatorService from "./PostFindDonatorService";

export const PostDetailComponent = () => {
  const [token, setToken] = useState("");
  const [content, setContent] = useState("");
  const [deadlineRegister, setDeadlineRegister] = useState(0);
  const [createAt, setCreateAt] = useState(0);
  const [updateAt, setUpdatedAt] = useState(0);
  const { key } = useParams();

  const navigate = useNavigate();

  useEffect(() => {
    const getDetailPost = () => {
      const token = localStorage.getItem("token");
      if (token == null) {
        navigate("/");
        return;
      }
      setToken(token);
      PostFindDonatorService.getPostDetail(token, key)
        .then((res) => {
          setContent(res.data["content"]);
          setDeadlineRegister(res.data["deadlineRegister"]);
          setCreateAt(res.data["createAt"]);
          setUpdatedAt(res.data["updateAt"]);
        })
        .catch((error) => {
          console.log(error);
        });
    };
    getDetailPost();
  }, []);

  function goBack() {
    navigate(-1);
  }

  function editPost(key) {
    let formEdit = {
      content: content,
      deadlineRegister: deadlineRegister,
    };
    PostFindDonatorService.updatePost(token, key, formEdit)
      .then((res) => {
        console.log(res);
        navigate(-1);
        window.alert("Chỉnh sửa thành công");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function deletePost(key) {
    PostFindDonatorService.deletePost(token, key)
      .then((res) => {
        console.log(res);
        navigate(-1);
        window.alert("Xóa thành công");
      })
      .catch((error) => {
        console.log(error);
      });
  }

  function getListRegister(key) {
    navigate(`/register-post/${key}`);
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
        <div id="edit-post">
          <h3>Chi tiết bài đăng</h3>
          <fieldset>
            <h4>Thời gian đăng bài: {convertTimeStampToDate(createAt)}</h4>
            <h4>
              Thời gian chỉnh sửa bài đăng: {convertTimeStampToDate(updateAt)}
            </h4>
          </fieldset>
          <fieldset>
            <h4>Hạn đăng kí:</h4>
            <input
              type="date"
              value={convertTimeStampToDate(deadlineRegister)}
              onChange={(e) =>
                setDeadlineRegister(convertDateToTimeStamp(e.target.value))
              }
              required
            />
          </fieldset>
          <fieldset>
            <h4>Nội dung bài đăng:</h4>
            <textarea
              placeholder="Viết thông báo ở đây"
              tabIndex="5"
              value={content}
              onChange={(e) => setContent(e.target.value)}
              required
            ></textarea>
          </fieldset>
          <fieldset className="d-flex">
            <button
              className="mr-15px"
              onClick={() => editPost(key)}
              id="edit-post-submit"
            >
              Chỉnh sửa bài viết
            </button>
            <button onClick={() => deletePost(key)} id="edit-post-submit">
              Xóa bài viết
            </button>
          </fieldset>
          <fieldset className="d-flex">
            <button
              className="mr-15px"
              onClick={() => getListRegister(key)}
              id="edit-post-submit"
            >
              danh sách người đăng kí
            </button>
            <button id="edit-post-submit" onClick={() => goBack()}>
              Trở về
            </button>
          </fieldset>
        </div>
      </div>
    </div>
  );
};
