import axios from "axios";
import { useContext, useState } from "react";
import { MyContext } from "../App";

const CreatePost = () => {

    const [contextValue] = useContext(MyContext)

    const [postText, setPostText] = useState("")

    const handleChange = (event) => {
        setPostText(event.target.value)
    }

    function handleSubmit(event) {
        event.preventDefault()
        axios.post("http://localhost:8080/posts", {
            "postedBy": contextValue.accountId,
            "postText": postText
        })
            .then(res => console.log(res.data))
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <textarea rows={5} cols={40} onChange={handleChange}></textarea>
                <br />
                <br />
                <button>Submit Post</button>
            </form>
        </div>
    );
}

export default CreatePost;