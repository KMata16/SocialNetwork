import axios from "axios";
import { useEffect, useState } from "react";
import GetComments from "../components/GetComments";
import GetUsername from "../components/GetUsername";

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
            {
                posts.map((post) => (
                    <div key={post.postId}>
                        <h2>{post.postText}</h2>
                        <GetUsername userId={post.postedBy} />
                        <GetComments postId={post.postId} />
                        <hr />
                    </div>
                ))
            }
        </div>
    )

}

export default Posts;