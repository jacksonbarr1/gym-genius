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
            state.user = action.payload;
        },
        loginFailure(state, action) {
            state.isAuthenticated = false;
            state.user = null;
            state.error = action.payload;
        },
        logoutSuccess(state, action) {
            state.isAuthenticated = false;
            state.user = null;
        },
        updateUserSuccess(state, action) {
            state.user = action.payload;
        }
    }
});

export const { loginSuccess, loginFailure, logoutSuccess, updateUserSuccess } = authenticationSlice.actions
export default authenticationSlice.reducer