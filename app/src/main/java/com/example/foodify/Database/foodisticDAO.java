package com.example.foodify.Database;

import android.graphics.Point;

import androidx.room.*;

import com.example.foodify.Database.Entities.PointEntity;
import com.example.foodify.Database.Entities.PromotionEntity;
import com.example.foodify.Database.Entities.ShoppingListEntity;
import com.example.foodify.Database.Entities.UserEntity;
import com.example.foodify.ShoppingList.ShoppingList;

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
