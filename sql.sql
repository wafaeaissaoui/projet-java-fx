
CREATE TABLE IF NOT EXISTS "user" (
        "id"    INTEGER,
        "username"      TEXT,
        "email" TEXT,
        "password"      TEXT
);

CREATE TABLE IF NOT EXISTS "order" (
        "id"    INTEGER PRIMARY KEY AUTOINCREMENT,
        "nam_client"    INTEGER ,
        "product"       INTEGER ,
        "number"        INTEGER,
        "price" INTEGER,
        "status"        TEXT,
        FOREIGN KEY("nam_client") REFERENCES "client"("id_client"),
        FOREIGN KEY("product") REFERENCES "product"("id_product")
);
 

CREATE TABLE IF NOT EXISTS "client" (
        "id_client"     INTEGER PRIMARY KEY AUTOINCREMENT,
        "nameclient"    TEXT,
        "tele"  INTEGER,
        "email" TEXT,
        "price" INTEGER
);
INSERT INTO client VALUES(1,'wafae aissaoui',680567890,'wafaeissa@gmail.com',2000);
INSERT INTO client VALUES(2,'aya merzouk',645431230,'ayamerzouk2@gmail.com',222);
CREATE TABLE IF NOT EXISTS "product" (
        "id_product"    INTEGER PRIMARY KEY AUTOINCREMENT,
        "nameproduct"   TEXT,
        "price" INTEGER,
        "qunatity"      INTEGER,
        "description"   TEXT
);
INSERT INTO product VALUES(1,'produit A',300,10,'produit.........');
INSERT INTO product VALUES(2,'produit B',200,12,'produit......');

