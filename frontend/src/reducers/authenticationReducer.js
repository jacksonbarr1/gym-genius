import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    isAuthenticated: false,
    user: null
};

const authenticationSlice = createSlice({
    name: 'userAuthentication',
    initialState: initialState,
    reducers: {
        loginSuccess(state, action) {
            state.isAuthenticated = true;
            state.user = action.payload.user;
            localStorage.setItem('isLoggedIn', 'true')
            localStorage.setItem('user', JSON.stringify(action.payload.user))
        },
        loginFailure(state, action) {
            state.isAuthenticated = false;
            state.user = null;
            state.error = action.payload;
            localStorage.setItem('isLoggedIn', 'false')
            localStorage.setItem('user', '')
        },
        logoutSuccess(state, action) {
            state.isAuthenticated = false;
            state.user = null;
            localStorage.setItem('isLoggedIn', 'false')
            localStorage.setItem('user', '')
        },
        updateUserSuccess(state, action) {
            state.user = action.payload.user;
        }
    }
});

export const { loginSuccess, loginFailure, logoutSuccess, updateUserSuccess } = authenticationSlice.actions
export default authenticationSlice.reducer