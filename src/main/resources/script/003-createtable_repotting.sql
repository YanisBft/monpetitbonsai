CREATE TABLE repotting (
    id UUID PRIMARY KEY,
    repotting_date DATE,
    bonsai_id UUID,
    FOREIGN KEY (bonsai_id) REFERENCES Bonsai(id)
);