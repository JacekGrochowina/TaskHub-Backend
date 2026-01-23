INSERT INTO users (email, password, role, created_at, updated_at)
VALUES ('john.doe@example.com', '{noop}password123', 'USER', CURRENT_TIMESTAMP, NULL),
       ('jane.smith@example.com', '{noop}password456', 'ADMIN', CURRENT_TIMESTAMP, NULL);
