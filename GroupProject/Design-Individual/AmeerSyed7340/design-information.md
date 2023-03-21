
# Design information

1. This step is realized in the user class where regardless of the status of the user they are able to check the offering, get the amount and seach for what they are looking for.
2. Realized in the child class called Employee. The methods addProducts(), deleteProducts() and editProducts() are placed in this class to make sure only employees have access to these actions.
3. Different categories of floors are realized in the Floor Categories class where the attributes are color, size, brand, type and price regardless of the categories.
4. Not considered for this design.
5. The first level is shown by the aggregation from floor category to floor, while the floor type is realized below from specific categories.
6. The user class has a method/operation which allows users to search with name as a parameter.
7. The associated attributes of each category is included in the floor category class.
8. As we are not searching for tiles using the  material attributes, they are included in the Tile and Stone class as their own individual attributes.
9. Wood floors can have types and species so there is a relationship between the Wood class and the classes Species and Floor Type.
10. Laminate floor also have relationships with the floor type class.
11. Not considered because it does not affect the design directly.

