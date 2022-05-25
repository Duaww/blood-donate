import { useNavigate } from "react-router-dom";
import { useLocation } from 'react-router-dom';
import React, {useEffect ,useState } from "react";
import "./ListPost.css";
import PostFindDonatorService from "./PostFindDonatorService";

export const ListPostComponent = () => {

    const [token, setToken] = useState("");
    const [listPost, setListPost] = useState([]);
    const navigate = useNavigate();
    const location = useLocation();
      
    useEffect(() => {
        const checkAuth = () => {
            const token = localStorage.getItem('token');
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
            size: 10
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


    function goBack() {
        navigate("/my-profile");
    }

    function getInforPost(key) {
        navigate(`/detail/${key}`);
    }

    function convertTimeStampToDate(timestamp) {
        return new Date(timestamp * 1000).toDateString();
    }

    return (
        <div>
            ListPost
            {listPost.map((post, index) => (
                <div className="row"  key={index}>
                    <p>
                        {post.content}
                    </p>
                    <span>{convertTimeStampToDate(post.deadlineRegister)}</span>
                    <br />
                     <input type="button" value="Detail" onClick={() => getInforPost(post.id)} />
                    <span>--------------------</span>
                </div>
            ))}
            <input type="button" value="Back" onClick={() => goBack()} />
        </div>
    );
}