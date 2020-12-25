import axios from "axios";

const API_URL = "http://localhost:8082/oauth/token";

class AuthService {
  login(username, password) {

    var FormData = require('form-data');
    var data = new FormData();
    data.append('username', username);
    data.append('password', password);
    data.append('grant_type', 'password');

    var config = {
      method: 'post',
      url: API_URL,
      headers: { 
        'Authorization': 'Basic bW9iaWxlOnBpbg=='
      },
      data : data
    };
    return axios(config)
        .then(function (response){
        if (response.data.access_token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
      });
  }

  getAccessToken(){
    return JSON.parse(localStorage.getItem('user'));
}

  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password) {
    return axios.post(API_URL + "signup", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));
  }
}

export default new AuthService();
