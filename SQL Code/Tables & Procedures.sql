create database demo;

use demo;

create table category(
    categoryId int primary key auto_increment,
    category varchar(50) not null
);

create table product(
    id int primary key auto_increment,
    name varchar(50) not null,
    price float not null,
    quantity int not null check (quantity > 0),
    color varchar(50),
    description varchar(100),
    category int,
    foreign key (category) references category(categoryId)
);



DELIMITER //
create procedure selectALLProduct()
begin
    select product.id, product.name, product.price, product.quantity, product.color, product.description,category.category
           from product join category on product.category = category.categoryId;
end //
DELIMITER ;


call selectALLProduct();


DELIMITER //
create procedure selectALLProduct()
begin
    select product.id, product.name, product.price, product.quantity, product.color, product.description,category.category
    from product join category on product.category = category.categoryId;
end //
DELIMITER ;


DELIMITER //
create procedure selectProductByID(IN id int)
begin
    select product.id, product.name, product.price, product.quantity, product.color, product.description,category.category
    from product join category on product.category = category.categoryId
    where product.id = id;
end //
DELIMITER ;


call selectProductByID(3);


DELIMITER //
create procedure convertCategory(IN input varchar(50), OUT output int)
begin
    select categoryId into output from category where category = input;
end //
DELIMITER ;

call convertCategory('Phone', @categoryID);
select @category;

DELIMITER //
create procedure createProduct
    (IN name varchar(50), IN price float, IN quantity int, IN color varchar(50), IN description varchar(100), IN category varchar(50))
    begin
        call convertCategory(category, @categoryID);
        insert into product(NAME, PRICE, QUANTITY, COLOR, DESCRIPTION, CATEGORY) value (name, price, quantity, color, description, @categoryID);
    end //
DELIMITER ;


call createProduct('Laptop', 200, 5, 'Grey', 'Dell', 'Television');

DELIMITER //
create procedure editProduct
    (IN editId int, IN inputName varchar(50), IN inputPrice float, IN inputQuantity int, IN inputColor varchar(50), IN inputDescription varchar(100), IN category varchar(50))
    begin
        call convertCategory(category, @categoryID);
        update product set name = inputName, price = inputPrice, quantity = inputQuantity, color = inputColor, description = inputDescription, category = @categoryID where id = editId;
    end //
DELIMITER ;

call editProduct(9,'Laptop', 250.6, 10, 'white', 'Dell', 'Television' );

