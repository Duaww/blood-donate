import "./CreatePost.css";
import { useNavigate } from "react-router-dom";
import { useLocation } from 'react-router-dom';
import React, {useEffect ,useState } from "react";
import PostFindDonatorService from "./PostFindDonatorService";


export const CreatePostComponent = () => {

    const [token, setToken] = useState("");
    const [contentPost, setContentPost] = useState("");
    const [deadline, setDeadLine] = useState(0);
    const navigate = useNavigate();
    const location = useLocation();
    useEffect(() => {
        const checkAuth = () => {
            if (location["state"] == null) {
                navigate("/");
                return;
            }
            setToken(location["state"]["token"]);
        };
        checkAuth();
    }, []);

    function goBack() {
        navigate(-1);
    }

    function createNewPost() {

        if (contentPost == '' || deadline == 0) {
            window.location.reload();
            return;
        }
        let postInfo = { content: contentPost, deadlineRegister: deadline };
        PostFindDonatorService.createNewPost(token, postInfo)
            .then((res) => {
                navigate("/list-post", { state: { token } });
            })
            .catch((error) => {
                console.log(error);
            });
    }


    return (
        <div>
            CreatePost
            <div>
                <label>Content</label>
                <textarea value={contentPost} onChange={(e) => setContentPost(e.target.value)} id="" name="" rows="4" cols="50" required />
                <label>Deadline Register</label>
                <input type="text" name="deadline" value={deadline} onChange={(e) => setDeadLine(e.target.value)} required />
                <input type="button" value="Create" onClick={() => createNewPost()}/>
            </div>
            <input type="button" value="Back" onClick={() => goBack()} />
        </div>
    );
}