import React from 'react';
import axios from 'axios';
import '../Category/AddCategory.css';
import { Container, Col, Form, Row, FormGroup, Label, Input, Button } from 'reactstrap';
class AddCategory extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            employeeName: '',
            employeeGender: '',
            employeePhone:'',
        }
    }
    AddCategory = () => {
        let employeeDTO = {employeeName: this.state.employeeName, employeeGender: this.state.employeeGender, employeePhone:this.state.employeePhone };
        axios.post('http://localhost:8082/employee/add', employeeDTO)
            .then(json => {
                if (json) {
                    alert("Data Saved Successfully");
                    this.props.history.push('/CategoryList')
                }
                else {
                    alert('Data not Saved');
                    this.props.history.push('/CategoryList')
                }
            })
    }
    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }
    render() {
        return (
            <Container className="App">
                <h4 className="PageHeading">Enter Employee Informations</h4>
                <Form className="form">
                    <Col>
                        <FormGroup row>
                            <Label for="name" sm={2}>Name</Label>
                            <Col sm={10}>
                                <Input type="text" name="employeeName" onChange={this.handleChange} value={this.state.employeeName} placeholder="Enter Name" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="address" sm={2}>Gender</Label>
                            <Col sm={10}>
                                <Input type="text" name="employeeGender" onChange={this.handleChange} value={this.state.employeeGender} placeholder="Enter gender" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="address" sm={2}>Phone</Label>
                            <Col sm={10}>
                                <Input type="text" name="employeePhone" onChange={this.handleChange} value={this.state.employeePhone} placeholder="Enter phone" />
                            </Col>
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup row>
                            <Col sm={5}>
                            </Col>
                            <Col sm={1}>
                                <button type="button" onClick={this.AddCategory} className="btn btn-success">Submit</button>
                            </Col>
                            <Col sm={1}>
                                <Button color="danger">Cancel</Button>{' '}
                            </Col>
                            <Col sm={5}>
                            </Col>
                        </FormGroup>
                    </Col>
                </Form>
            </Container>
        );
    }
}
export default AddCategory;