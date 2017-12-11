import React, {Component} from 'react';
import * as API from '../api/API';
import {withRouter} from 'react-router-dom';
import '../styles/tablecss.css';

class Starred extends Component {

  state={sfiles:[]}

  componentWillMount(){
    var temp=[];
    API.starred()
        .then((data) => {
            if (data) {
              for(var i=0;i<data.length;i++){
              temp = this.state.sfiles.concat(data[i]);
              this.setState({ sfiles: temp });
            }
            } else {
                console.log("File not listed");
            }
  })
}

    render() {
        return (
          <div className="container">
          <div className="w3-indigo w3-panel"><h3>Starred Files</h3></div>
          {this.state.sfiles.map(f => {
        return ( <div key={Math.random()}>
        <ul className="w3-ul w3-border">
              <li>  <a href={"http://localhost:3001/download/"+(f)} className="links">{f.filename}</a>
                </li></ul>
                 </div>
                 )
      })
     }
     </div>
        );
    }
}

export default withRouter(Starred);
