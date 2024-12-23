import { Link } from "react-router-dom";

const Navbar = () => {
    return (
        <div>
            <h1>Navbar Test</h1>
            <div>
                <Link to="/welcome">Welcome</Link>
                <Link to="/login">Login</Link>
                <Link to="/register">Register</Link>
                <Link to="/posts">Posts</Link>
            </div>
        </div>
    );
}

export default Navbar;