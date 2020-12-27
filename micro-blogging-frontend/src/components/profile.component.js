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
        <div className="jumbotron" >
          <div style={{marginLeft: '15rem'}}>
            <header >
              <h3 style={{marginLeft: '2rem'}}>
                <strong>{currentUser.username}</strong> Profile
              </h3>
            </header>
            <p>
              <strong>Token:</strong>{"  "}
              {accessToken.access_token}
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
                {currentUser.role.name}
          </div>
        </div>: null}
        </div>
    );
  }
}
