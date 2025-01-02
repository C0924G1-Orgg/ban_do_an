create database order_food;
use order_food;
-- Bảng khách hàng
create table customers (
                           customer_id int auto_increment primary key,
                           customer_name varchar(50) not null,
                           customer_email varchar(100) unique not null,
                           customer_password varchar(255) not null,
                           customer_phone varchar(15),
                           customer_address varchar(100)
);
-- Bảng nhà hàng
create table restaurants (
                             restaurant_id int auto_increment primary key,
                             restaurant_name varchar(100) not null,
                             restaurant_address varchar(100) not null,
                             restaurant_phone varchar(15) not null,
                             restaurant_email varchar(100) not null
);
-- Bảng thực đơn món ăn
create table foods (
                           food_id int auto_increment primary key,
                           restaurant_id int not null,
                           food_name varchar(100) not null,
                           food_description varchar(255),
                           food_price decimal(10, 2) not null,
                           foreign key (restaurant_id) references restaurants(restaurant_id) on delete cascade
);
-- Bảng đơn hàng
create table order_table (
                             order_id int auto_increment primary key,
                             customer_id int not null,
                             restaurant_id int not null,
                             order_date datetime not null,
                             order_total_price decimal(10, 2) not null,
                             order_status varchar(50) not null default 'pending',
                             foreign key (customer_id) references customers(customer_id) on delete cascade,
                             foreign key (restaurant_id) references restaurants(restaurant_id)
);
-- Bảng chi tiết đơn hàng
create table order_detail (
                              detail_id int auto_increment primary key,
                              order_id int not null,
                              food_id int not null,
                              detail_quantity int not null,
                              detail_price decimal(10, 2) not null,
                              foreign key (order_id) references order_table(order_id) on delete cascade,
                              foreign key (food_id) references foods(food_id) on delete cascade
);
insert into customers (customer_name, customer_email, customer_password, customer_phone, customer_address)
values
    ('Nguyen Van A', 'nva@gmail.com', 'password1', '0912345678', 'Hà Nội'),
    ('Le Thi B', 'ltb@gmail.com', 'password2', '0987654321', 'Sài Gòn'),
    ('Tran Van C', 'tvc@gmail.com', 'password3', '0911122233', 'Đà Nẵng'),
    ('Pham Thi D', 'ptd@gmail.com', 'password4', '0933445566', 'Hải Phòng'),
    ('Hoang Van E', 'hve@gmail.com', 'password5', '0977889900', 'Cần Thơ'),
    ('Nguyen Thi F', 'ntf@gmail.com', 'password6', '0922233344', 'Huế'),
    ('Bui Van G', 'bvg@gmail.com', 'password7', '0944556677', 'Quảng Ninh'),
    ('Do Thi H', 'dth@gmail.com', 'password8', '0933445566', 'Đồng Nai'),
    ('Nguyen Van I', 'nvi@gmail.com', 'password9', '0919988776', 'Nha Trang'),
    ('Tran Thi K', 'ttk@gmail.com', 'password10', '0966778899', 'Hà Nội');
insert into restaurants (restaurant_name, restaurant_address, restaurant_phone, restaurant_email)
values
    ('Nhà hàng A', 'Hà Nội', '0241234567', 'a@gmail.com'),
    ('Nhà hàng B', 'Sài Gòn', '0281234567', 'b@gmail.com'),
    ('Nhà hàng C', 'Đà Nẵng', '0511234567', 'c@gmail.com'),
    ('Nhà hàng D', 'Hải Phòng', '0311234567', 'd@gmail.com'),
    ('Nhà hàng E', 'Cần Thơ', '0711234567', 'e@gmail.com'),
    ('Nhà hàng F', 'Huế', '0541234567', 'f@gmail.com'),
    ('Nhà hàng G', 'Quảng Ninh', '0331234567', 'g@gmail.com'),
    ('Nhà hàng H', 'Đồng Nai', '0611234567', 'h@gmail.com'),
    ('Nhà hàng I', 'Nha Trang', '0581234567', 'i@gmail.com'),
    ('Nhà hàng K', 'Hà Nội', '0249876543', 'k@gmail.com');
insert into foods (restaurant_id, food_name, food_description, food_price)
values
    (1, 'Phở Bò', 'Phở bò truyền thống', 50000),
    (1, 'Bún Chả', 'Bún chả Hà Nội', 45000),
    (2, 'Cơm Tấm', 'Cơm tấm sườn bì chả', 40000),
    (2, 'Hủ Tiếu', 'Hủ tiếu Nam Vang', 45000),
    (3, 'Mì Quảng', 'Mì Quảng đặc biệt', 60000),
    (3, 'Bánh Tráng Trộn', 'Bánh tráng trộn Đà Nẵng', 25000),
    (4, 'Bánh Đa Cua', 'Bánh đa cua Hải Phòng', 55000),
    (5, 'Lẩu Cá', 'Lẩu cá lóc đồng', 75000),
    (6, 'Bún Bò Huế', 'Bún bò Huế đặc biệt', 50000),
    (7, 'Chả Mực', 'Chả mực Hạ Long', 70000);
insert into order_table (customer_id, restaurant_id, order_date, order_total_price, order_status)
values
    (1, 1, '2023-01-10 12:00:00', 150000, 'completed'),
    (2, 2, '2023-01-11 13:00:00', 200000, 'completed'),
    (3, 3, '2023-01-12 14:00:00', 300000, 'pending'),
    (4, 4, '2023-01-13 15:00:00', 250000, 'completed'),
    (5, 5, '2023-01-14 16:00:00', 350000, 'cancelled'),
    (6, 6, '2023-01-15 17:00:00', 100000, 'completed'),
    (7, 7, '2023-01-16 18:00:00', 400000, 'pending'),
    (8, 8, '2023-01-17 19:00:00', 300000, 'completed'),
    (9, 9, '2023-01-18 20:00:00', 200000, 'completed'),
    (10, 10, '2023-01-19 21:00:00', 150000, 'pending');
insert into order_detail (order_id, food_id, detail_quantity, detail_price)
values
    (1, 1, 2, 100000),
    (1, 2, 1, 45000),
    (2, 3, 3, 120000),
    (2, 4, 2, 90000),
    (3, 5, 1, 60000),
    (4, 6, 4, 100000),
    (5, 7, 2, 140000),
    (6, 8, 1, 75000),
    (7, 9, 2, 100000),
    (8, 10, 1, 70000);