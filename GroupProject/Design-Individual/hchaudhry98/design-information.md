# Design Information

1. As a user of the system I want to be able to see each store's offering and the amount of product in stock by square feet.      

	Each store has an array of floors that have access to a subclass's attribute that has the square foot amount as well as the different floor types.     
	 
2. The application must allow employees to add new products to the system. As well as delete and edit them.     

	The methods, addNewItem, editItem, deleteItem, and addNewProduct allows the employee to edit the store's floor stock to their liking.     
	
3. The different categories of floors available are tile, stone, wood, laminate and vinyl.         

    There is a string in FloorCategory type that will be in Store class, and there will be multiple FloorCategory objects in Store, each of them representing the different cateogries of available flooring.     
    
4. The application must contain a database (DB) of floors.      

	Not considered because it does not affect the design directly.         
	
5. Users must be able to search for products by picking from a hierarchical list, where the
first level is the floor category, and the second level is the floor type.      

	In FloorCategory class, there is a method called selectCategory where the user will be presented with a list of different floor categories, upon choosing the desired category, the method will then invoke another method located in the same class, called selectMaterial, which will then allow the user to select the desired material.      
	
6. Users must also be able to specify an item by typing its name (search functionality).       

	There is a searchItem method in Store class that will allow for this functionality.       
	
7. All floors regardless of their category have an associated color, size, brand, type and
price.        

	All of these attributes have been added in the abstract Floor class.       
	
8. Categories tile and stone have different materials they are made of, e.g. Tile - porcelain,
ceramic, resin; Stone - marble, pebble, slate.        

	Proper attributes have been added to FloorMaterial class in order to handle the different materials that the floors will have.     
	
9. Wood floors have both a type (solid, engineered, bamboo, etc) and species (oak,
hickory, maple, etc.).        

	There is an attribute called categoryType in FloorCategory in order to handle the uniqueness of wood, for all other categories of floors, it will be null.      
	
10. Laminate can be regular laminate or water resistant, whereas vinyl can be water
resistant or waterproof.        

	The attribute categoryType in FloorCategory will be able to handle these two different types of laminate flooring and vinyl flooring.      
	
11. The User Interface (UI) must be intuitive and responsive.      

	Not considered because it does not affect the design directly.       