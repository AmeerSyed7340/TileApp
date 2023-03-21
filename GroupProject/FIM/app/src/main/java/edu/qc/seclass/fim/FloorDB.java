package edu.qc.seclass.fim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FloorDB extends SQLiteOpenHelper{
    //increment if failures arise in insertion of data or when changing the schema
    //version 1: 04/21 - Hammad's attempt at database building after messing up and starting over
    public static final int dbversion = 10;

    //name of database
    private static String dbName = "FloorDB";

    //table names
    public static String type_tbl = "Type";
    public static String category_tbl = "Category";
    public static String species_tbl = "Species";
    public static String floor_tbl = "Floor";
    public static String store_tbl = "Store";
    public static String inv_tbl = "Inventory";
    public static String guest_tbl = "Guest";
    public static String empl_tbl = "Employee";

    //column names (per table)

    //Type table
    public static String type_id = "Type_ID";
    public static String type_name = "Type_Name";
    public static String type_catID = "Type_CatID";

    //Category table
    public static String cat_id = "Category_ID";
    public static String cat_name = "Category_Name";

    //Species table
    public static String species_id = "Species_ID";
    public static String species_name = "Species_Name";
    public static String species_woodID = "Species_WoodID";

    //Floor table
    public static String floor_id = "Floor_ID";
    public static String floor_catID = "Floor_CatID";
    public static String floor_typeID = "Floor_TypeID";
    public static String floor_speciesID = "Floor_SpeciesID"; //can be null
    public static String floor_color = "Floor_Color";
    public static String floor_size = "Floor_Size";
    public static String floor_brand = "Floor_Brand";
    public static String floor_price = "Floor_Price";

    //Store table
    public static String store_id = "Store_ID";
    public static String store_name = "Store_Name";
    public static String store_addr = "Store_Address";

    //Inventory table
    public static String inv_id = "Inventory_ID";
    public static String inv_storeID = "Inventory_StoreID";
    public static String inv_floorID = "Inventory_FloorID";
    public static String inv_quantity = "Inventory_Quantity";

    //Guest Table
    public static String guest_id = "Guest_ID";
    public static String guest_name = "Guest_Name";
    public static String guest_email = "Guest_Email";
    public static String guest_pwrd = "Guest_Password";

    //Employee Table
    public static String empl_id = "Employee_ID";
    public static String empl_uName = "Employee_UserName";
    public static String empl_pwrd = "Employee_Password";

    List<Floors> floorsList = new ArrayList<>();

    public FloorDB(Context context){
        super(context, dbName, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Type (\n" +
                "   Type_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Type_Name TEXT NOT NULL UNIQUE \n " +
                "   ,Type_CatID INT" + ");");
        db.execSQL("CREATE TABLE Category (\n" +
                "   Category_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Category_Name TEXT NOT NULL UNIQUE" + ");");
        db.execSQL("CREATE TABLE Species (\n" +
                "   Species_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Species_Name TEXT NOT NULL UNIQUE \n " +
                "   ,Species_WoodID INT" + ");");
        db.execSQL("CREATE TABLE Floor (\n" +
                "   Floor_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Floor_Color TEXT NOT NULL \n" +
                "   ,Floor_Size TEXT NOT NULL \n" +
                "   ,Floor_Brand TEXT NOT NULL \n" +
                "   ,Floor_Price TEXT NOT NULL \n" +
                "   ,Floor_CatID INT \n" +
                "   ,Floor_TypeID INT \n " +
                "   ,Floor_SpeciesID INT" + ");");
        db.execSQL("CREATE TABLE Store (\n" +
                "   Store_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Store_Name TEXT NOT NULL UNIQUE \n " +
                "   ,Store_Address TEXT NOT NULL" + ");");
        db.execSQL("CREATE TABLE Inventory (\n" +
                "   Inventory_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Inventory_Quantity INT NOT NULL \n " +
                "   ,Inventory_StoreID INT \n" +
                "   ,Inventory_FloorID INT" + ");");
        db.execSQL("CREATE TABLE Guest (\n" +
                "   Guest_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Guest_Name TEXT NOT NULL UNIQUE \n " +
                "   ,Guest_Email TEXT NOT NULL \n" +
                "   ,Guest_Password TEXT NOT NULL" + ");");
        db.execSQL("CREATE TABLE Employee (\n" +
                "   Employee_ID INTEGER PRIMARY KEY AUTOINCREMENT \n " +
                "   ,Employee_UserName TEXT NOT NULL UNIQUE \n " +
                "   ,Employee_Password TEXT NOT NULL \n" + ");");

        db.execSQL("INSERT INTO Guest (Guest_Name, Guest_Email, Guest_Password) VALUES ('John Doe', 'jdoe123@gmail.com', 'password');");
        db.execSQL("INSERT INTO Employee (Employee_UserName, Employee_Password) VALUES ('employee1', 'secretPass');");
        //db.execSQL("INSERT INTO Category (Category_Name) VALUES ('Tile'), ('Stone'), ('Wood'), ('Laminate'), ('Vinyl');");
        //db.execSQL("INSERT INTO Type (Type_Name, Type_CatID) VALUES ('Porcelain', 1), ('Ceramic', 1), ('Resin', 1), ('Marble', 2), ('Pebble', 2), ('Slate', 2)," +
        //        " ('Solid', 3), ('Engineered', 3), ('Bamboo', 3), ('Regular Laminate', 4), ('Water Resistant', 4), ('Water Resistant', 5), ('Water Proof', 5);");
        //db.execSQL("INSERT INTO Species (Species_Name) VALUES ('Oak'), ('Hickory'), ('Maple');");
        //db.execSQL("INSERT INTO Store (Store_Name, Store_Address) VALUES ('The Store Depot', '73-52 Flatbush Ave, Brooklyn NY'), ('The Floor Store', '10504 S 15th St, Bellevue NH')," +
          //      " ('The Store of Floors', '6075 18 Mile Rd, Sterling Heights MI');");

        db.execSQL("INSERT INTO Category SELECT 1 AS Category_ID, 'Tile' AS Category_Name UNION SELECT 2, 'Stone' UNION SELECT 3, 'Wood' UNION SELECT 4, 'Laminate' UNION SELECT 5, 'Vinyl'");
        db.execSQL("INSERT INTO Type SELECT 1 AS Type_ID, 'Porcelain' AS Type_Name , 1 AS Type_CatID UNION SELECT 2, 'Ceramic', 1 UNION SELECT 3, 'Resin', 1 UNION SELECT 4, 'Marble', 2 UNION SELECT 5, 'Pebble', 2 " +
                "UNION SELECT 6, 'Slate', 2 UNION SELECT 7, 'Solid', 3 UNION SELECT 8, 'Engineered', 3 UNION SELECT 9, 'Bamboo', 3 UNION SELECT 10, 'Regular Laminate', 4 UNION SELECT 11, 'Water Resistant', 4 " +
                "UNION SELECT 12, 'Water Resistant Vinyl', 5 UNION SELECT 13, 'Water Proof', 5");
        db.execSQL("INSERT INTO Species SELECT 1 AS Species_ID, 'Oak' AS Species_Name, 3 AS Species_WoodID UNION SELECT 2, 'Hickory', 3 UNION SELECT 3, 'Maple', 3");
        db.execSQL("INSERT INTO Store SELECT 1 AS Store_ID, 'The Store Depot' AS Store_Name, '73-52 Flatbush Ave, Brooklyn NY' AS Store_Address UNION SELECT 2, 'The Floor Store', '10504 S 15th St, Bellevue NH' " +
                "UNION SELECT 3, 'The Store of Floors', '6075 18 Mile Rd, Sterling Heights MI'");

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query1 = "DROP TABLE IF EXISTS Type";
        String query2 = "DROP TABLE IF EXISTS Category";
        String query3 = "DROP TABLE IF EXISTS Species";
        String query4 = "DROP TABLE IF EXISTS Floor";
        String query5 = "DROP TABLE IF EXISTS Store";
        String query6 = "DROP TABLE IF EXISTS Inventory";
        String query7 = "DROP TABLE IF EXISTS Guest";
        String query8 = "DROP TABLE IF EXISTS Employee";
        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        db.execSQL(query4);
        db.execSQL(query5);
        db.execSQL(query6);
        db.execSQL(query7);
        db.execSQL(query8);
        onCreate(db);
    }

    public boolean checkEmployeeCredentials(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Employee WHERE Employee_UserName = ? AND Employee_Password = ?", new String[]{userName, password});
        if(cursor.getCount() == 1){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkGuestCredentials(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isGuest = false;
        String checkUser = "", checkPass = "";

        Cursor cursor = db.rawQuery("SELECT * FROM Guest WHERE Guest_Email = ? AND Guest_Password = ?", new String[]{userName, password});
        if(cursor.getCount() == 1){
            return true;
        } else {
            return isGuest;
        }
    }

    public boolean newGuestSignUp(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(guest_name, name);
        contentValues.put(guest_email, email);
        contentValues.put(guest_pwrd, password);
        long result = db.insert(guest_tbl, null, contentValues);

        if(result == -1){
            return false;
        } else {
            return true;
        }
    }



    private int getStoreID(String selectedStore) {
        SQLiteDatabase db = this.getReadableDatabase();
        int storeID = 0;
        Cursor cursor = db.rawQuery("SELECT Store_ID FROM Store WHERE Store_Name = ?", new String[]{selectedStore});
        if(cursor.moveToFirst()){
            do {
                storeID = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return storeID;
    }



    private int getSpeciesID(String species) {
        SQLiteDatabase db = this.getReadableDatabase();
        int speciesID = 0;
        Cursor cursor = db.rawQuery("SELECT Species_ID FROM Species WHERE Species_Name = ?", new String[]{species});
        if(cursor.moveToFirst()){
            do {
                speciesID = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return speciesID;
    }

    private int getTypeID(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        int typeID = 0;
        Cursor cursor = db.rawQuery("SELECT Type_ID FROM Type WHERE Type_Name = ?", new String[]{type});
        if(cursor.moveToFirst()){
            do {
                typeID = cursor.getInt(0);
            } while (cursor.moveToNext());
        }

        return typeID;
    }

    private int getCatID(String category) {
        SQLiteDatabase db = this.getReadableDatabase();
        int catID = 0;
        Cursor cursor = db.rawQuery("SELECT Category_ID FROM Category WHERE Category_Name = ?", new String[]{category});
        if(cursor.moveToFirst()){
            do {
                catID = cursor.getInt(0);
            } while (cursor.moveToNext());
        }

        return catID;
    }



    private int addToFloorTable(String category, String type, String species, String brand, String size, String color, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        int catID = getCatID(category);
        int typeID = getTypeID(type);
        int speciesID = -1;
        if(!(species.equalsIgnoreCase("null"))){
            speciesID = getSpeciesID(species);
        }

        if(catID == 0 || typeID == 0 || speciesID == 0){
            return -1;
        } else {
            ContentValues cv = new ContentValues();
            cv.put(floor_color, color);
            cv.put(floor_size, size);
            cv.put(floor_brand, brand);
            cv.put(floor_price, price);
            cv.put(floor_catID, catID);
            cv.put(floor_typeID, typeID);
            if (speciesID != -1) {
                cv.put(floor_speciesID, speciesID);
            }
            long result = db.insert(floor_tbl, null, cv);

            if (result == -1) {
                return -1;
            } else {
                return (int) result;
            }
        }
    }

    public boolean addNewFloorToInv(String selectedStore, String category, String type, String species, String brand, String size, String color, String price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        int isFloorAdded = addToFloorTable(category, type, species, brand, size, color, price);
        int storeID = getStoreID(selectedStore);


        if(isFloorAdded == -1 || storeID == 0){
            return false;
        } else {
            cv.put(inv_quantity, quantity);
            cv.put(inv_storeID, storeID);
            cv.put(inv_floorID, isFloorAdded);
            long result = db.insert(inv_tbl, null, cv);

            if(result == -1){
                return false;
            } else {
                return true;
            }
        }
    }
    public List<Floors> getALlFloors(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Floors> floorsList = new ArrayList<>();
        int id = 0;
        String cat = "", type = "", size = "", price = "";
        Cursor cursor = db.rawQuery("SELECT Floor_ID, Category_Name, Type_Name, Floor_Size, Floor_Price FROM  Floor INNER JOIN Category ON Category.Category_ID = Floor.Floor_CatID INNER JOIN Type ON Type.Type_ID = Floor.Floor_TypeID", null);
        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(0);
                cat = cursor.getString(1);
                type = cursor.getString(2);
                size = cursor.getString(3);
                price = cursor.getString(4);
                Floors floor = new Floors(id, cat, type, size, price);
                floorsList.add(floor);
            } while(cursor.moveToNext());
        }
        return floorsList;
    }

    public String getCatName(int floorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String catName = "";
        Cursor cursor = db.rawQuery("SELECT Category_Name FROM Category INNER JOIN Floor ON Category.Category_ID = Floor.Floor_CatID AND Floor_ID = " + floorID, null);
        if(cursor.moveToFirst()){
            do {
                catName = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return catName;
    }

    public String getTypeName(int floorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String typeName = "";
        Cursor cursor = db.rawQuery("SELECT Type_Name FROM Type INNER JOIN Floor ON Type.Type_ID = Floor.Floor_TypeID AND Floor_ID = " + floorID, null);
        if(cursor.moveToFirst()){
            do {
                typeName = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return typeName;
    }

    public String getSpeciesName(int floorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String speciesName = "null";
        Cursor cursor = db.rawQuery("SELECT Species_Name FROM Species INNER JOIN Floor ON Species.Species_ID = Floor.Floor_SpeciesID AND Floor_ID = " + floorID, null);
        if(cursor.moveToFirst()){
            do {
                speciesName = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return speciesName;
    }

    public String getColor(int floorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String color = "";
        Cursor cursor = db.rawQuery("SELECT Floor_Color FROM Floor WHERE Floor_ID = ?", new String[]{""+floorID});
        if(cursor.moveToFirst()){
            do {
                color = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return color;
    }

    public String getSize(int floorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String size = "";
        Cursor cursor = db.rawQuery("SELECT Floor_Size FROM Floor WHERE Floor_ID = ?", new String[]{""+floorID});
        if(cursor.moveToFirst()){
            do {
                size = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return size;
    }

    public String getBrand(int floorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String brand = "";
        Cursor cursor = db.rawQuery("SELECT Floor_Brand FROM Floor WHERE Floor_ID = ?", new String[]{""+floorID});
        if(cursor.moveToFirst()){
            do {
                brand = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return brand;
    }

    public String getPrice(int floorID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String price = "";
        Cursor cursor = db.rawQuery("SELECT Floor_Price FROM Floor WHERE Floor_ID = ?", new String[]{""+floorID});
        if(cursor.moveToFirst()){
            do {
                price = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return price;
    }

    public boolean updateFloor(int floorID, String categoryText, String typeText, String speciesText, String colorText, String sizeText, String brandText, String priceText) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(floor_catID, getCatID(categoryText));
        cv.put(floor_typeID, getTypeID(typeText));
        if(!(speciesText.equalsIgnoreCase("null"))){
            cv.put(floor_speciesID, getSpeciesID(speciesText));
        }
        cv.put(floor_color, colorText);
        cv.put(floor_size, sizeText);
        cv.put(floor_brand, brandText);
        cv.put(floor_price, priceText);
        long result = db.update(floor_tbl, cv, "Floor_ID=?", new String[]{""+floorID});
        if(result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean deleteFloor(int floorID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(floor_tbl, "Floor_ID = ?", new String[]{""+floorID}) > 0;
    }
}
