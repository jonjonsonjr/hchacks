package me.kglawrence.keri.ibrowse;

import com.orm.SugarRecord;

/**
 * Created by kglawrence on 2/12/16.
 */
public class Books extends SugarRecord{

    int ISBN;
    int readingLevel;
    String author;
    String title;
    String image;

    public Books() {
    }

    public Books(int ISBN, int readingLevel, String author, String title, String image) {
        this.ISBN = ISBN;
        this.readingLevel = readingLevel;
        this.author = author;
        this.title = title;
        this.image = image;
    }

}
