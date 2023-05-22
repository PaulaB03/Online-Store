# Online Store

- [Version 1](https://github.com/PaulaB03/PROIECT_PAO_234_BALAN_PAULA/tree/ver1)
- [Version 2](https://github.com/PaulaB03/PROIECT_PAO_234_BALAN_PAULA/tree/ver2)
- [Version 3](https://github.com/PaulaB03/PROIECT_PAO_234_BALAN_PAULA/tree/ver3)

## Version 1

### Classes

- Address
- Product
- Store
- Order
- Person:
  - User
  - Owner
  - Driver

### Actions

- Driver can:
  - view deliveries assigned to him today

- User can:
  - register, log in and log out
  - update personal information
  - add products to cart
  - view cart
  - create orders
  - browse stores 

- Owner can:
  - view personal stores
  - create a new store
  - edit a store

- Store can:
  - view personal products
  - add/remove/update product
  
### Packages

- model: implementation of classes
- constants: a list of constant string
- util: enum classes (Category and PayMethod)
- validation: validates user input
- service: interface for classes

## Version 2

### Singleton Classes

- AddressCSV: Reads/Writes Addresses from/to address.csv
- OrderCSV: Reads/Writes Orders from/to orders.csv
- ProductCSV: Reads/Writes Products from/to products.csv
- UserCSV: Reads/Writes Users from/to users.csv
- Audit: Writes what and when a read/write service has been called

## Version 3

### Database

Created database that connects to JDBC. 

Implements insert, read, update and delete actions.
