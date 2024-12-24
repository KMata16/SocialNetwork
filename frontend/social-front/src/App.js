import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import React, { useState } from "react";
import Welcome from "./pages/Welcome";
import Navbar from "./components/Navbar";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Posts from "./pages/Posts";
import UserProfile from "./pages/UserProfile";
import Follow from "./pages/Follow";

export const MyContext = React.createContext();

function App() {

  const [contextValue, setContextValue] = useState({
    username: "",
    accountId: 0
  });

  return (
    <MyContext.Provider value={[contextValue, setContextValue]}>
      <Router>
        <div>
          <Navbar />
        </div>
        <Routes>
          <Route index element={<Welcome />} />
          <Route path="/welcome" element={<Welcome />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/posts" element={<Posts />} />
          <Route path="/profile" element={<UserProfile />} />
          <Route path="/follow" element={<Follow />} />
        </Routes>
      </Router>
    </MyContext.Provider>
  );
}

export default App;
