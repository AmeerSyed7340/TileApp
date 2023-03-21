## 1. As a user of the system I want to be able to see each store's offering and the amount of product in stock by square feet

To achieve this requirement, I implemented an User super class in which it has the functionality to browse every product that is in stock in a store. Each product also has a size attribute.

## 2. The application must allow employees to add new products to the system. As well as delete and edit them.

To achieve this requirement, I added an Employee subclass to the User class. The reason it being a subclass of User class is that an employee can also buys products from the store. The only difference between an employee and a customer is that an employee is able to add new products and delete or edit any existing products.

## 3. The different categories of floors available are tile, stone, wood, laminate and vinyl

To achieve this requirement, I added a Floor class first, then created 5 different subclasses to represent each different category.

## 4. The application must contain a database (DB) of floors.

To meet this requirement, I added a class Floor with attributes color, size, brand, type and price, and the database will keep track of them.

## 5. Users must be able to search for products by picking from a hierarchical list, where the first level is the floor category, and the second level is the floor type.

I do not think this should be considered in the design phase. This should be in the implementation phase instead, but I added a searchItems function into the User class to take care of this.

## 6. Users must also be able to specify an item by typing its name (search functionality).

Same as requirement 5. This is not be considered in the design phase.

## 7. All floors regardless of their category have an associated color, size, brand, type and price

To meet this requirement, I added color, size, brand, type and price as attributes to the class Floor.

## 8. Categories tile and stone have different materials they are made of, e.g. Tile - porcelain, ceramic, resin; Stone - marble, pebble, slate

To meet this requirement, I added materials as attributes in both TileFloor and StoneFloor classes.

## 9. Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak, hickory, maple, etc.)

To meet this requirement, I added type as an attribute in the WoodFloor class.

## 10. Laminate can be regular laminate or water resistant, whereas vinyl can be water resistant or waterproof

To meet this requirement, I added isWaterResistant as attributes in both LaminateFloor and VinylFloor classes.

## 11. The User Interface (UI) must be intuitive and responsive.

This requirement is not considered since it does not affect the design.