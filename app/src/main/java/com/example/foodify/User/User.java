package com.example.foodify.User;

import com.example.foodify.Enums.FoodStyle;

import java.util.Date;

/**
 * @author jentevandersanden
 * This data class represents a user.
 */
public class User {

    private String mFirstName;
    private String mName;               // Name of the user.
    private Date mBirthday;             // Date of birth
    private String mAddress;            // Address of user
    private FoodStyle mEatingHabits;    // Vegan, vegetarian or omnivore

    /**
     * CONSTRUCTOR
     */
    User(String firstname, String name, Date birthday, FoodStyle eating_habits, String address){
        mFirstName = firstname;
        mName = name;
        mBirthday = birthday;
        mEatingHabits = eating_habits;
        mAddress = address;
    }


    /**
     * Getters and setters
     */
    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date mBirthday) {
        this.mBirthday = mBirthday;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public FoodStyle getEatingHabits() {
        return mEatingHabits;
    }

    public void setEatingHabits(FoodStyle mEatingHabits) {
        this.mEatingHabits = mEatingHabits;
    }
}
