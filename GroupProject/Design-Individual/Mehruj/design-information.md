# Software Design for a Chain of Floor Supply
A software design for a chain of Floor Supply Stores, an application for managing inventory whose requirements are :
identify and refine 
a.classes, 
b.attributes, 
c. operations, 
d.relationships in design 

# Meeting Requirements
1.A user of the system should be able to see each store's offering and the amount of product in stock by square feet.
--Created the user class, that can search for products using searchProduct() operation that will search from the Store class the list of products. It can see the stock and search the product amount by using these two operations seeStock() and searchProductAmount(). 
The store class uses the Floor class for getting the product list. 
User class is using Floor class as well. 

2.The application must allow employees to add new products to the system. As well as
delete and edit them.
--Employees class is created and it has the employeeID as attribute. It also consists of addProduct(), deleteProduct() and editProduct() operations who take in Floor as parameter. 

3.The different categories of floors available are tile, stone, wood, laminate and vinyl. 
--Tile, Stone, Wood, Laminate, Vinyl classes are created. All of them have getType() operations. Tile and Stone have material attribute; Wood has species attribute, Laminate has isWaterproof boolean attribute(checks whether regular or water-proof), Vinyl has checkWaterBehavior(checks whether water-resistant or water-proof) attribute.

4.The application must contain a database (DB) of floors.
--Not considered because it does not affect the design directly

5.Users must be able to search for products by picking from a hierarchical list, where the first level is the floor category, and the second level is the floor type.
--User class has searchProduct() and seeStock() method. It is connceted to getCategoryName() method in Floor, that will give the category and then getType() in Tile,Stone,Wood,Laminate,and Vinyl. 

6.Users must also be able to specify an item by typing its name (search functionality).
--User class has searchProduct() operation taking Floor as parameter. 

7.All floors regardless of their category have an associated color, size, brand, type and price.
-- Floor class has color, size, brand, price attributes and the child classes, Tile,Stone,Wood,Laminate,and Vinyl all have type atttribute. 

8.Categories tile and stone have different materials they are made of, eg. Tile - porcelain, ceramic, resin; Stone - marble, pebble, 
-- Tile and Stone classes have material attribute

9.Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak,hickory, maple, etc.)
--Wood class has both type and Species attribute. 

10.Laminate can be regular laminate or water resistant, whereas vinyl can be water resistant or waterproof.
--Laminate class has checkWaterProof attribute to check whether it is regular or water-proof. 
Vinyl has checkWaterBehavior attribute to chcek whether it is water-resistant or water-proof. 

11.The User Interface (UI) must be intuitive and responsive.
--This requirement was not implemented as it doesnt directly contribute to the design of the system. 












  