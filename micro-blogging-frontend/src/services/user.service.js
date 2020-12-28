import axios from 'axios';
import authHeader from './auth-header';
import AuthService from './auth.service'

const API_BASE_URL = 'http://localhost:8082';

class UserService {

  blogPostByBlogger(content){
    var data = JSON.stringify({"content":content});
    var token = this.getAcesstoken();
    var config = {
      method: 'post',
      url: API_BASE_URL + '/blogger/post/create',
      headers: { 
        'Content-Type': 'application/json', 
        'Authorization': 'Bearer ' + token
      },
      data : data
    };
    return axios(config);
  }

  getBLogerPostByBloggerName(){
    var username = AuthService.getCurrentUserName();
    return axios.get(API_BASE_URL + '/blogger/find/post?username=' + username, { headers: authHeader() });
  }

  getAcesstoken(){
    const accessToken = JSON.parse(localStorage.getItem('token'));
    return  accessToken.access_token 
  }

  getBloggerContent() {
    return axios.get(API_BASE_URL + '/blogger/find/post');
  }

  getUserBoard() {
    return axios.get(API_BASE_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_BASE_URL + '/blogger/find/post', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_BASE_URL + '/blogger/find/post', { headers: authHeader() });
  }
}

export default new UserService();
