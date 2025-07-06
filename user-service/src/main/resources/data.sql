

CREATE TABLE IF NOT EXISTS "USERS" (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    registered_date DATE NOT NULL
    );

-- Inserting dummy data into the USERS table.
-- Note: The table name 'USERS' is quoted because USER can be a reserved keyword in some SQL dialects.

INSERT INTO "USERS" (id, username, email, password, registered_date) VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11', 'john.doe', 'john.doe@example.com', 'password123', '2023-01-15');

INSERT INTO "USERS" (id, username, email, password, registered_date) VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a12', 'jane.smith', 'jane.smith@example.com', 'securepass!', '2023-03-22');

INSERT INTO "USERS" (id, username, email, password, registered_date) VALUES
    ('a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a13', 'admin.user', 'admin@smartgrow.de', 'adminpass', '2022-11-01');
