import React, { Component } from "react";

import UserService from "../services/user.service";

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      blogDTO: []
    };
  }

  componentDidMount() {
    UserService.getPublicContent().then(
      response => {
        console.log(JSON.stringify(response.data));
        this.setState({
          blogDTO: response.data
        });
      },
      error => {
        this.setState({
          blogDTO:
            (error.response && error.response.data) ||
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
          {/* <h3>{this.state.blogDTO}</h3> */}
        </header>
      </div>
    );
  }
}
