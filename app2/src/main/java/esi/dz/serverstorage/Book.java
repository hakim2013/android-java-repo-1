package esi.dz.serverstorage;

import android.graphics.Bitmap;

/**
 * Created by pc on 22/04/2016.
 */
public class Book {

    String title;
    String category;
    String author ;

    public Book(String title, String category, String author) {
        this.title = title;
        this.category = category;
        this.author = author;

    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


}




