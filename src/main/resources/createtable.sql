CREATE TABLE bonsai (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    species VARCHAR(255),
    acquisition_date DATE,
    acquisition_age INT,
    status VARCHAR(15)
);