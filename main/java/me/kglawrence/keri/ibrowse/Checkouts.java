package me.kglawrence.keri.ibrowse;

import com.orm.SugarRecord;
import java.util.Date;

/**
 * Created by kglawrence on 2/12/16.
 */
public class Checkouts extends SugarRecord {

    int id;
    Date timeIn;
    Date timeOut;
    Books book;
    Users user;

    public Checkouts() {
    }

    public Checkouts(int id, Date timeIn, Date timeOut, Books book, Users user) {
        this.id = id;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.book = book;
        this.user = user;
    }

}
