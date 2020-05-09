package com.example.foodify.Database;

import android.graphics.Point;

import com.example.foodify.Database.Entities.CommentEntity;
import com.example.foodify.Database.Entities.PointEntity;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Database.Entities.PromotionEntity;
import com.example.foodify.Database.Entities.ShoppingListEntity;
import com.example.foodify.Database.Entities.UserEntity;
import com.example.foodify.ShoppingList.ShoppingList;

/**
 * @author jentevandersanden
 * This class can be used to add sample data to the database instance
 * or
 * delete them from the database instance
 */
public class DatabasePopulator{

    /**
     * Adds a new Product to the database
     * @param db : The database instance
     * @param product : The product to be inserted
     */
    public static void addProduct(AppDatabase db, ProductEntity product){
        db.m_foodisticDAO().createProduct(product);
    }

    /**
     * Adds a new Product to the database
     * @param db : The database instance
     * @param user: The user to be inserted
     */
    public static void addUser(AppDatabase db, UserEntity user){
        db.m_foodisticDAO().createUser(user);
    }

    /**
     * Adds a new comment to the database
     * @param db : The database instance
     * @param comment : The comment to be inserted
     */
    public static void addComment(AppDatabase db, CommentEntity comment){
        db.m_foodisticDAO().createComment(comment);
    }

    /**
     * Adds a new Product to the database
     * @param db : The database instance
     * @param promotion : The promotion to be inserted
     */
    public static void addPromotion(AppDatabase db, PromotionEntity promotion){
        db.m_foodisticDAO().createPromotion(promotion);
    }

    /**
     * Adds a new shopping list to the database
     * @param db : The database instance
     * @param list : The list to be inserted
     */
    public static void addShoppingList(AppDatabase db, ShoppingListEntity list){
        db.m_foodisticDAO().createShoppingList(list);
    }

    /**
     * Adds a new Product to the database
     * @param db : The database instance
     * @param point : The point to be inserted
     */
    public static void addPointEntity(AppDatabase db, PointEntity point){
        db.m_foodisticDAO().createShopPoint(point);
    }

    /**
     * Removes all data from the database
     */
    public static void clearDatabase(AppDatabase db){
        db.m_foodisticDAO().deleteComments();
        db.m_foodisticDAO().deletePoints();
        db.m_foodisticDAO().deleteProducts();
        db.m_foodisticDAO().deleteProductsOnLists();
        db.m_foodisticDAO().deletePromotions();
        db.m_foodisticDAO().deleteShoppingLists();
        db.m_foodisticDAO().deleteUsers();
    }


}
