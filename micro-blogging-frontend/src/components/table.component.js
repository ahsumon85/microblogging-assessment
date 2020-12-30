import React, { Component, Link } from 'react';
import axios from 'axios';
import '../App.css'
import UserService from "../services/user.service";
import AuthService from "../services/auth.service";

import Modal from "./modal.component"

class Table extends Component {

    constructor(props) {
        super(props);
    
        this.state = {
            downvotemodal: false,
            upvotemodal: false,
            upvote: true,
            dowonvote: true
        };
      }
  
    deletePost = () => {
        UserService.deletePost(parseInt(this.props.blog.blogId)).then(
            response => {
                window.location.reload();
            }
          );
    }
    upVoteBloggerPost = () => {
        UserService.upVoteBloggerPost(parseInt(this.props.blog.blogId)).then(
            response => {
                window.location.reload();
            }
          );
    }
    downVoteBloggerPost = () =>{
        UserService.downVoteBloggerPost(parseInt(this.props.blog.blogId)).then(
            response => {
                window.location.reload();
            }
          );
    }

    modalOpen() {
        this.setState({ modal: true });
      }

      handleSubmit(e) {
        this.setState({ name: this.state.modalInputName });
        this.modalClose();
      }
    
      downVotemodalOpen(upAndDownvoteList) {
        this.setState({ downvotemodal: true });
       
      }

      upVotemodalOpen(upAndDownvoteList) {
        this.setState({ upvotemodal: true });
       
      }
    
      upVotemodalClose() {
        this.setState({
          modalInputName: "",
          upvotemodal: false
        });
      }

      downVotemodalClose() {
        this.setState({
          modalInputName: "",
          downvotemodal: false
        });
      }

      componentDidMount() {
        const currentUser = AuthService.getCurrentUser();
        if (!currentUser) {
                this.setState({ 
                    upvote: false,
                    dowonvote: false
                })
            }
      }

    render() {
        return (
            <tbody>
                    <tr>
                        <td style={{backgroundColor:'#848482'}}>
                            <span style={{fontWeight: "bold", color:"Highlight"}}>
                                {this.props.blog.contentTitle}
                            </span>
                            <br />
                            <span>
                                {this.props.blog.user.email}
                            </span>
                            <span style={{marginLeft:'1vw'}}>
                                {this.props.blog.createDate}
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            {this.props.blog.content}
                           
                        </td>
                        
                    </tr>
                    <tr>
                        <td>
                          
                            {this.props.blog.totalUpVote && (
                                <a href="javascript:;" style={{fontWeight: "bold", color:"green"}} onClick={e => this.upVotemodalOpen()}>
                                     {" "}{this.props.blog.totalUpVote}{"  "} Vote  {" "}
                                </a>
                            )}
                            {this.state.upvote && (
                                <button type="button" onClick={this.upVoteBloggerPost} className="btn btn-success">Upvote</button>
                            )}
                            <span style={{marginLeft: "1vw"}}> </span>

                            {this.props.blog.totaldownVote && (
                                <a href="javascript:;" style={{fontWeight: "bold", color:"green"}} onClick={e => this.downVotemodalOpen()}>
                                     {" "}{this.props.blog.totaldownVote}{"  "} Downvote  {" "}
                                </a>
                            )}
                            {this.state.dowonvote && (
                                <button type="button" onClick={this.downVoteBloggerPost} className="btn btn-danger">Downvote</button>
                            )}
                            <span style={{marginLeft: "5vw"}}> </span>
                            {this.props.blog.isDelete && (
                                <button type="button" onClick={this.deletePost} className="btn btn-danger">Delete </button>
                            )}
                        </td>
                    </tr>


                    <Modal show={this.state.upvotemodal} handleClose={e => this.upVotemodalClose(e)}>
                    <tr>
                         {this.props.blog.upvoteList.map(
                                vote => 
                                    <tr key = {vote.upAndDownVoteId} style={{backgroundColor:'white'}}>
                                        <td style={{fontWeight: "bold"}}> {vote.user.email} </td>
                                    </tr>
                            )
                        }
                    </tr>
                    </Modal>

                    <Modal show={this.state.downvotemodal} handleClose={e => this.downVotemodalClose(e)}>
                    <tr>
                         {this.props.blog.downvoteList.map(
                                vote => 
                                    <tr key = {vote.upAndDownVoteId} style={{backgroundColor:'white'}}>
                                        <td style={{fontWeight: "bold"}}> {vote.user.email} </td>
                                    </tr>
                            )
                        }
                    </tr>
                    </Modal>
            </tbody>
        );
    }
}
export default Table;