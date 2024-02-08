CREATE SCHEMA shoppingcar;
use shoppingcar;

CREATE TABLE shoppingcar.USERS (
	USER_ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    LAST_NAME VARCHAR(50),
    BIO VARCHAR(200),
    EMAIL VARCHAR(200) NOT NULL,
    AREA_OF_INTEREST VARCHAR(300),
    PRIMARY KEY (USER_ID)
);

Insert into shoppingcar.USERS values(1,'Gabo','Aragon','banana','gabo@mail.com','clothing');
Insert into shoppingcar.USERS values(2,'Javier','Sanchez','something','javi@mail.com','electronics');
Insert into shoppingcar.USERS values(3,'Diego','Jimenez','algo','diego@mail.com','accesories');
Insert into shoppingcar.USERS values(4,'jose','Gonzalez','algo','jose@mail.com','accesories');

select * from shoppingcar.USERS;


CREATE TABLE shoppingcar.PRODUCTS(
	PRODUCT_ID INT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR (100) NOT NULL,
    PRICE NUMERIC(10) NOT NULL,
    IMAGE LONGBLOB,
    DESCRIPTION VARCHAR(200),
    TOTAL_PRODUCTS_INVENTORY NUMERIC(10) NOT NULL,
    STATUS BOOLEAN NOT NULL,
    PRIMARY KEY (PRODUCT_ID)
);

select * from shoppingcar.ORDER_HISTORY;

CREATE TABLE shoppingcar.ORDER_HISTORY(
	ORDER_ID INT NOT NULL AUTO_INCREMENT,
    ORDER_DATE DATE NOT NULL,
    USER_ID INT,
    PRODUCT_ID INT,
    PRIMARY KEY (ORDER_ID)
);

insert into shoppingcar.PRODUCTS values(1,'Playstation5', 7000, 'efaewe43', 'Consola de videojuegos', 10, True);
insert into shoppingcar.PRODUCTS values(2,'Vans', 900, 'tehrr56', 'Zapatos tipo tennis', 8, True);
insert into shoppingcar.PRODUCTS values(3,'keychain', 80, 'gyera79', 'Llavero con cadena', 16, True);

select * from shoppingcar.PRODUCTS;


ALTER TABLE shoppingcar.ORDER_HISTORY
ADD FOREIGN KEY (USER_ID) REFERENCES shoppingcar.USERS(USER_ID),
ADD FOREIGN KEY (PRODUCT_ID) REFERENCES shoppingcar.PRODUCTS(PRODUCT_ID);
