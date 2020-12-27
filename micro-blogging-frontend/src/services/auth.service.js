import axios from "axios";

const API_BASE_URL = "http://localhost:8082";

class AuthService {
  
  login(username, password) {
    var FormData = require('form-data');
    var data = new FormData();
    data.append('username', username);
    data.append('password', password);
    data.append('grant_type', 'password');
    var config = {
      method: 'post',
      url: API_BASE_URL + '/oauth/token',
      headers: { 
        'Authorization': 'Basic bW9iaWxlOnBpbg=='
      },
      data : data
    }
    return axios(config).then(function (response){
        if (response.data.access_token) {
          localStorage.setItem("token", JSON.stringify(response.data));
        }
        return response.data;
      });   
  }


   getUserInfoByUsername(username){
    return axios.get(API_BASE_URL + '/user/find/user/info?username=' + username)
        .then(function (response) {
          if (response.data.username) {
            localStorage.setItem("user", JSON.stringify(response.data));
          }
        });
  }

  logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
  }

  register(username, email, password) {
    let userDTO = {"username": username, "password": password, "email": email};
    var data = JSON.stringify(userDTO);
    var config = {
      method: 'post',
      url: API_BASE_URL + '/user/sing-up',
      headers: { 
        'Content-Type': 'application/json'
      },
      data : data
    };
    return axios(config);
  }

  
  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }

  getAccessToken(){
    return JSON.parse(localStorage.getItem('token'));
  }
}

export default new AuthService();
