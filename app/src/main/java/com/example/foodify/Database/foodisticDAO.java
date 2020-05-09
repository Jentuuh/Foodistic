package com.example.foodify.Database;

import androidx.room.*;

import com.example.foodify.Database.Entities.CommentEntity;
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
     * Gets user with a certain name from the database
     * @param f_name: the name of the user we're looking for
     * @return
     */
    @Query("SELECT * FROM Users WHERE firstname LIKE :f_name")
    UserEntity getUserByName(String f_name);

    /**
     * Gets user with a certain email address from db
     * @param mail : The email the user must have.
     * @return
     */
    @Query("SELECT * FROM Users WHERE email LIKE :mail")
    UserEntity getUserByEmail(String mail);

    /**
     * Inserts a new UserEntity (row) into the database
     * @param user : user to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createUser(UserEntity user);

    /**
     * Deletes all users from the database
     */
    @Query("DELETE FROM Users")
    void deleteUsers();


    /**
     * //////////////
     * PRODUCT QUERYS
     * //////////////
     */
    /**
     * Gets information about a product with a certain ID
     * @param p_name
     */
    @Query("SELECT * FROM Products WHERE name LIKE :p_name LIMIT 1")
    ProductEntity getProduct(String p_name);



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
     * Deletes all products from the database
     */
    @Query("DELETE FROM Products")
    void deleteProducts();




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
     * Deletes all shopping lsits from the database
     */
    @Query("DELETE FROM ShoppingLists")
    void deleteShoppingLists();


    /**
     * //////////////////////////////
     * ITEMS ON SHOPPING LIST QUERYS
     * /////////////////////////////
     */

    /**
     * Gets all items on a certain list
     * @param list_name : list that we want the items from
     * @return
     */
    @Query("SELECT * FROM ProductsOnList WHERE listname LIKE :list_name")
    List<ProductOnListEntity> getItemsOnList(String list_name);

    @Query(("SELECT * FROM ProductsOnList WHERE listname LIKE :list_name AND productname LIKE :product_name"))
    ProductOnListEntity getItemOnList(String list_name, String product_name);

    /**
     * Updates a product's quantity when + or - was pressed in the shopping list UI.
     * @param list_name : list that we want to update

     * @param new_quantity : The updated quantity
     */
    @Query("UPDATE ProductsOnList SET quantity = :new_quantity WHERE listname LIKE :list_name AND productname LIKE :product_name")
    void updateProductQuantity(String list_name, int new_quantity, String product_name);

    @Query("UPDATE ProductsOnList SET checked = :check WHERE listname LIKE :list_name AND productname LIKE :product_name")
    void updateProductChecked(boolean check, String list_name, String product_name);

    @Query("UPDATE ProductsOnList SET checked = :check WHERE listname LIKE :list_name")
    void uncheckAll(boolean check, String list_name);

    /**
     * Insert a new product into a certain list
     * @param new_product_on_list
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addProductToList(ProductOnListEntity new_product_on_list);

    /**
     * Removes a product on a certain list
     * @param list_name : List to remove from
     */
    @Query("DELETE FROM ProductsOnList WHERE listname LIKE :list_name AND productname LIKE :product_name ")
    void removeProductFromList( String list_name, String product_name);

    /**
     * Gets the quantity of a product on the list
     * @param product_name : Product that contains the quantity
     * @return
     */
    @Query("SELECT quantity FROM ProductsOnList WHERE productname LIKE :product_name AND listname LIKE :list_name")
    int getProductQuantity(String product_name , String list_name);

    /**
     * Deletes all products that are on lists from the database
     */
    @Query("DELETE FROM ProductsOnList")
    void deleteProductsOnLists();



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
     * Deletes all points from the database
     */
    @Query("DELETE FROM Points")
    void deletePoints();


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

    /**
     * Deletes all promotions from the database
     */
    @Query("DELETE FROM Promotions")
    void deletePromotions();

    /**
     * //////////////
     * COMMENT QUERYS
     * //////////////
     */

    /**
     * Gets all comments for a certain product.
     * @param product_name : the product to get the comments for.
     * @return
     */
    @Query("SELECT * FROM Comments WHERE productname = :product_name")
    List<CommentEntity> getCommentsForProduct(String product_name);

    /**
     * Inserts a new comment into the database.
     * @param new_comment : the new comment to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void createComment(CommentEntity new_comment);

    /**
     * Deletes all comments from the database
     */
    @Query("DELETE FROM Comments")
    void deleteComments();

}
