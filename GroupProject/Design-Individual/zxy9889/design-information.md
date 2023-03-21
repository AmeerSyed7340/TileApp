1. As a user of the system I want to be able to see each store's offering and the amount of product in stock by square feet. 
    --To implement the requirement, we need to create a class called ItemView that make users see each store's offering and the amount of prodcut in stock. 

2. The application must allow employees to add new products to the system. As well as delete and edit them.
     --This is a relation between User and Item class. To implement the requirement, we need to create a class called User. Relationship needs to be added for Item and User. We can use the following methods, addProduct(), deleteItem(), editItem()

3. The different categories of floors available are tile, stone, wood, laminate and vinyl
      --To implement the requirement, we need to create a class called ItemType to show different categories of floors.

4. The application must contain a database (DB) of floors.
      --To implement the requirement, we need to create a class called Item which can have all items available in store.

5. Users must be able to search for products by picking from a hierarchical list, where the first level is the floor category, and the second level is the floor type. 
    --This requirement is not fully related to our design

6. Users must also be able to specify an item by typing its name (search functionality). 
    --This requirement is not fully related to our design

7. All floors regardless of their category have an associated color, size, brand, type and price 
   --This is related to ItemType. 

8. Categories tile and stone have different materials they are made of, e.g. Tile- porcelain, ceramic, resin; Stone - marble, pebble, slate 
    --To implement the requirement, we need to create a class called Material to separate different materials.

9. Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak, hickory, maple, etc.) 
     --To implement the requirement, we need to create a class called woodType to help user choose different types.

10. Laminate can be regular laminate or water resistant, whereas vinyl can be water resistant or waterproof 
    --This is related to ItemType. 

11. The User Interface (UI) must be intuitive and responsive.
      --This is an application UI requirement that we do not need to consider it.