/*Queries from Author.java File */

/*This query insets a new Author into the Author table with their name and ID*/
insert into Author values('" + this.name + "','" + this.authorID + "');

/*This query deletes an Author from the Author table using their ID*/
DELETE FROM Author WHERE authorID = ?;

/*Queries from Book.java File */

/*This query inserts a new book into the Book table using a name, number of pages, stock, threshold, isbn, price,
  genre, book royalty, author id, and publisher email*/
INSERT INTO Book VALUES('this.name','this.numberOfPages','this.stock','this.threshold','this.isbn','this.price','this.genre','this.bookRoyalty','this.authorID','this.email');

/*This query removes a book from the Book table using a isbn*/
DELETE FROM Book WHERE isbn = ?;

/*This increases the stock of a book determined by its isbn by a certain amount*/
UPDATE Book SET stock = ? WHERE isbn = ?;

/*Queries from BookOrder.java File */

/*This inserts a new Order into the BookOrder table using a orderID, shipping address, payment info, and a
  tracking number*/
INSERT INTO BookOrder VALUES('this.orderID','this.shippingAddress','this.paymentInfo','this.trackingNumber');

/*This returns the entire BookOrder table */
SELECT * FROM BookOrder

/*Queries from Cart.java File */

/*Selects the cartIDs from the customer table where the username is a specified value */
SELECT cartID FROM Customer WHERE username = ?;

/*Selects everything from the Cart table where the cartID is a specified value */
SELECT * FROM Cart WHERE cartID = ?;

/*Reduces the stock by a set amount of a specific book specified by its isbn */
UPDATE Book SET stock = stock - ? WHERE isbn = ?;

/*Deletes all items from the Cart table where cartID is a specified value */
DELETE FROM Cart WHERE cartID = ?

/*Auto restocks by increasing a books stock back up to its initial stock value when the stock falls below or equal to
  the threshold */
UPDATE Book SET stock = startingstock WHERE stock <= threshold;

/*Selects unique isbns from the Cart Table where the cartID value and isbn are specified values. Used to check if
  book is already in cart */
SELECT DISTINCT isbn FROM Cart WHERE cartID = ? AND isbn = ?;

/*When a book is already in the cart and is added again the amount being added are added to that books total amount */
UPDATE Cart SET amount = amount + ? WHERE isbn = ? AND cartID = ?;

/*If a book is not already in the cart when trying to add it, the book is added to the Cart table using its cartID,
  bookISBN, and amount */
INSERT INTO Cart values('this.cartID','bookISBN','amount');

/*When a book is removed from the cart this query removes that book from the cart table using its isbn */
DELETE FROM Cart WHERE isbn = ?;

/*Queries from Collection.java File */

/*This query insets a new Collection into the Collection table with a collection name, the first book added to the
  collection, and the username of the creator*/
INSERT INTO Collection VALUES('this.collectionName','isbn','this.username')

/*This query gets the username of the creator of a specified collection */
SELECT username FROM Collection WHERE collectionName = 'this.collectionName'

/*Queries from Customer.java File */

/*Inserts a new customer into the customer table given the customers name, username, password, address, postalcode,
  payment info, and cartID */
INSERT INTO Customer values('this.customerName','this.userName','this.password','this.address','this.postalCode','this.paymentInfo','this.cartID');

/*Selects all columns from the customer table for the rows where the username and password are specific values,
  used in user authentication */
SELECT * FROM Customer WHERE username = 'this.userName' AND password = 'this.password';

/*Selects everything from the customer table to determine the number of rows */
SELECT * FROM Customer;

/*Queries from Employee.java File */

/*Selects all columns from the employee table for the rows where the username and password are specific values,
  used in user authentication */
SELECT * FROM Employee WHERE username = 'this.userName' AND password = 'this.password';

/*Queries from OrderItem.java File */

/*Inserts a new item into orderItems using the orderID of the new order, the isbn of the book in the order,
  and the number of that book being purchased */
INSERT INTO OrderItem VALUES('this.isbn','this.orderID','this.amount');

/*Queries from Publisher.java File */

/*Adds a new publisher to the publisher table using a name, address, phone number, bank Account, and an email */
INSERT INTO Publisher VALUES('this.name','this.address','this.phoneNumber','this.bankAccount','this.email');

/*Deletes a publisher from the publisher table using its email */
DELETE FROM Publisher WHERE email = ?;

/*Queries from TextInterface.java File */

/*Makes a table of books that are linked to an author name entered by the user */
SELECT * FROM Author INNER JOIN Book ON Author.authorid = Book.authorid WHERE Author.name = ?;

/*Makes a table of books that are linked to an isbn entered by the user */
SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book WHERE isbn = ?;

/*Makes a table of books that are linked to a genre entered by the user */
SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book WHERE genre = ?;

/*Makes a table of books that are linked to a book name entered by the user */
SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book WHERE name = ?;

/*Makes a table of books that are linked to a collection name entered by the user */
SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book INNER JOIN Collection ON book.isbn = collection.isbn WHERE collection.collectionname = ?;

/*Makes a table of all books in the cart with all the books info */
SELECT Book.name, Book.authorID, Book.isbn, Book.stock, Book.price, Book.genre FROM Book INNER JOIN Cart ON Book.isbn = Cart.isbn;

/*Calculates the total sales by taking all sold items from the OrderItem table and getting their prices from the book table to sum */
SELECT SUM(price * amount) as all_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn) AS book_order;

/*Calculates the net profit by taking all sold items from the OrderItem table and getting their prices from the book
  table to sum then subtracting the bookRoyalty owed to the publisher */
SELECT SUM((price * amount) - (price * bookRoyalty * 0.01)) as net_profit FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn) AS book_order;

/*Calculates the total sales of a genre by taking all sold items from the OrderItem table and selecting the books
  from a specified genre then getting their prices from the book table to sum */
SELECT SUM(price * amount) AS genre_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE Book.genre = ?) AS book_order;

/*Calculates the total sales of a book by taking all sold items from the OrderItem table and selecting the book
  with a specific isbn then getting their prices from the book table to sum */
SELECT SUM(price * amount) AS book_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE OrderItem.isbn = ?) AS book_orders;

/*Calculates the total sales of an author by taking all sold items from the OrderItem table and selecting the books
  with a specific authorID then getting their prices from the book table to sum */
SELECT SUM(price * amount) as author_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE authorID = ?) AS book_order;

/*Calculates the total sales of a publisher by taking all sold items from the OrderItem table and selecting the books
  with a specific publisher email then getting their prices from the book table to sum */
SELECT SUM(price * amount) as all_sales FROM (SELECT * FROM Book INNER JOIN OrderItem ON Book.isbn = OrderItem.isbn WHERE email = ?) AS book_order;