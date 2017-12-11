import React, {Component} from 'react';
import * as API from '../api/API';
import {withRouter} from 'react-router-dom';
import '../styles/tablecss.css';
import 'w3-css/w3.css';
import '../styles/tablecss.css';

class Listall extends Component {

    state={files2:[],files:[],folders:[],bgColor1:'white',bgColor2:'#FFEE5B', newFolder:''}

   componentWillMount(){
      var joined=[],fld=[],temp=[];
        API.list()
            .then((data) => {
                if (data) {
                  for(var i=0;i<data.length;i++){
                  temp = this.state.files.concat(data[i]);
                  this.setState({ files: temp });
                }
                } else {
                    console.log("File not listed");
                }
            });

            API.listfolder()
            .then((data) => {
                if (data) {
                  for(var i=0;i<data.length;i++){
                  fld = this.state.folders.concat(data[i]);
                  this.setState({ folders: fld });
                }
                } else {
                    console.log("File not listed");
                }
            });
    }

    updateStarred = (f) => {
        API.starupdate(f)
            .then((output) => {
                if (output === 1) {
                    console.log("Star updated");
                } else {
                    console.log("Star not updated");
                }
            });
    };

    handleDelete = (f) => {
      console.log("DEL ID: "+JSON.stringify(f));
        API.deletefile(f)
            .then((output) => {
                if (output === 1) {
                    console.log("File deleted");
                } else {
                    console.log("File not deleted");
                }
            });
    };


      render() {
          return (
            <div className="container w3-panel">
    <div className="w3-indigo w3-panel"><h3>All Files</h3></div>

    {this.state.files.map(f => {
      if(f.folderID===0){
           return ( <div  key={Math.random()}>
           <div >
           <ul className="w3-ul w3-border w3-right-blue">
                  <li>
                  <div className="col-sm-11 col-md-11 col-lg-11"><a href={"http://localhost:8080/download?fname="+f.filename} className="links">{f.filename}</a></div>
                  {f.starred===0 ?
                    (<button className="w3-button w3-tiny w3-white w3-border button3" onClick={()=> this.updateStarred(f)} >*</button>) :
                    (<button className="w3-button w3-tiny w3-yellow w3-border button3" onClick={()=> this.updateStarred(f)} >*</button>)}
                    &nbsp;&nbsp;<button className="w3-button w3-tiny w3-red w3-border button3" onClick={()=> this.handleDelete(f)}>x</button>
                    </li></ul>
                    </div>
                    </div>
                  )}
                })
                }

    <br/>
    <div className="w3-indigo w3-panel"><h3>All Folders</h3></div>

    {/*this.state.folders.map(f => {
        return ( <div key={f.folderName}><b>{f.folderName}</b></div>)
      })*/}


    {this.state.folders.map(f => {
        return ( <div key={f.folderName}><b>{f.folderName}</b>

    {this.state.files.map(f2 => {
      if(f2.folderID===f.folderID){
        return ( <div key={Math.random()}>
        <div >
        <ul className="w3-ul w3-border">
                <li>
                <div className="col-sm-11 col-md-11 col-lg-11"><a href={"http://localhost:8080/download?fname="+f2.filename} className="links">{f2.filename}</a></div>
                 {f2.starred===0 ?
                  (<button className="w3-button w3-tiny w3-white w3-border button3" onClick={()=> this.updateStarred(f2)}>*</button>):
                  (<button className="w3-button w3-tiny w3-yellow w3-border button3" onClick={()=> this.updateStarred(f2)}>*</button>)}
                  &nbsp;&nbsp;<button className="w3-button w3-tiny w3-red w3-border button3" onClick={()=> this.handleDelete(f2)}>x</button>
</li></ul>
                  </div>
                  </div>
       )}
})
}
  </div>
       )
})
}

</div>
         );//return
      }//render
}//class

export default withRouter(Listall);
