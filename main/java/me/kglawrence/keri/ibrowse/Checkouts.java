package me.kglawrence.keri.ibrowse;

import com.orm.SugarRecord;
import java.util.Date;

/**
 * Created by kglawrence on 2/12/16.
 */
public class Checkouts extends SugarRecord {

    int id;
    long timeIn;
    long timeOut;
    Books book;
    Users user;

    public Checkouts() {
    }

    public Checkouts(int id, Books book, Users user) {
        this.id = id;
        Date date = new Date();
        timeOut = date.getTime();
        this.book = book;
        this.user = user;
    }

    public void setTimeIn() {
        Date date = new Date();
        timeIn = date.getTime();
    }


}
