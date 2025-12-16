CREATE TABLE employee(
	EmpID VARCHAR(6) NOT NULL,
        Title VARCHAR(5),
	EmpName VARCHAR(30) NOT NULL,
        Salary decimal(10,2) not null,
	EmpAddress VARCHAR(30),
        Position VARCHAR(15),
        Email VARCHAR(30),
        PhoneNumber VARCHAR(13),
	CONSTRAINT PRIMARY KEY (EmpID)
);
INSERT INTO employee VALUES('E001','Mr','Danapala',40000,'No.20 Walana','Manager','danapala@gmail.com','0716680434');
INSERT INTO employee VALUES('E002','Mr','Gunapala',40000,'No 200, Thalpitiya','Manager','Gunapala@gmail.com','0716680434');
INSERT INTO employee VALUES('E003','Mr','Amarapala',34000,'No 100, Horawala','Manager','Amarapala@gmail.com','0716680434');
INSERT INTO employee VALUES('E004','Mr','Somapala',67000,'No .10, Ginigama','Cashier','Somapala@gmail.com','0716680434');
INSERT INTO employee VALUES('E005','Mr','Jinapala',32000,'N0. 34 Ginthota','Cashier','Jinapala@gmail.com','0716680434');
INSERT INTO employee VALUES('E006','Miss','Gnanawathee','1982-1-3',78000,'No. 230, Galle Road',12500,'Manager','danapala@gmail.com','0716680434');
INSERT INTO employee VALUES('C007','Miss','Amarawathee','1984-5-7',98000,'No, Galle Road','Ambalangoda','Southern',43343);
INSERT INTO employee VALUES('C008','Ms','Leelawathee','1950-4-8',57000,'No 12, Rathnapura Road','Madampe','Sabaragamuwa',32312);
INSERT INTO employee VALUES('C009','Ms','Gunawathee','1972-3-9',76000,'No122, Anuradhapura Road','Kurunegala','Wayamba',23233);
INSERT INTO employee VALUES('C010','Mr','Dayapala','1983-4-9',40000,'No. 234, Attidiya Road','Dehiwala','Western',23434);
INSERT INTO employee VALUES('C011','Mr','Sangapala','1990-5-9',76000,'No. 43, St Peters Road','Negambo','Western',32313);
INSERT INTO employee VALUES('C012','Miss','Ariyawathee','1987-8-9',59000,'No. 123, Pamunuwa Road','Maharagama','Western',23123);
INSERT INTO employee VALUES('C013','Miss','Somawathee','1987-5-3',67000,'No. 345, Matugama Road','Kalutara','Western',23233);
INSERT INTO employee VALUES('C014','Mr','Somapala','1981-8-9',38000,'No. Ragala Road','Walapane','Central',44332);
INSERT INTO employee VALUES('C015','Mr','Ariyapala','1986-8-9',76000,'No. Kandy Road','Thrincomalee','Eastern',77867);

CREATE TABLE supplier(
	SupplierId VARCHAR(6) NOT NULL,
	Name VARCHAR(30) NOT NULL,
	Address VARCHAR(30),
        Email VARCHAR(30),
        PhoneNumber VARCHAR(13),
	CONSTRAINT PRIMARY KEY (SupplierId)
);

INSERT INTO supplier VALUES('S001','MAS Industries','No.20 Walana','MAS@gmailcom','0716680434');
INSERT INTO supplier VALUES('S002','ABC Fashion company','No 200, Thalpitiya','ABC@gmailcom','0716680434');
INSERT INTO supplier VALUES('S003','Nike Fashion company','No 100, Horawala','Nike@gmailcom','0716680434');
INSERT INTO supplier VALUES('S004','Somapala PLC','No .10, Ginigama','SomapalaPLC@gmailcom','0716680434');
INSERT INTO supplier VALUES('S005','Jinapala PLC','N0. 34 Ginthota','JinapalaPLC@gmailcom','0716680434');
INSERT INTO supplier VALUES('S006','DEF PLC','N0. 34 Ginthota','DEFPLC@gmailcom','0716680434');

CREATE TABLE product(
	SupplierId VARCHAR(6) NOT NULL,
        ProductId VARCHAR(6) NOT NULL unique,
	ProductName VARCHAR(30) NOT NULL,
        Category VARCHAR(30) NOT NULL,
	Size VARCHAR(20),
	UnitPrice DECIMAL(6,2) NOT NULL,
	QtyOnHand INT(5) NOT NULL,
	CONSTRAINT PRIMARY KEY (ProductId,SupplierId),
        CONSTRAINT FOREIGN KEY(SupplierId) REFERENCES supplier(SupplierId)
        ON UPDATE CASCADE ON DELETE CASCADE
	
);


INSERT INTO product VALUES('S001','P001','Trouser','Mens-Fashion','L',105.00,3000);
INSERT INTO product VALUES('S001','P002','T-Shirt','Mens-Fashion','M',525.00,200);
INSERT INTO product VALUES('S001','P003','Shirt','Mens-Fashion','S',995.00,36);
INSERT INTO product VALUES('S001','P004','Jacket','Mens-Fashion','M',4100.00,36);
INSERT INTO product VALUES('S001','P005','High Heel','Womens-Fashion','19',60.00,6000);
INSERT INTO product VALUES('S002','P006','Blouse','Womens-Fashion','M',560.00,300);
INSERT INTO product VALUES('S002','P007','Coka Cola 500ml','Beverages','500ml',60.00,30);
INSERT INTO product VALUES('S002','P008','Sprite 500ml','Beverages','500ml',55.00,30);
INSERT INTO product VALUES('S002','P009','Sprite 1.5L','Beverages','1.5L',160.00,30);
INSERT INTO product VALUES('S003','P010','Pepsi 500ml','Beverages','500ml',55.00,30);
INSERT INTO product VALUES('S003','P011','Pepsi 1.5L','Beverages','1.5L',160.00,30);

CREATE TABLE Orders(
        CustID VARCHAR(6) NOT NULL,
	OrderID VARCHAR(6) NOT NULL unique,
	OrderDate DATE,
	OrderStatus VARCHAR(10) NOT NULL,
	CONSTRAINT PRIMARY KEY (OrderID,CustID),
        CONSTRAINT FOREIGN KEY(CustID) REFERENCES customer(CustID)
	
);

CONSTRAINT FOREIGN KEY(CustID) REFERENCES Customer(CustID)
	

INSERT INTO Orders VALUES('C001','D001','2008-2-5','Canceled');
INSERT INTO Orders VALUES('C001','D002','2008-2-15','Canceled');
INSERT INTO Orders VALUES('C001','D003','2008-2-20','Canceled');
INSERT INTO Orders VALUES('C002','D004','2008-2-28','Canceled');
INSERT INTO Orders VALUES('C001','D005','2008-3-20','Canceled');
INSERT INTO Orders VALUES('C003','D006','2008-4-10','Canceled');
INSERT INTO Orders VALUES('C001','D007','2008-5-10','Canceled');
INSERT INTO Orders VALUES('C004','D008','2008-6-20','Canceled');
INSERT INTO Orders VALUES('C001','D009','2008-8-12','Canceled');
INSERT INTO Orders VALUES('C004','D010','2008-9-20','Canceled');
INSERT INTO Orders VALUES('C003','D011','2008-2-6','Delivered');
INSERT INTO Orders VALUES('C001','D012','2008-2-16','Delivered');
INSERT INTO Orders VALUES('C001','D013','2008-2-20','Delivered');
INSERT INTO Orders VALUES('C002','D014','2008-3-16','Delivered');
INSERT INTO Orders VALUES('C002','D015','2008-4-15','Delivered');
INSERT INTO Orders VALUES('C002','D016','2008-8-26','Delivered');
INSERT INTO Orders VALUES('C003','D017','2008-2-16','Preparing');
INSERT INTO Orders VALUES('C003','D018','2008-3-26','Preparing');
INSERT INTO Orders VALUES('C001','D019','2008-6-11','Preparing');
INSERT INTO Orders VALUES('C003','D020','2008-9-26','Preparing');
INSERT INTO Orders VALUES('C003','D021','2008-11-26','Preparing');
INSERT INTO Orders VALUES('D022','2008-1-11','C004');
INSERT INTO Orders VALUES('D023','2008-7-21','C004');
INSERT INTO Orders VALUES('D024','2008-9-11','C004');
INSERT INTO Orders VALUES('D025','2008-11-11','C005');
INSERT INTO Orders VALUES('D026','2008-12-14','C005');
INSERT INTO Orders VALUES('D027','2008-12-28','C005');
INSERT INTO Orders VALUES('D028','2008-11-29','C005');
INSERT INTO Orders VALUES('D029','2008-11-30','C005');
INSERT INTO Orders VALUES('D030','2008-1-11','C006');
INSERT INTO Orders VALUES('D031','2008-2-23','C006');
INSERT INTO Orders VALUES('D032','2008-2-27','C006');
INSERT INTO Orders VALUES('D033','2008-2-28','C006');
INSERT INTO Orders VALUES('D034','2008-3-23','C006');
INSERT INTO Orders VALUES('D035','2008-4-17','C006');
INSERT INTO Orders VALUES('D036','2008-5-5','C006');
INSERT INTO Orders VALUES('D037','2008-8-27','C006');
INSERT INTO Orders VALUES('D038','2008-10-27','C006');
INSERT INTO Orders VALUES('D039','2008-12-22','C006');
INSERT INTO Orders VALUES('D040','2008-12-29','C006');


CREATE TABLE OrderDetail(
	OrderID VARCHAR(6) NOT NULL,
	ProductId VARCHAR(6) NOT NULL,
	OrderQTY INT(11) NOT NULL,
	Discount INT(2),
	CONSTRAINT PRIMARY KEY (OrderID,ProductId),
	CONSTRAINT FOREIGN KEY (OrderID) REFERENCES orders(OrderID)
	ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT FOREIGN KEY (ProductId) REFERENCES product(ProductId)
	ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE systemAccess(
	EmpID VARCHAR(6) NOT NULL unique,
        Password VARCHAR(8) NOT NULL unique,
	
	CONSTRAINT PRIMARY KEY (EmpID,Password),
        CONSTRAINT FOREIGN KEY(EmpID) REFERENCES employee(EmpID)
	ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO systemAccess VALUES('E001','123456');
INSERT INTO systemAccess VALUES('E002','126557');
INSERT INTO systemAccess VALUES('E003','154358');
INSERT INTO systemAccess VALUES('E004','323459');
INSERT INTO systemAccess VALUES('E005','423453');

CREATE TABLE customer(
	CustID VARCHAR(6) NOT NULL Unique,
        Title VARCHAR(5),
	CustName VARCHAR(30) NOT NULL,
	CustAddress VARCHAR(30),
        Email VARCHAR(30),
        PhoneNumber VARCHAR(13) NOT NULL Unique,
	CONSTRAINT PRIMARY KEY (CustID,PhoneNumber)
);

INSERT INTO customer VALUES('C001','Mr','Danapala','No.20 Walana','danapala@gmailcom','0716680434');
INSERT INTO customer VALUES('C002','Mr','Gunapala','No 200, Thalpitiya','Gunapala@gmailcom','0716780434');
INSERT INTO customer VALUES('C003','Mr','Amarapala','No 100, Horawala','Amarapala@gmailcom','0717680434');
INSERT INTO customer VALUES('C004','Mr','Somapala','No .10, Ginigama','Somapala@gmailcom','0716680444');
INSERT INTO customer VALUES('C005','Mr','Jinapala','N0. 34 Ginthota','Jinapala@gmailcom','0717780434');
