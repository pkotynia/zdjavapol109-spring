DROP TABLE astronaut;
CREATE TABLE astronaut (
    astronaut_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    craft VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (astronaut_id)
);

DROP TABLE if EXISTS book;
CREATE TABLE book (
    book_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    publication_date DATE NOT NULL,
    PRIMARY KEY (book_id)
);