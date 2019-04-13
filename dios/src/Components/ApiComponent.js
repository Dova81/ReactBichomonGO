import Axios from "axios";

const server = "http://localhost:8080";

const API = {
    get: path => Axios.get(`${server}${path}`).then(response => response.data),
  /*put: (path, body) => axios.put(`${server}${path}`, body).then(response => response.data),
    post: (path, body) => axios.post(`${server}${path}`, body).then(response => response.data),*/
  };


export default API