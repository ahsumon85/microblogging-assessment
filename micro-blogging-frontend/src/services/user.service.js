import axios from 'axios';
import authHeader from './auth-header';
import AccessTokenProvider from './access-token-provider'

const API_URL = 'http://localhost:8080/api/test/';

const API_BASE_URL = 'http://localhost:8082';

class UserService {

  getPublicContent() {
    var config = {
      method: 'get',
      url: API_BASE_URL + '/blogger/find/post',
      headers: { 
        'Authorization': 'Bearer ' + AccessTokenProvider.getAccessToken()
      }
    };
    return axios(config);
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
