package me.kglawrence.keri.ibrowse;

import com.orm.SugarRecord;

/**
 * Created by kglawrence on 2/12/16.
 */
public class Users extends SugarRecord {

    int number;
    String firstName;
    String lastName;
    int readingLevel;
    String color;
    String shape;

    public Users() {
    }

    public Users(int number, String firstName, String lastName, int readingLevel, String color, String shape) {
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.readingLevel = readingLevel;
        this.color = color;
        this.shape = shape;
    }
}
