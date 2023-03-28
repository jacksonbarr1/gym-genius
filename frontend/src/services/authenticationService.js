import axios from "axios"

const baseUrl = 'http://localhost:8081/user'

const logIn = async (userData) => {
    const response = await axios.post(baseUrl + '/authenticate', userData)
        .catch(error => console.error(error))
    return response.data
}

export default {
    logIn
}