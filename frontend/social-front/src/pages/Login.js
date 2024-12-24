import axios from "axios";
import { useState } from "react";
import { MyContext } from "../App";
import { useContext } from "react";

const Login = () => {

    const [contextValue, setContextValue] = useContext(MyContext)

    const [user, setUser] = useState({
        username: "",
        password: ""
    });

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }

    function handleSubmit(event) {
        event.preventDefault();
        axios.post("http://localhost:8080/login", user)
            .then(res => {
                setContextValue({ ...contextValue, username: res.data.username, accountId: res.data.accountId })
            })
            .catch(error => {
                alert("Wrong Credentials")
            })
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h2>Login</h2>
                <hr />
                <h3>Username: </h3>
                <input type="text" name="username" value={user.username} onChange={handleChange} />
                <h3>Password:</h3>
                <input type="password" name="password" value={user.password} onChange={handleChange} />
                <br />
                <br />
                <button>Login</button>
            </form>
        </div>
    );
}

export default Login;