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
    const [updateAt, setUpdatedAt] = useState(0)
    const { key } = useParams();

    const navigate = useNavigate();

    useEffect(() => {
        const getDetailPost = () => {
            const token = localStorage.getItem('token');
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
                    setUpdatedAt(res.data["updateAt"])  
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
        };
        PostFindDonatorService.updatePost(token, key, formEdit)
            .then((res) => {
                console.log(res);
                navigate(-1);
                // window.alert("update success");
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
            })
            .catch((error) => {
                console.log(error);
            });
    }


    return (
        <div>
            <span>content</span>
            <textarea value={content} onChange={(e) => setContent(e.target.value)} id="" name="" rows="4" cols="50" required />
           
            <span>deadline</span>
            <textarea value={deadlineRegister} onChange={(e) => setDeadlineRegister(e.target.value)} id="" name="" rows="4" cols="5" required />
            
            <span>createAt</span>
            <textarea value={createAt} onChange={(e) => setCreateAt(e.target.value)} id="" name="" rows="4" cols="5" required />

            <span>updateAt</span>
            <textarea value={updateAt} onChange={(e) => setUpdatedAt(e.target.value)} id="" name="" rows="4" cols="5" required />
            
            
            <input type="button" value="Edit" onClick={() => editPost(key)} />
            <input type="button" value="Delete" onClick={() => deletePost(key)} />
            <input type="button" value="Back" onClick={() => goBack()} />
             
        </div>
    );
   
}