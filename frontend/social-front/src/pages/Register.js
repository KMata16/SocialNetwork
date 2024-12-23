import { useState } from "react";

const Register = () => {

    const [user, setUser] = useState({
        name: "",
        email: "",
        password: ""
    });

    const handleChange = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value })
    }

    return (
        <div>
            <h3>Email:</h3>
            <input type="email" name="email" value={user.email} onChange={handleChange} />
            <h3>Username:</h3>
            <input type="text" name="name" value={user.name} onChange={handleChange} />
            <h3>Password:</h3>
            <input type="password" name="password" value={user.password} onChange={handleChange} />
        </div >
    );
}

export default Register;