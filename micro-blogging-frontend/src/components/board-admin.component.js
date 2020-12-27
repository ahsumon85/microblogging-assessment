import React, { Component } from "react";

import UserService from "../services/user.service";

export default class BoardAdmin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      blogList: []
    };
  }

  componentDidMount() {
    UserService.getAdminBoard().then(
      response => {
        console.log(JSON.stringify(response.data));
        this.setState({
          blogList: response.data
        });
      },
      error => {
        this.setState({
          blogList:
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          {/* <h3>{this.state.blogList.content}</h3> */}
        </header>
      </div>
    );
  }
}
