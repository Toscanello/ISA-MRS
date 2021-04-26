class TokenDecoder {
    parseJwt(token) {
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload);
    }

    getUserRole() {
        let user = localStorage.getItem('user')
        if (!user) {
            return 'ERR'
        }
        return this.parseJwt(user).role
    }
}

export default new TokenDecoder()