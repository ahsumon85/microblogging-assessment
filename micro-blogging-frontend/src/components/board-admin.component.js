import React, { Component } from "react";
import AdminTable from './admin-table.component';
import UserService from "../services/user.service";
import AuthService from "../services/auth.service";
import "./styles.css";

export default class BoardAdmin extends Component {
  constructor(props) {
    super(props);
    this.state = {
      blogList: [],
      isDelete: true,
      status: false
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    UserService.getAdminContent().then(
      response => {
        console.log(console.log(JSON.stringify(response.data)));
        this.setState({
          currentUser: currentUser,
          blogList: response.data,
          status: true
        });
      },
      error => {
        this.setState({
          blogList:
            (error.response && error.response.data) ||
            error.message ||
            error.toString()
        });
      }
    );
  }

  tabRow() {
    return this.state.blogList.map(function (object, i) {
        return <AdminTable blog={object} key={i} />;
    });
  }

  render() {
    return (
      <div className="container">
       <header className="jumbotron" style={{marginTop: "-1vw"}}>
          <table className="table"  style={{marginTop: "-4vw"}}>
                {this.state.status ? this.tabRow() : <h1>Network Error </h1>}
          </table>
        </header>
      </div>
    );
  }
}
