import axios from 'axios';
import authHeader from './auth-header';
import AccessTokenProvider from '../services/access-token-provider'

const API_BASE_URL = 'http://localhost:8082';

class UserService {

  blogPostByBlogger(content){
  
  }

  getBloggerContent() {
    return axios.get(API_BASE_URL + '/blogger/find/post');
  }

  getUserBoard() {
    return axios.get(API_BASE_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_BASE_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_BASE_URL + '/blogger/find/post', { headers: authHeader() });
  }
}

export default new UserService();
