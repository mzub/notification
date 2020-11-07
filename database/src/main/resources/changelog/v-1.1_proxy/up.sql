CREATE SCHEMA system;

GO

CREATE TABLE system.proxies
(
    id            serial       NOT NULL,
    address       varchar(30)  NOT NULL,
    login         varchar(255) NULL,
    "password"    varchar(255) NULL,
    active        bool         NOT NULL DEFAULT true,
    CONSTRAINT proxies_address_key UNIQUE (address),
    CONSTRAINT proxies_pkey PRIMARY KEY (id)
);

GO

INSERT INTO system.proxies
(address, login, "password", active)
VALUES('193.31.102.172:9061', 'Yhu7sP', 'TEvDBN', true);
