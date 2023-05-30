DROP TABLE astronaut;
CREATE TABLE astronaut (
    astronaut_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    craft VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (astronaut_id)
);