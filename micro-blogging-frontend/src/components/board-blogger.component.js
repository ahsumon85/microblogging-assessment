import React, { Component } from "react";

import UserService from "../services/user.service";
import CheckButton from "react-validation/build/button";
import Table from './table.component';
import Form from "react-validation/build/form";
import AuthService from "../services/auth.service";

const required = value => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};


export default class BoardBlogger extends Component {
  constructor(props) {
    super(props);
    this.onChangeContent = this.onChangeContent.bind(this);
    this.handleBloggerPost = this.handleBloggerPost.bind(this);
    this.state = {
      blogList: [],
      status: false,
      content: '',
      successful: false,
      message: "",
      currentUser: {
              username: ""
        },
      isDelete: true
    };
  }

  componentDidMount() {
    const currentUser = AuthService.getCurrentUser();
    UserService.getBLogerPostByBloggerName().then(
      response => {
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
  
  onChangeContent(e) {
    this.setState({
      content: e.target.value
    });
  }


  handleBloggerPost(e) {
    e.preventDefault();

    this.setState({
      message: "",
      successful: false
    });

    this.form.validateAll();

    if (this.checkBtn.context._errors.length === 0) {
      UserService.blogPostByBlogger(this.state.content)
          .then(response => {
              window.location.reload();
            },
            error => {
              const errorMessage = "Please try again";
              this.setState({
                successful: false,
                message: errorMessage
              });
            }
          );
    }
  }

  tabRow() {
    return this.state.blogList.map(function (object, i) {
        return <Table blog={object} key={i} />;
    });
  }

 

  render() {
    const { currentUser } = this.state;
    return (
      <div className="container" style={{width: "100vw"}}>

        {currentUser && (
        <Form
            onSubmit={this.handleBloggerPost}
            ref={c => {
              this.form = c;
            }}
          >
            {!this.state.successful && (
              <div>
                <div className="form-group">
                  <textarea
                    type="text"
                    placeholder="Enter your blog........"
                    className="form-control"
                    name="content"
                    value={this.state.content}
                    onChange={this.onChangeContent}
                    validations={[required]}
                  />
                </div>
                <div className="form-group">
                  <button className="btn btn-primary btn-block">BLOG POST</button>
                </div>
              </div>
            )}

            {this.state.message && (
              <div className="form-group">
                <div
                  className={
                    this.state.successful
                      ? "alert alert-success"
                      : "alert alert-danger"
                  }
                  role="alert"
                >
                  {this.state.message}
                </div>
              </div>
            )}
            <CheckButton
              style={{ display: "none" }}
              ref={c => {
                this.checkBtn = c;
              }}
            />
          </Form>
          )}
        <header className="jumbotron" style={{marginTop: "-1vw"}}>
          <table className="table"  style={{marginTop: "-4vw"}}>
                {this.state.status ? this.tabRow() : <h1>Network Error </h1>}
          </table>
        </header>
      </div>         
    );
  }
}
