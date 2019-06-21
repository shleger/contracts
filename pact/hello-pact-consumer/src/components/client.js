const request = require('superagent');
// const API_HOST = process.env.API_HOST || 'http://jsonplaceholder.typicode.com';
// const API_PORT = process.env.API_PORT || 80;
const API_HOST = process.env.API_HOST || 'http://localhost';
const API_PORT = process.env.API_PORT || 9123;

const API_ENDPOINT = `${API_HOST}:${API_PORT}`;

// Fetch provider data
const fetchProviderData = () => {
  return request
    .get(`${API_ENDPOINT}/users`)
    .then(res => { return res.body })
};

module.exports = {
  fetchProviderData,
};
