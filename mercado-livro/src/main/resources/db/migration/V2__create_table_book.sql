CREATE TABLE book (
	id INT AUTO_INCREMENT PRIMARY KEY,
	identifier uuid NOT NULL,
    name VARCHAR(250) NOT NULL,
    price VARCHAR(250) NOT NULL,
    status VARCHAR(100) NOT NULL,
    customer_id INT NOT NULL,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);
