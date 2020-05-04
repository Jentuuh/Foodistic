package com.example.foodify.Database;

import androidx.annotation.NonNull;
import androidx.room.*;


/**
 * @author jentevandersanden
 * Class that represents the row ReminderEntity in the table Reminders */
@Entity(tableName = "Users")
public class UserEntity {
    @PrimaryKey @NonNull
    public String primKey;
}
