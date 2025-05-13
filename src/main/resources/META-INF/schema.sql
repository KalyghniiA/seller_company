
CREATE TABLE IF NOT EXISTS "persons_roles" (
                                 "role_id" BIGSERIAL NOT NULL UNIQUE,
                                 "role_name" VARCHAR(255) unique,
                                 PRIMARY KEY("role_id")
);

CREATE TABLE IF NOT EXISTS "organizations_roles" (
                                       "role_id" BIGSERIAL NOT NULL UNIQUE,
                                       "role_name" VARCHAR(255) unique,
                                       PRIMARY KEY("role_id")
);

CREATE TABLE IF NOT EXISTS "persons" (
                           "person_id" BIGSERIAL NOT NULL UNIQUE,
                           "person_first_name" VARCHAR(255),
                           "person_last_name" VARCHAR(255),
                           "person_phone" VARCHAR(255),
                           "person_email" VARCHAR(255),
                           "person_role_id" BIGINT,
                           PRIMARY KEY("person_id")
);

CREATE TABLE IF NOT EXISTS "items" (
                        "item_id" BIGSERIAL NOT NULL UNIQUE,
                        "item_name" VARCHAR(255),
                        PRIMARY KEY("item_id")
);

CREATE TABLE IF NOT EXISTS "organizations" (
                                 "organization_id" BIGSERIAL NOT NULL UNIQUE,
                                 "organization_name" VARCHAR(255),
                                 "organization_address" VARCHAR(255),
                                 "organization_role_id" BIGINT,
                                 PRIMARY KEY("organization_id")
);

CREATE TABLE IF NOT EXISTS "invoices" (
                            "invoice_id" BIGSERIAL NOT NULL UNIQUE,
                            "invoice_is_sale" BOOLEAN,
                            PRIMARY KEY("invoice_id")
);

CREATE TABLE IF NOT EXISTS "contacts" (
                            "organization_id" BIGINT NOT NULL,
                            "person_id" BIGINT
);

CREATE TABLE IF NOT EXISTS "prices" (
                                    "suppliers_id" BIGINT NOT NULL UNIQUE,
                                    "item_id" BIGINT,
                                    "item_price" DOUBLE PRECISION,
                                    "item_quantity" BIGINT,
                                    "item_manufacturer" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "invoices_info" (
                                 "invoice_id" BIGINT NOT NULL UNIQUE,
                                 "invoice_manager_id" BIGINT,
                                 "invoice_organization_id" BIGINT,
                                 "item_id" BIGINT,
                                 "item_price" DOUBLE PRECISION,
                                 "item_quantity" BIGINT
);

CREATE TABLE IF NOT EXISTS "warehouse_remains" (
                                     "item_id" BIGINT NOT NULL,
                                     "item_purchase_price" DOUBLE PRECISION,
                                     "item_quantity" BIGINT,
                                     "invoice_id" BIGINT
);

ALTER TABLE persons
    ADD FOREIGN KEY (person_role_id) REFERENCES persons_roles(role_id)
    ON UPDATE CASCADE ON DELETE NO ACTION;

ALTER TABLE organizations
    ADD FOREIGN KEY (organization_role_id) REFERENCES organizations_roles(role_id)
    ON UPDATE CASCADE ON DELETE NO ACTION;

ALTER TABLE contacts
    ADD FOREIGN KEY (organization_id) REFERENCES organizations(organization_id)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE contacts
    ADD FOREIGN KEY (person_id) REFERENCES persons(person_id)
    ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE prices
    ADD FOREIGN KEY (suppliers_id) REFERENCES organizations(organization_id)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE prices
    ADD FOREIGN KEY (item_id) REFERENCES items(item_id)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE invoices_info
    ADD FOREIGN KEY (invoice_id) REFERENCES invoices(invoice_id)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE invoices_info
    ADD FOREIGN KEY (item_id) REFERENCES items(item_id)
    ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE invoices_info
    ADD FOREIGN KEY (invoice_manager_id) REFERENCES persons(person_id)
    ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE invoices_info
    ADD FOREIGN KEY (invoice_organization_id) REFERENCES organizations(organization_id)
    ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE warehouse_remains
    ADD FOREIGN KEY (item_id) REFERENCES items(item_id)
    ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE warehouse_remains
    ADD FOREIGN KEY (invoice_id) references invoices(invoice_id)
    ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO persons_roles(role_name) values
                                       ('manager'),
                                       ('provision'),
                                       ('client');

INSERT INTO organizations_roles(role_name) values
                                               ('client'),
                                               ('dealer'),
                                               ('manufacturer');