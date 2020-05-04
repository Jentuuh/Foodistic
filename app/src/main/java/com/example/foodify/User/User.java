package com.example.foodify.User;

import com.example.foodify.Enums.FoodStyle;

import java.util.Date;

/**
 * @author jentevandersanden
 * This data class represents a user.
 */
public class User {
    String m_name;              // Name of the user.
    Date m_birthday;            // Date of birth
    FoodStyle m_eating_habits;    // Vegan, veganistic or omnivore

    /**
     * CONSTRUCTOR
     */
    public User(String name, Date birthday, FoodStyle eating_habits){
        m_name = name;
        m_birthday = birthday;
        m_eating_habits = eating_habits;
    }
}
