
class AccessTokenProvider {

    getAccessToken(){
        const token = JSON.parse(localStorage.getItem('user'));
        return token.access_token;
    }

    authContentHeader() {
        const accessToken = JSON.parse(localStorage.getItem('token'));
            if (accessToken && accessToken.access_token) {
            return { Authorization: 'Bearer ' + accessToken.access_token,
                    'Content-Type': 'application/json'
            }; 
            } else {
            return {};
            }
    }
}

export default new AccessTokenProvider();