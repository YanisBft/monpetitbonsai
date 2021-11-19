CREATE TABLE watering (
    id UUID PRIMARY KEY,
    watering_date DATE,
    bonsai_id UUID,
    FOREIGN KEY (bonsai_id) REFERENCES Bonsai(id)
);