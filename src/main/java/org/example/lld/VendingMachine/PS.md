## Problem Statement:

Design a low-level vending machine system that simulates the process of selecting a product, inserting money, and receiving the product along with any change. The system should support multiple product types with varying prices and quantities, and be able to handle different denominations of currency.

**Core Requirements:**

- Product Selection: Users should be able to select a product from a displayed inventory.
- Payment Processing: The system must accept various denominations of coins/notes and validate the inserted amount against the product price.
- Product Dispensing: Once sufficient payment is made, the selected product should be dispensed.
- Change Management: The system must calculate and return the correct change. If exact change cannot be provided, the transaction should be cancelled and the inserted money returned.
- Inventory Management: Track product quantities and prevent selection of out-of-stock items.
- Error Handling: Gracefully handle insufficient funds, out-of-stock items, and invalid selections.

**Additional (Potential 'Weird') Requirements:**

- Support for 'combo' purchases (e.g., drink + snack at a discounted price).
- Ability to restock products and refill change.
- Maintenance mode for technicians.
- Integration with a remote monitoring system for sales and stock levels.
- Support for multiple payment methods (e.g., card payments, mobile payments) beyond cash, though this can be simplified for the LLD.

**Design Considerations:**

- Use Object-Oriented Programming (OOP) principles.
- Identify key classes and their interactions (e.g., VendingMachine, Product, Coin, State).
- Consider state management for the vending machine (e.g., Idle, HasSelection, AcceptingMoney, DispensingProduct, ReturningChange).
- Discuss potential design patterns (e.g., State, Strategy, Factory).
- Focus on extensibility for adding new products, payment methods, or features.