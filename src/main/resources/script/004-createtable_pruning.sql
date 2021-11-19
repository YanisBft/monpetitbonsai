CREATE TABLE pruning (
    id UUID PRIMARY KEY,
    pruning_date DATE,
    bonsai_id UUID,
    FOREIGN KEY (bonsai_id) REFERENCES Bonsai(id)
);