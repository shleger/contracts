const request = require('superagent')
const API_ENDPOINT = `http://jsonplaceholder.typicode.com`;

// Fetch provider data
const fetchProviderData = () => {
  return request
    .get(`${API_ENDPOINT}/users`)
    .then(res => { return res.body })
};

module.exports = {
  fetchProviderData,
};
