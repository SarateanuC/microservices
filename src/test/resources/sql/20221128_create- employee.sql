CREATE TABLE employee
(
    id         UUID PRIMARY KEY,
    name       varchar NOT NULL,
    company_id uuid    NOT NULL,
    address_id uuid,
    status     varchar,
    CONSTRAINT fk_address_id
        FOREIGN KEY (address_id)
            REFERENCES address (address_id)

);