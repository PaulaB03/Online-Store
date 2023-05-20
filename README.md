# Version 1

## Classes

- Address
- Product
- Store
- Order
- Person:
  - User
  - Owner
  - Driver

## Actions

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
  
## Packages

- model: implementation of classes
- constants: a list of constant string
- util: enum classes (Category and PayMethod)
- validation: validates user input
- service: interface for classes
