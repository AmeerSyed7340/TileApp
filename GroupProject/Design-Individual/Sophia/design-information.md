Requirements

1.  A user would need to be able to view the offerings of the store by square feet so I included an interface called "User" that includes a method called getSquareFeet()
2. An employee must be able to add, delete and edit floors so I've included a class called "Employee" that implements the "User" interface and has methods for adding deleting and editing floors
3. The class "Floor" contains attributes to store the different categories of floors
4. "Stock" represents the database of floors
5. Search functionality is created in the "User" interface and is implemented by Employees and Customers
6.  Because customers also need to to be able to search by name, a method, searchName() was added to the User interface
7. Floor Categories also have a color, size, brand, type and price so I added a sub class with those attributes
8. Tile and stone floors can be made of different materials such as porcelain, ceramic, marble, pebble, etc. so I've included subclasses for tile and stone that represent those attributes. 
9. Additional subclasses were added to represent the type and species of the wood floors
10. Within the categories class, boolean operations are included to represent if a laminate floor is water resistant and if a vinyl floor is water resistant or waterproof