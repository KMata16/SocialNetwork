import { useEffect, useState } from "react";
import axios from "axios";

const GetUsername = (props) => {

    const [users, setUsers] = useState([])

    useEffect(() => {
        axios.get(`http://localhost:8080/users/${props.userId}`)
            .then((res) => {
                setUsers(res.data)
            })
    }, [])

    return (
        <div>
            {
                <h4>{users}</h4>
            }
        </div>
    )
}

export default GetUsername;