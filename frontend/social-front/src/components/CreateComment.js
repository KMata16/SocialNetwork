import axios from "axios";
import { useContext, useState } from "react";
import { MyContext } from "../App";

const CreateComment = (props) => {

    const [contextValue] = useContext(MyContext)

    const [comment, setComment] = useState("")

    const handleChange = (event) => {
        setComment(event.target.value)
    }

    function handleSubmit(event) {
        event.preventDefault()
        axios.post(`http://localhost:8080/posts/${props.postId}/comments`, {
            "commentText": comment,
            "postedByUser": contextValue.accountId,
            "originalPost": props.postId
        })
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <textarea rows={5} cols={40} onChange={handleChange}></textarea>
                <br />
                <button>Submit Comment</button>
            </form>
        </div>
    )
}

export default CreateComment;