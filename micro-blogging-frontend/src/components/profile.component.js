import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import AuthService from "../services/auth.service";

export default class Profile extends Component {
  constructor(props) {
    super(props);

    this.state = {
      redirect: null,
      userReady: false,
      currentUser: {
                     username: ""
      },
      accessToken: {
                    access_token: ""
      }
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    const accessToken = AuthService.getAccessToken();
    if (!currentUser) this.setState({ redirect: "/home" });
    this.setState({ 
      currentUser: currentUser,
      accessToken: accessToken,
      userReady: true 
    })
  }

  render() {
    if (this.state.redirect) {
      return <Redirect to={this.state.redirect} />
    }

    const { currentUser, accessToken } = this.state;

    return (
      <div className="container">
        {(this.state.userReady) ?
        <div>
        <header className="jumbotron">
          <h3>
            <strong>{currentUser.username}</strong> Profile
          </h3>
        </header>
        <p>
          <strong>Token:</strong>{"  "}
          {accessToken.access_token}
          {/* {currentUser.accessToken.substring(0, 20)} ...{" "}
          {currentUser.accessToken.substr(currentUser.accessToken.length - 20)} */}
        </p>
        <p>
          <strong>Id:</strong>{"  "}
          {currentUser.username}
        </p>
        <p>
          <strong>Email:</strong>{"  "}
          {currentUser.email}
        </p>
        <strong>Authorities:</strong> {"  "}
        
          {/* {currentUser.roles &&
            currentUser.roles.map((role, index) => <li key={index}>{role}</li>)} */}
            {currentUser.role.name}
       
      </div>: null}
      </div>
    );
  }
}
