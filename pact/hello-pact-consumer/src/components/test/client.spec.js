const chai = require('chai');
const nock = require('nock');
const chaiAsPromised = require('chai-as-promised')
const expect = chai.expect;
chai.use(chaiAsPromised);
const API_HOST = `http://jsonplaceholder.typicode.com`;

process.env.API_HOST  = API_HOST;
process.env.API_PORT = 80;


describe('Consumer', () => {
  describe('when a call to the User provider is made', () => {
    const {fetchProviderData} = require('../client');

    it('can process the JSON payload', () => {
      nock(API_HOST)
      .get('/users')
      // .query({ validDate: /.*/ })
      .reply(200,   {
        "id": 1,
        "name": "Leanne Graham",
        "username": "Bret",
        "email": "Sincere@april.biz",
        "address": {
          "street": "Kulas Light",
          "suite": "Apt. 556",
          "city": "Gwenborough",
          "zipcode": "92998-3874",
          "geo": {
            "lat": "-37.3159",
            "lng": "81.1496"
          }
        },
        "phone": "1-770-736-8031 x56442",
        "website": "hildegard.org",
        "company": {
          "name": "Romaguera-Crona",
          "catchPhrase": "Multi-layered client-server neural-net",
          "bs": "harness real-time e-markets"
        }
      });

      const response = fetchProviderData();
      return expect(response).to.eventually.have.property('username', "Bret")
    })
  })
});
