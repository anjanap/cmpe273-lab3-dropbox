const api = process.env.REACT_APP_CONTACTS_API_URL || 'http://localhost:8080'

const headers = {
    'Accept': 'application/json'
};

//signup
export const signup = (payload) =>
fetch(`${api}/signup`, {
  method: 'POST',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
  credentials:'include',
  body: JSON.stringify(payload)
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  console.log("This is signup error");
  return error;
});

//sign in
export const checklogin = (payload) =>
fetch(`${api}/signin`, {
  method: 'POST',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include',
  body: JSON.stringify(payload)
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  console.log("This is login error");
  return error;
});

//logout
export const logout = () =>
    fetch(`${api}/logout`, {
      method: 'POST',
      headers: {
          ...headers,
          'Content-Type': 'application/json'
      },
        credentials:'include'
            }).then(res => {
                return res;
            }).catch(error => {
                    console.log("This is error");
                    return error;
                });
//add file
export const add = (payload) =>
            fetch(`${api}/addfile`, {
                method: 'POST',
                  credentials:'include',
                body: payload
            }).then(res=>res.json())
            .then(res => {
                return res;
            })
                .catch(error => {
                    console.log("This is file upload error");
                    return error;
                });


//add files to folder
export const addtofolder = (payload) =>
fetch(`${api}/folderfile`, {
  method: 'POST',
  credentials:'include',
  body: payload
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  return error;
});

//create folder
export const createfolder = (payload) =>
      fetch(`${api}/createfolder`, {
        method: 'POST',
        headers: {
            ...headers,
            'Content-Type': 'application/json'
        },
          credentials:'include',
        body: JSON.stringify(payload)
      }).then(res=>res.json())
      .then(res => {
        return res;
      })
      .catch(error => {
        console.log("This is new folder error");
          return error;
        });

//list all files
export const list = (payload) =>
              fetch(`${api}/listfiles`, {
                method: 'GET',
                headers: {
                    ...headers,
                    'Content-Type': 'application/json'
                },
                  credentials:'include'
            }).then(res=>res.json())
            .then(res => {
              return res;
            })
            .catch(error => {
              console.log("This is list error");
              return error;
            });


//list all folders
export const listfolder = () =>
              fetch(`${api}/allfolders`, {
                method: 'GET',
                headers: {
                  ...headers,
                  'Content-Type': 'application/json'
                },
                  credentials:'include'
              }).then(res=>res.json())
              .then(res => {
                return res;
              })
              .catch(error => {
                console.log("This is list error");
                return error;
              });

//list starred folders
export const starred = (payload) =>
              fetch(`${api}/starredfiles`, {
                method: 'GET',
                headers: {
                  ...headers,
                  'Content-Type': 'application/json'
                },
                  credentials:'include'
              }).then(res=>res.json())
              .then(res => {
                return res;
              })
              .catch(error => {
                console.log("This is starred error");
                return error;
              });

//update star
export const starupdate = (payload) =>
fetch(`${api}/starfile`, {
  method: 'POST',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include',
  body: JSON.stringify(payload)
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  console.log("This is update star error");
  return error;
});

//delete file
export const deletefile = (payload) =>
fetch(`${api}/deletefile`, {
  method: 'POST',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include',
  body: JSON.stringify(payload)
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  return error;
});

//display activity report
export const activity = () =>
fetch(`${api}/activity`, {
  method: 'GET',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include'
}).then(res=>res.json())
.then(res => {
    return res;
  })
  .catch(error => {
    return error;
  });

//create group
export const creategroup = (payload) =>
fetch(`${api}/creategroup`, {
  method: 'POST',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include',
  body: JSON.stringify(payload)
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  return error;
});

//list all groups
export const listgroups = (payload) =>
fetch(`${api}/allgroups`, {
  method: 'GET',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include'
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  return error;
});




export const updateacc = (payload) =>
fetch(`${api}/updateaccount`, {
  method: 'POST',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include',
  body: JSON.stringify(payload)
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  return error;
});

export const getacc = (payload) =>
fetch(`${api}/account`, {
  method: 'GET',
  headers: {
    ...headers,
    'Content-Type': 'application/json'
  },
    credentials:'include'
}).then(res=>res.json())
.then(res => {
  return res;
})
.catch(error => {
  return error;
});
