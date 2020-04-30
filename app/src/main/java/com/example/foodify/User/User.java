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
    FoodStyle eating_habits;    // Vegan, veganistic or omnivore
}
