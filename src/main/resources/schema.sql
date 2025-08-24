-- schema.sql

-- Creating the employee table
CREATE TABLE IF NOT EXISTS employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    department VARCHAR(100),
    salary DECIMAL(10, 2),
    returns DECIMAL(10, 2),
    tax DECIMAL(10, 2),
    professional_tax DECIMAL(10, 2)
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

