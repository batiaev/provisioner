CREATE TABLE IF NOT EXISTS users
(
    id       UUID PRIMARY KEY,
    name     VARCHAR(64)  NOT NULL,
    email    VARCHAR(128) NOT NULL UNIQUE,
    mobile   VARCHAR(64)  NOT NULL UNIQUE,
    password CHAR(64)     NOT NULL,
    role     VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS virtual_machines
(
    id       UUID PRIMARY KEY,
    owner_id UUID         NOT NULL,
    os       VARCHAR(128) NOT NULL,
    ram      BIGINT       NOT NULL,
    hardDisk CHAR(128)    NOT NULL,
    cpu      INT          NOT NULL,
    CONSTRAINT fk_vm_owner
        FOREIGN KEY (owner_id)
            REFERENCES users (id)
);
