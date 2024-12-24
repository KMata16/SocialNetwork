import axios from "axios";
import { useEffect, useState } from "react";
import GetComments from "../components/GetComments";
import GetUsername from "../components/GetUsername";
import CreatePost from "../components/CreatePost";
import CreateComment from "../components/CreateComment";
import Likes from "../components/Likes";

const Posts = () => {

    const [posts, setPosts] = useState([]);
    const url = "http://localhost:8080/posts";

    useEffect(() => {
        axios.get(url)
            .then(response => {
                setPosts(response.data)
            })
    }, [])


    return (
        <div>
            <h1>Posts</h1>
            <hr />
            <CreatePost />
            {
                posts.map((post) => (
                    <div key={post.postId}>
                        <h2>{post.postText}</h2>
                        <GetUsername userId={post.postedBy} />
                        <Likes likedMessage={post.postId} />
                        <br></br>
                        <CreateComment postId={post.postId} />
                        <GetComments postId={post.postId} />
                        <hr />
                    </div>
                ))
            }
        </div>
    )

}

export default Posts;