import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
class Table extends Component {
    constructor(props) {
        super(props);
    }
    DeleteCategory = () => {
        axios.delete('http://localhost:8082/employee/delete/' + parseInt(this.props.obj.employeeId))
            .then(json => {
                alert('Record deleted successfully!!');
            })
    }
    render() {
        return (
            <tr>
                <td>
                    {this.props.obj.employeeName}
                </td>
                <td>
                    {this.props.obj.employeePhone}
                </td>
                <td>
                    {this.props.obj.employeeGender}
                </td>
                <td>
                    <Link to={"/edit/" + this.props.obj.employeeId} className="btn btn-success">Edit</Link>
                </td>
                <td>
                    <button type="button" onClick={this.DeleteCategory} className="btn btn-danger">Delete</button>
                </td>
            </tr>
        );
    }
}
export default Table;