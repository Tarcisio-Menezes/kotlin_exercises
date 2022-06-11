CREATE TABLE book (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    price INT NOT NULL,
    status VARCHAR(100) NOT NULL,
    customer_id INT NOT NULL,
    image VARCHAR(200) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(id));
