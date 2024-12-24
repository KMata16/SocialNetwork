import axios from "axios";
import { useEffect, useState } from "react";

const Likes = (props) => {

    const [currentLikes, setCurrentLikes] = useState();

    useEffect(() => {
        axios.get(`http://localhost:8080/posts/${props.likedMessage}/like`)
            .then(response => {
                setCurrentLikes(response.data.numberOfLikes)
            })
    })

    function handleClick(event) {
        axios.post(`http://localhost:8080/posts/${props.likedMessage}/like`)
    }

    return (
        <div>
            <h5>Likes: {currentLikes}</h5>
            <button onClick={handleClick}>Like</button>
        </div>
    )
}

export default Likes;