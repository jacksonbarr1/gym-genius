import {useEffect} from "react";
import {useNavigate} from "react-router-dom";
import {useDispatch} from "react-redux";
import {logoutSuccess} from "../reducers/authenticationReducer";


const LogOut = () => {
    const dispatch = useDispatch()
    const navigate = useNavigate()
    useEffect(() => {
        dispatch(logoutSuccess())

        const timeoutId = setTimeout(() => {
            navigate('/', {replace: true})
        }, 2500)

        return () => clearTimeout(timeoutId)
    }, [navigate])

    return (
        <div>
            <p>Logged out</p>
        </div>
    )
}

export default LogOut;