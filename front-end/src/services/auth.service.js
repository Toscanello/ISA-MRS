import axios from 'axios'

const API_URL = 'http://localhost:9090/auth/'

class AuthService {
    login(user) {
        return axios
                .post(API_URL + 'login', {
                    email: user.username,
                    password: user.password
                })
                .then(response => {
                    if (response.data.accessToken) 
                        localStorage.setItem('user', JSON.stringify(response.data))
                    
                    return response.data
                })
    }

    logout() {
        localStorage.removeItem('user')
    }

    register(user) {
        //TODO: Registracija ako bude trebala
        //HAIKU O KOLEGI IZDAJICI
        //Ovo je trebao da je 
        //Nikolin deo, ali on je
        //nestao k'o suze na kiši
        console.log(user)
    }
}

export default new AuthService()