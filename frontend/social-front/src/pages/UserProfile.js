import axios from "axios";
import { useContext, useState } from "react";
import { MyContext } from "../App";

const UserProfile = () => {

    const [contextValue] = useContext(MyContext)

    const [username, setUsername] = useState("");

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("")

    function handleSubmitUsername(event) {
        event.preventDefault()
        axios.put(`http://localhost:8080/users/${contextValue.accountId}/username`, {
            "username": username
        })
    }

    function handleSubmitEmail(event) {
        event.preventDefault()
        axios.put(`http://localhost:8080/users/${contextValue.accountId}/email`, {
            "email": email
        })
    }

    function handleSubmitPassword(event) {
        event.preventDefault()
        axios.put(`http://localhost:8080/users/${contextValue.accountId}/password`, {
            "password": password
        })
    }

    const handleEmail = (event) => {
        setEmail(event.target.value)
    }

    const handleUsername = (event) => {
        setUsername(event.target.value)
    }

    const handlePassword = (event) => {
        setPassword(event.target.value)
    }

    return (
        <div>
            <h1>User Profile</h1>
            <hr />
            <form onSubmit={handleSubmitUsername}>
                <h3>Change Username: </h3>
                <input type="text" name="username" value={username} onChange={handleUsername}></input>
                <br />
                <button>Change</button>
                <br />
            </form>
            <form onSubmit={handleSubmitEmail}>
                <h3>Change Email: </h3>
                <input type="email" name="email" value={email} onChange={handleEmail}></input>
                <br />
                <button>Change</button>
                <br />
            </form>
            <form onSubmit={handleSubmitPassword}>
                <h3>Change Password: </h3>
                <input type="password" name="password" value={password} onChange={handlePassword}></input>
                <br />
                <button>Change</button>
                <br />
            </form>
        </div>
    )
}

export default UserProfile;