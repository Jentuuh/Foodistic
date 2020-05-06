package com.example.foodify.Database;
import android.content.Context;

import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodify.Database.Entities.PointEntity;
import com.example.foodify.Database.Entities.ProductEntity;
import com.example.foodify.Database.Entities.ProductOnListEntity;
import com.example.foodify.Database.Entities.ShoppingListEntity;
import com.example.foodify.Database.Entities.UserEntity;


/**
 * @author jentevandersanden
 * Abstract class that represents the database holder for the database
 */
@Database(entities = {UserEntity.class, ShoppingListEntity.class, ProductOnListEntity.class, ProductEntity.class, PointEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    // Singleton instance of the database
    private static AppDatabase INSTANCE;
    public abstract foodisticDAO m_foodisticDAO();

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "Foodistic-Database").allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3).build();
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE reminder ADD COLUMN radius FLOAT NOT NULL DEFAULT 200");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Points ADD COLUMN logo INT NOT NULL DEFAULT 17301567 "); // Default ic_menu_gallery
        }
    };

    /**
     * Destroys the singleton instance of the database.
     */
    public static void destroyInstance(){
        INSTANCE = null;
    }

}

