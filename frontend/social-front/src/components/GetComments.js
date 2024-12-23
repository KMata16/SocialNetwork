import { useEffect, useState } from "react";
import axios from "axios";
import GetUsername from "./GetUsername";

const GetComments = (props) => {

    const [comments, setComments] = useState([])

    useEffect(() => {
        axios.get(`http://localhost:8080/posts/${props.postId}/comments`)
            .then((res) => {
                setComments(res.data)
            })
    }, [])

    return (
        <div>
            {
                comments.map((comment) => (
                    <div key={comment.commentId}>
                        <h3>{comment.commentText}</h3>
                        <GetUsername userId={comment.postedByUser} />
                    </div>
                ))
            }
        </div>
    )

}

export default GetComments;