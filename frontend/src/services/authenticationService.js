import axios from "axios"

const baseUrl = 'http://localhost:8081/user'

const logIn = async (userData) => {
    return await axios.post(baseUrl + '/authenticate', userData)
}

export default logIn