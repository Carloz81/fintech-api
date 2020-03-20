insert into customer (id,creationTime,name,surname) VALUES (1,CURRENT_TIMESTAMP,'Ridley','Scott');
insert into customer (id,creationTime,name,surname) VALUES (2,CURRENT_TIMESTAMP,'Steven','Spielberg');
insert into customer (id,creationTime,name,surname) VALUES (3,CURRENT_TIMESTAMP,'Quentin','Tarantino');
insert into customer (id,creationTime,name,surname) VALUES (4,CURRENT_TIMESTAMP,'Michael','Bay');

ALTER TABLE ACCOUNT
    ADD FOREIGN KEY (CuSTOMERID)
    REFERENCES CUSTOMER (ID);

ALTER TABLE TRANSACTION
    ADD FOREIGN KEY (ACCOUNTID )
    REFERENCES ACCOUNT (ID);