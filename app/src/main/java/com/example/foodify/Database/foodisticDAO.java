package com.example.foodify.Database;

import androidx.room.*;

import com.example.foodify.Database.Entities.PointEntity;
import com.example.foodify.Database.Entities.PromotionEntity;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Database.Entities.ProductOnListEntity;
import com.example.foodify.Database.Entities.ShoppingListEntity;
import com.example.foodify.Database.Entities.UserEntity;

import java.util.List;

/**
 * @author jentevandersanden
 * This interface represents the functions that perform query's on the
 * Reminder Room database.
 */
@Dao
public interface foodisticDAO {


    /**
     * ////////////
     * USER QUERY'S
     * ////////////
     */


    /**
     * Gets all users with a certain name from the database
     * @param f_name: the name of the user we're looking for
     * @return
     */
    @Query("SELECT * FROM Users WHERE firstname LIKE :f_name")
    List<UserEntity> getUserByName(String f_name);

    /**
     * Inserts a new UserEntity (row) into the database
     * @param user : user to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createUser(UserEntity user);


    /**
     * //////////////
     * PRODUCT QUERYS
     * //////////////
     */
    /**
     * Gets information about a product with a certain ID
     * @param p_ID
     */
    @Query("SELECT * FROM Products WHERE ID LIKE :p_ID")
    ProductEntity getProduct(int p_ID);

    /**
     * Creates and inserts a new product into the db
     * @param product
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createProduct(ProductEntity product);

    /**
     * Gets all items from the database
     * @return
     */
    @Query("SELECT * FROM Products")
    List<ProductEntity> getAllProducts();


    /**
     * ////////////////////
     * SHOPPING LIST QUERYS
     * ////////////////////
     */

    /**
     * Gets all shopping lists saved on this device.
     * @return
     */
    @Query("SELECT * FROM ShoppingLists")
    List<ShoppingListEntity> getAllShoppingLists();

    /**
     * Inserts a new shopping list into the database.
     * @param newlist
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createShoppingList(ShoppingListEntity newlist);

    /**
     * Deletes a shopping list from the database
     * @param to_delete : list to be deleted.
     */
    @Query("DELETE FROM ShoppingLists WHERE name LIKE :to_delete")
    void deleteShoppingList (String to_delete);


    /**
     * //////////////////////////////
     * ITEMS ON SHOPPING LIST QUERYS
     * /////////////////////////////
     */

    /**
     * Gets all items on a certain list
     * @param list_ID : list that we want the items from
     * @return
     */
    @Query("SELECT * FROM ProductsOnList WHERE listID LIKE :list_ID")
    List<ProductOnListEntity> getItemsOnList(int list_ID);

    @Query(("SELECT * FROM ProductsOnList WHERE listID LIKE :list_ID AND productname LIKE :product_name"))
    ProductOnListEntity getItemOnList(int list_ID, String product_name);

    /**
     * Updates a product's quantity when + or - was pressed in the shopping list UI.
     * @param list_ID : list that we want to update

     * @param new_quantity : The updated quantity
     */
    @Query("UPDATE ProductsOnList SET quantity = :new_quantity WHERE listID LIKE :list_ID AND productname LIKE :product_name")
    void updateProductQuantity(int list_ID, int new_quantity, String product_name);

    @Query("UPDATE ProductsOnList SET checked = :check WHERE listID LIKE :list_ID AND productname LIKE :product_name")
    void updateProductChecked(boolean check, int list_ID, String product_name);

    @Query("UPDATE ProductsOnList SET checked = :check WHERE listID LIKE :list_ID")
    void uncheckAll(boolean check, int list_ID);

    /**
     * Insert a new product into a certain list
     * @param new_product_on_list
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addProductToList(ProductOnListEntity new_product_on_list);

    /**
     * Removes a product on a certain list
     * @param list_ID : List to remove from
     */
    @Query("DELETE FROM ProductsOnList WHERE listID LIKE :list_ID AND productname LIKE :product_name ")
    void removeProductFromList( int list_ID, String product_name);

    /**
     * Gets the quantity of a product on the list
     * @param product_id : Product that contains the quantity
     * @return
     */
    @Query("SELECT quantity FROM ProductsOnList WHERE productid LIKE :product_id AND listID LIKE :list_id")
    int getProductQuantity(int product_id , int list_id);




    /**
     * ////////////////////
     * SHOP POINT QUERYS
     * ////////////////////
     */

    /**
     * Gets all shops from the database
     * @return list of ShopPoints
     */
    @Query("SELECT * FROM Points")
    List<PointEntity> getAllShopPoints();

    /**
     * Inserts a new shop point into the database.
     * @param new_shop_point
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createShopPoint(PointEntity new_shop_point);

    /**
     * Update points of shops with a certain name from the database
     * @param f_shop: the name of the shop we're looking for
     * @param f_points: the new points of the shop
     * @return
     */
    @Query("UPDATE Points SET points = :f_points WHERE shop = :f_shop ")
    void setPointsByShop(String f_shop, int f_points);

    /**
     * ////////////////////
     * POINT PROMOTION QUERYS
     * ////////////////////
     */

    /**
     * Gets all promotions of given shop from the database
     * @return list of Promotions
     */
    @Query("SELECT * FROM Promotions WHERE shop LIKE :shop")
    List<PromotionEntity> getPromotionsByShop(String shop);

    /**
     * Inserts a new shop point into the database.
     * @param new_promotion
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createPromotion(PromotionEntity new_promotion);

}
