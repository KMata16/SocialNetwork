import { Link } from "react-router-dom";
import './navbar.css';

const Navbar = () => {
    return (
        <div className="navbar">
            <h1>Messages</h1>
            <div className="links">
                <Link to="/welcome">Welcome</Link>
                <Link to="/login">Login</Link>
                <Link to="/register">Register</Link>
                <Link to="/posts">Posts</Link>
                <Link to="/follow">Follow</Link>
                <Link to="/profile">Profile</Link>
            </div>
        </div>
    );
}

export default Navbar;