ALTER TABLE bonsai
    ADD COLUMN owner_id UUID
        REFERENCES Owner(id);