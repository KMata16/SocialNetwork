import axios from "axios";
import { useContext, useState, useEffect } from "react";
import { MyContext } from "../App";
import GetUsername from "../components/GetUsername";

const Follow = () => {

    const [contextValue] = useContext(MyContext)

    const [following, setFollowing] = useState([]);

    const [followers, setFollowers] = useState([])

    useEffect(() => {
        axios.get(`http://localhost:8080/users/${contextValue.accountId}/following`)
            .then(response => {
                setFollowing(response.data)
            })
    }, [])

    useEffect(() => {
        axios.get(`http://localhost:8080/users/${contextValue.accountId}/followers`)
            .then(response => {
                setFollowers(response.data)
            })
    }, [])

    return (
        <div>
            <h2>Following</h2>
            {
                following.map((follow, index) => (
                    <div key={index + 1}>
                        <GetUsername userId={follow.secondaryUser} />
                    </div>
                ))
            }
            <hr></hr>
            <h2>Followers</h2>
            {
                followers.map((follow, index) => (
                    <div key={index + 1}>
                        <GetUsername userId={follow.primaryUser} />
                    </div>
                ))
            }
        </div>
    )

}

export default Follow;