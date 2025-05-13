// LOGIN
export const login = (props, d) => {
    if (d.username === 'user' && d.password === 'password') {
        localStorage.setItem('auth', d)
        props.history.push('/home');
        console.log('Login Success')
    }
    else {
        console.error('Login Failed')
    }
}

// LOGOUT
export const logout = () => {
    localStorage.removeItem('auth')
    console.log('Logout Success')
}

// LOGIN STATUS
export const isLogin = () => {
    if (localStorage.getItem('auth')) return true;
    return false;
}
