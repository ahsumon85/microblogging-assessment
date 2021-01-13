import React from 'react';
import { Container, Col, Form, Row, FormGroup, Label, Input, Button } from 'reactstrap';
import axios from 'axios'
import '../Category/AddCategory.css'
class Edit extends React.Component {
    constructor(props) {
        super(props)
        this.onChangeEmployeeName = this.onChangeEmployeeName.bind(this);
        this.onChangeEmployeeGender = this.onChangeEmployeeGender.bind(this);
        this.onChangeEmployeePhone = this.onChangeEmployeePhone.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.state = {
            employeeName: '',
            employeeGender: '',
            employeePhone:'',
        }
    }
    componentDidMount() {
        axios.get('http://localhost:8082/employee/find/by-id?id=' + this.props.match.params.id)
            .then(response => {
                this.setState({
                    employeeName: response.data.employeeName,
                    employeePhone: response.data.employeePhone,
                    employeeGender: response.data.employeeGender
                });
            })
            .catch(function (error) {
                console.log(error);
            })
    }

    onChangeEmployeeName(e) {
        this.setState({
            employeeName: e.target.value
        });
    }

    onChangeEmployeeGender(e) {
        this.setState({
            employeeGender: e.target.value
        });
    }

    onChangeEmployeePhone(e) {
        this.setState({
            employeePhone: e.target.value
        });
    }

    onSubmit(e) {
        debugger;
        e.preventDefault();
        const employeeDTO = {
            employeeId: this.props.match.params.id,
            employeeName: this.state.employeeName,
            employeeGender: this.state.employeeGender,
            employeePhone: this.state.employeePhone
        };
        axios.post('http://localhost:8082/employee/update', employeeDTO)
            .then(res => { this.props.history.push('/CategoryList') });

    }
    render() {
        return (
            <Container className="App">
                <h4 className="PageHeading">Update Category Informations</h4>
                <Form className="form" onSubmit={this.onSubmit}>
                    <Col>
                        <FormGroup row>
                            <Label for="name" sm={2}>Name</Label>
                            <Col sm={10}>
                                <Input type="text" name="employeeName" value={this.state.employeeName} onChange={this.onChangeEmployeeName} placeholder="Enter Name" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="Password" sm={2}>Gender</Label>
                            <Col sm={10}>
                                <Input type="text" name="employeeGender" value={this.state.employeeGender} onChange={this.onChangeEmployeeGender} placeholder="Enter Gender" />
                            </Col>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="Password" sm={2}>Phone</Label>
                            <Col sm={10}>
                                <Input type="text" name="employeePhone" value={this.state.employeePhone} onChange={this.onChangeEmployeePhone} placeholder="Enter Phone" />
                            </Col>
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup row>
                            <Col sm={5}>
                            </Col>
                            <Col sm={1}>
                                <Button type="submit" color="success">Submit</Button>{' '}
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
export default Edit;