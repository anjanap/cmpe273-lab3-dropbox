import React, {Component} from 'react';
import * as API from '../api/API';
//import { Route} from 'react-router-dom';
import ReactDOM from 'react-dom';

class Signup extends Component {
  state={
    firstname:'',
    lastname:'',
    email:'',
    password:'',
    status:''
  };

  componentWillMount(){
    this.setState({status:''});
  }

  handleSubmit = (user) => {
      API.signup(user)
          .then((output) => {
              if (output === 1) {
                console.log("Success signup");
                ReactDOM.findDOMNode(this.refs.fn).value = "";
                ReactDOM.findDOMNode(this.refs.ln).value = "";
                ReactDOM.findDOMNode(this.refs.em).value = "";
                ReactDOM.findDOMNode(this.refs.pwd).value = "";
                this.setState({status: "Sign up successful."});

              } else {
                console.log("Failed signup");
                this.setState({status: "Sign up failed."});
              }
          });
  };


    render() {
        return (
          <div>
          <form>
          <div className="form-group row">
          <div className="col-sm-2 col-md-2 col-lg-2">First Name:</div>
           <div className="col-sm-10 col-md-10 col-lg-10">
           <input type="text" ref="fn" id="fname" className="w3-input" onChange={(event)=>{
                                        this.setState({firstname: event.target.value});}} /></div>
          </div>

          <div className="form-group row">
          <div className="col-sm-2 col-md-2 col-lg-2">Last Name:</div>
           <div className="col-sm-10 col-md-10 col-lg-10">
           <input type="text" ref="ln" id="lname" className="w3-input" onChange={(event)=>{
                                        this.setState({lastname: event.target.value});}} /></div>
          </div>

          <div className="form-group row">
          <div className="col-sm-2 col-md-2 col-lg-2">Email:</div>
           <div className="col-sm-10 col-md-10 col-lg-10">
           <input type="text" ref="em" id="email" className="w3-input" onChange={(event)=>{
                                        this.setState({email: event.target.value});}} /></div>
          </div>

          <div className="form-group row">
          <div className="col-sm-2 col-md-2 col-lg-2">Password:</div>
           <div className="col-sm-10 col-md-10 col-lg-10">
           <input type="password" ref="pwd" id="pswd" className="w3-input" onChange={(event)=>{
                                        this.setState({password: event.target.value});}} /></div>
          </div>

          <div className="form-group row">
          <div className="col-sm-4 col-md-4 col-lg-4">
          <button type="button" className="w3-button w3-green w3-border w3-border-white w3-round-large" onClick={() => this.handleSubmit(this.state)}>Submit</button>
          </div>
          </div>

          <div className="form-group row">
          <div className="col-sm-4 col-md-4 col-lg-4">
          <font color="red">{this.state.status}</font>
          </div>
          </div>

          </form>

          </div>
        );
    }
}

export default Signup;
