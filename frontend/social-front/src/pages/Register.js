import { useState } from "react";
import axios from "axios";

const Register = () => {

    const [user, setUser] = useState({
        username: "",
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }

    function handleSubmit(event) {
        // event.preventDefault();
        axios.post("http://localhost:8080/register", user)
        // .then(res => console.log(res.data))
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <h3>Email:</h3>
                <input type="email" name="email" value={user.email} onChange={handleChange} />
                <h3>Username:</h3>
                <input type="text" name="username" value={user.username} onChange={handleChange} />
                <h3>Password:</h3>
                <input type="password" name="password" value={user.password} onChange={handleChange} />
                <br />
                <br />
                <button>Register</button>
            </form>
        </div >
    );
}

export default Register;