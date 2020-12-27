import React, { Component } from 'react';
import axios from 'axios';
import '../App.css'

class Table extends Component {
  
    // DeleteCategory = () => {
    //     axios.delete('http://localhost:8082/employee/delete/' + parseInt(this.props.obj.employeeId))
    //         .then(json => {
    //             alert('Record deleted successfully!!');
    //         })
    // }
    render() {
        return (
            <tr>
                <tr>
                     <td>
                        {this.props.blog.user.email}
                    </td>
                    <td>
                        {this.props.blog.createDate}
                    </td>
                </tr>
                <tr>
                    <td>
                        {this.props.blog.content}
                    </td>
                </tr>
            </tr>
        );
    }
}
export default Table;