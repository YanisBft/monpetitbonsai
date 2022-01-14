CREATE TABLE users (
    id UUID PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    enabled BOOLEAN
);