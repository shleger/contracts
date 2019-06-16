import React from 'react'

const Contacts = ({ contacts }) => {
  return (
      <div>
        <h1>Contact List</h1>
        {contacts.map((contact) => (
            <div className="card">
              <div className="card-body">
                <h5 className="card-title">{contact.id} {contact.name}, {contact.email}</h5>
                <p className="card-text">{contact.company.catchPhrase}</p>
              </div>
            </div>
        ))}
      </div>
  )
};

export default Contacts