import React, { Component, Link } from 'react';
import axios from 'axios';
import '../App.css'
import UserService from "../services/user.service";

class AdminTable extends Component {
  
  

    approvedPaddingVote(voteId){
        UserService.approvedPaddingVote(voteId).then(
            response => {
                window.location.reload();
            }
          );
    }


    render() {
        return (
            <tbody >
                {(this.props.blog.isShowAdminBoard !='0'  &&
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
                )}
                 {(this.props.blog.isShowAdminBoard !='0'  &&
                    <tr>
                         {this.props.blog.upAndDownvote.map(
                                vote => 
                                    <tr key = {vote.upAndDownVoteId} style={{backgroundColor:'white'}}>
                                        <td style={{fontWeight: "bold"}}> {vote.user.email} </td>
                                        <td style={{color: 'green', fontWeight: "bold"}}>{vote.upvote ? <p>Upvote </p> :'' }</td> 
                                        <td style={{color: 'red', fontWeight: "bold"}}> {vote.downvote ? <p>Downvote </p> : '' }</td> 
                                        <td>
                                            <button type="button"onClick={() => this.approvedPaddingVote(vote.upAndDownVoteId)} className="btn btn-success">Approved</button>   
                                        </td>
                                    </tr>
                            )
                        }
                    </tr>
                 )}
            </tbody>
        );
    }
}
export default AdminTable;