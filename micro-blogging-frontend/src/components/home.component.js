import React, { Component } from "react";

import UserService from "../services/user.service";
import Table from './table.component'

export default class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      blogList: []
    };
  }

  componentDidMount() {
    UserService.getBloggerContent().then(
      response => {
        console.log(JSON.stringify(response.data));
        this.setState({
          blogList: response.data
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
        return <Table blog={object} key={i} />;
    });
}

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
          <table className="table" >
                  <tbody>
                      {this.tabRow()}
                  </tbody>
          </table>
        </header>
      </div>         
    );
  }
}
