
CREATE TABLE tlb_invoices (
                                id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                                number_invoice VARCHAR(250) NOT NULL,
                                description VARCHAR(250),
                                customer_id BIGINT,
                                create_at DATE,
                                state VARCHAR(250) NOT NULL,

);


DROP TABLE IF EXISTS tbl_products;

CREATE TABLE tbl_invoce_items (
                              id BIGINT AUTO_INCREMENT  PRIMARY KEY,
                              product_id BIGINT,
                              quantity BIGINT,
                              price DOUBLE
);