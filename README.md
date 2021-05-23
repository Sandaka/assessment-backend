# assessment-backend

Technical assessment - Development

==========REQUIREMENTS TO RUN THE BACKEND APPLICATION==============

    Install PostgreSQL latest version (https://www.postgresqltutorial.com/install-postgresql/)
    Create database using the following query "CREATE DATABASE assessment;" Note: My database username=postgres and password=postgres. then you can change them to your credentials on application.properties file.

Then add these initial products to the database,

INSERT INTO product ("product_name", "carton_price", "units_per_carton","currency","status") VALUES ('Penguin-ears',175.00,20,'LKR','active');

INSERT INTO product ("product_name", "carton_price", "units_per_carton","currency","status") VALUES ('Horseshoe',825.00,5,'LKR','active');

    Install Java 8
    Install Maven
    Install IntelliJ IDE

==========REQUIREMENTS TO RUN THE FRONTEND APPLICATION==============

    Install the latest version of Angular CLI
    Install NodeJS
    Install npm
    I have used Visual Studio Code to develop frontend application
    Execute "ng serve" command on terminal to start the application
    Then open your browser and type http://localhost:4200 on address bar

==========HOW TO USE?==============

    After starting the application you will be redirected to Product cards list window. then you can see the product details and you can select the buying option and enter the qty.

    If you want to add the selected item, click the update cart button and you can see updated cart on right side with calculated total prices.

    If you want to remove any item from the cart, go to product card and put qty as '0' then click the the update cart button. you will see updated cart with the prices accordingly. Also you can do the same thing when updating the qty.

    Those selected items will be added to the cart after considering all the pricing calculation conditions.

    In addition, if you click "Item List | Search" button on navigation bar, you can get all the products in database with quick searchable option.

