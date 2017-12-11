import React, {Component} from 'react';
import * as API from '../api/API';
import {withRouter} from 'react-router-dom';
import ReactDOM from 'react-dom';

class Account extends Component {
  state={
    firstName:'', lastName:'', email:'', password:'',
    work:'', education:'', intrests:'',details:[]
  };

  componentWillMount(){
          API.getacc()
          .then((data) => {
              if (data) {
                this.setState({firstName:data[0].firstname, lastName:data[0].lastname,email:data[0].email, password:data[0].password,
                    work:data[0].work, education:data[0].education, intrests:data[0].intrests});
              } else {
                  console.log("No data");
              }
          });
  }


    render() {
        return (
          <div className="container">
          <div className="w3-indigo w3-panel"><h3>My Account</h3></div>
          <br />
<form>
<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
            <label>First Name:</label>
            <input type="text" value={this.state.firstName} className="form-control" onChange={(event)=>{this.setState({firstName: event.target.value});}}/>
          </div></div>
<div className="col-sm-1 col-md-1 col-lg-1"></div>
<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
            <label>Last Name:</label>
            <input type="text" value={this.state.lastName} className="form-control" onChange={(event)=>{this.setState({lastName: event.target.value});}}/>
          </div></div>

<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
            <label>Email:</label>
            <input type="text" value={this.state.email} className="form-control" onChange={(event)=>{this.setState({email: event.target.value});}}/>
          </div></div>
<div className="col-sm-1 col-md-1 col-lg-1"></div>
<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
            <label>Password:</label>
            <input type="text" value={this.state.password} className="form-control" onChange={(event)=>{this.setState({password: event.target.value});}}/>
          </div></div>

<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
            <label>Work:</label>
            <input type="text" value={this.state.work} className="form-control" onChange={(event)=>{this.setState({work: event.target.value});}}/>
          </div></div>
<div className="col-sm-1 col-md-1 col-lg-1"></div>
<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
            <label>Education:</label>
            <input type="text" value={this.state.education} className="form-control" onChange={(event)=>{this.setState({education: event.target.value});}}/>
          </div></div>

<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
            <label>Intrests:</label>
            <input type="text" value={this.state.intrests} className="form-control" onChange={(event)=>{this.setState({intrests: event.target.value});}}/>
          </div></div>
          <div className="col-sm-7 col-md-7 col-lg-7"></div>
<div className="col-sm-12 col-md-12 col-lg-12"></div>
<div className="col-sm-12 col-md-12 col-lg-12"></div>
<div className="col-sm-5 col-md-5 col-lg-5">
          <div className="input-field">
          <button type="button" className="w3-button w3-green w3-border w3-border-white w3-round-large" onClick={() => this.handleUpdate(this.state)}>Update</button>
          </div></div>

</form>
          </div>
        );
    }
}

export default withRouter(Account);
