# BookStore
COMP3005

Setting Up:
The application only requires connection to the database. To connect to the database the connection and driver amnager are set up in the TryConnection class that is
called at the start of the program. The string with the username and password and connection can be adjusted in TryConnection class to connect to a new database.
The database drive which is a dependency is the PostgreSQL JDBC Driver 42.3.1.

Using:
Everything but adding employees can be done in the program. To add an employee they must be added directly to the database with a username and password that will later
be used to login. Once logged in as the employee you can add books, authors, publishers, collections, ...etc. To use as a customer you must first register. Once registered
you are automatically logged in and can search books, display books, add to cart, and make an order. All commands are displayed and shown in quotes ex. "DisplayBooks"

Exit:
To switch between employee and customer the application must be restart. To close the application the customer or employee can type "Exit" at any time.
