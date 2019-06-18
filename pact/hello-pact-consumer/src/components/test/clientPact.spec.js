const chai = require('chai');
const path = require('path');
const chaiAsPromised = require('chai-as-promised');
const Pact = require('@pact-foundation/pact').Pact;
const expect = chai.expect;
const API_PORT = process.env.API_PORT || 9123;
const {fetchProviderData} = require('../client');
chai.use(chaiAsPromised);

const { somethingLike: like, term } = require('@pact-foundation/pact').Matchers


// Configure and import consumer API
// Note that we update the API endpoint to point at the Mock Service
const LOG_LEVEL = process.env.LOG_LEVEL || 'WARN';

const provider = new Pact({
  consumer: 'Users Consumer',
  provider: 'Users Provider',
  port: API_PORT,
  log: path.resolve(process.cwd(), 'logs', 'pact.log'),
  dir: path.resolve(process.cwd(), 'pacts'),
  logLevel: LOG_LEVEL,
  spec: 2,
});

const emailExample = "Sincere@april.biz";

describe('Pact with Our Provider', () => {
  describe('given data count > 0', () => {
    describe('when a call to the Provider is made', () => {
      before(() => {
        return provider.setup().then(() => {
          provider.addInteraction({
            uponReceiving: 'a request for JSON data',
            withRequest: {
              method: 'GET',
              path: '/users',
            },
            willRespondWith: {
              status: 200,
              headers: {'Content-Type': 'application/json; charset=UTF-8'},
              body: {
                email: term({
                  generate: emailExample,
                  matcher:
                      '^(([^<>()\\[\\]\\\\.,;:\\s@"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@"]+)*)|(".+"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$',
                }),
                username: like("Bret"),
                id: like(1000)
              },
            },
          })
        })
      });

      it('can process the JSON payload from the provider', () => {
        const response = fetchProviderData();
        // return expect(response).to.eventually.have.property('username', "Bret")
        return expect(response).to.eventually.have.property('username', "Bret")
      });

      it('should validate the interactions and create a contract', () => {
        return provider.verify()
      })
    });

    // Write pact files to file
    after(() => {
      return provider.finalize()
    })
  })
});
