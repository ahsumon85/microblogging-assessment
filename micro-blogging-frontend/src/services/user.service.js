import axios from 'axios';
import authHeader from './auth-header';
import AuthService from './auth.service'

const API_BASE_URL = 'http://localhost:8082';

class UserService {

  blogPostByBlogger(content, contentTitle){
    var data = JSON.stringify({"content":content, "contentTitle":contentTitle});
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
    return axios.get(API_BASE_URL + '/blogger/find/post/by-name?username=' + username, { headers: authHeader() });
  }

  getAcesstoken(){
    const accessToken = JSON.parse(localStorage.getItem('token'));
    return  accessToken.access_token 
  }
  getAdminContent() {
    return axios.get(API_BASE_URL + '/blogger/find/post?status=' + 0);
  }

  getBloggerContent() {
    return axios.get(API_BASE_URL + '/blogger/find/post?status=' + 1);
  }

  deletePost(blogId){
   return axios.delete('http://localhost:8082/blogger/delete/post/' + blogId, { headers: authHeader() });
  }

  upVoteBloggerPost(blogId){
    var userId = AuthService.getCurrentUserId();
    var token = this.getAcesstoken();
    var data = JSON.stringify({"upvote":1,"blog":{"blogId": blogId },"user":{"id": userId}});
    var config = {
      method: 'post',
      url: API_BASE_URL + '/blogger/vote/post',
      headers: { 
        'Content-Type': 'application/json', 
        'Authorization': 'Bearer ' + token
      },
      data : data
    };
    return axios(config);
  }

  downVoteBloggerPost(blogId){
    var userId = AuthService.getCurrentUserId();
    var token = this.getAcesstoken();
    var data = JSON.stringify({"downvote":1,"blog":{"blogId": blogId },"user":{"id": userId}});
    var config = {
      method: 'post',
      url: API_BASE_URL + '/blogger/vote/post',
      headers: { 
        'Content-Type': 'application/json', 
        'Authorization': 'Bearer ' + token
      },
      data : data
    };
    return axios(config);
  }
  approvedPaddingVote(voteId){
    var token = this.getAcesstoken();
    var data = JSON.stringify({"upAndDownVoteId":voteId});
    var config = {
      method: 'post',
      url: API_BASE_URL + '/blogger/approved/vote',
      headers: { 
        'Content-Type': 'application/json', 
        'Authorization': 'Bearer ' + token
      },
      data : data
    };
    return axios(config);
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
