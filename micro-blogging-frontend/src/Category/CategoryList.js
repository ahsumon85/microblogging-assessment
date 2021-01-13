import React, { Component } from 'react';
import axios from 'axios';
import Table from './Table';

class Categorylist extends Component {
    constructor(props) {
        super(props);
        this.state = { business: [] };
    }
    componentDidMount() {
        axios.get('http://localhost:8082/employee/find')
            .then(response => {
                this.setState({ business: response.data });
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    tabRow() {
        return this.state.business.map(function (object, i) {
            return <Table obj={object} key={i} />;
        });
    }
    render() {
        return (
            <div>
                <h4 align="center">Employee List</h4>
                <table className="table table-striped" style={{ marginTop: 10 }}>
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Phone</th>
                            <th>Gender</th>
                            <th colSpan="4">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.tabRow()}
                    </tbody>
                </table>
            </div>
        );
    }
}

export default Categorylist;