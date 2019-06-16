const request = require('superagent')
const API_ENDPOINT = `http://jsonplaceholder.typicode.com`;

// Fetch provider data
const fetchProviderData = () => {
  return request
    .get(`${API_ENDPOINT}/users`)
    .query({ validDate: new Date().toISOString() })
    .then(res => { return res })
};

module.exports = {
  fetchProviderData,
};
