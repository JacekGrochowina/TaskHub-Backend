CREATE TABLE users
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    email      VARCHAR(50),
    password   TEXT,
    role       VARCHAR(30) NOT NULL,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL
);