import axios from "axios"

const baseUrl = 'http://localhost:8081/user'

const logIn = async (userData) => {
    return await axios.post(baseUrl + '/authenticate', userData)
}

const register = async (registrationData) => {
    return await axios.post(baseUrl + '/register', registrationData)
}

export {
    logIn,
    register
}