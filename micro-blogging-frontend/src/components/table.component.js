import React, { Component, Link } from 'react';
import axios from 'axios';
import '../App.css'
import UserService from "../services/user.service";

import Modal from "./modal.component"

class Table extends Component {

    constructor(props) {
        super(props);
    
        this.state = {
          modal: false
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
    
      modalOpen(upAndDownvoteList) {
        this.setState({ modal: true });
       
      }
    
      modalClose() {
        this.setState({
          modalInputName: "",
          modal: false
        });
      }

    render() {
        return (
            <tbody>
                    <tr>
                        <td style={{backgroundColor:'#848482'}}>
                            <span style={{fontWeight: "bold", color:"Highlight"}}>
                                {this.props.blog.contentTitle}
                            </span>
                            <span style={{marginLeft:'1vw'}}>
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
                            <a href="javascript:;" style={{fontWeight: "bold", color:"green"}} onClick={e => this.modalOpen(this.props.blog.upAndDownvote)}>
                                   Vote {" "}{this.props.blog.totalUpVote}{"  "}
                            </a>
                            <button type="button" onClick={this.upVoteBloggerPost} className="btn btn-success">Upvote</button>
                                <span style={{marginLeft: "1vw"}}> </span>
                            <a href="javascript:;" style={{fontWeight: "bold", color:"green"}} onClick={e => this.modalOpen(this.props.blog.upAndDownvote)}>
                                   Downvote {" "}{this.props.blog.totaldownVote}{"  "}
                            </a>
                            <button type="button" onClick={this.downVoteBloggerPost} className="btn btn-danger">Downvote</button>
                            <span style={{marginLeft: "5vw"}}> </span>
                            {this.props.blog.isDelete && (
                                <button type="button" onClick={this.deletePost} className="btn btn-danger">Delete </button>
                            )}
                        </td>
                    </tr>


                    <Modal show={this.state.modal} handleClose={e => this.modalClose(e)}>
                    <tr>
                         {this.props.blog.upAndDownvote.map(
                                vote => 
                                    <tr key = {vote.upAndDownVoteId} style={{backgroundColor:'white'}}>
                                        <td style={{fontWeight: "bold"}}> {vote.user.email} </td>
                                        <td style={{color: 'green', fontWeight: "bold"}}>{vote.upvote ? <p>Upvote </p> :'' }</td> 
                                        <td style={{color: 'red', fontWeight: "bold"}}> {vote.downvote ? <p>Downvote </p> : '' }</td> 
                                        
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