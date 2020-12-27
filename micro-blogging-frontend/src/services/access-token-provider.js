
export default class AccessTokenProvider {

    getAccessToken(){
        const token = JSON.parse(localStorage.getItem('user'));
        return token.access_token;
    }
}

