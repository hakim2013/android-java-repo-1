package dz.esi.backend.model;

import java.io.Serializable;
import java.util.List;


public class Book {

    private int isbn ;
    private String title ;
    private Author[] listAuthors;
    private String editor;
    private String year;
    private  String summary;
    private String cover ;
    private String iconCover;

    public Author[] getListAuthors() {
        return listAuthors;
    }

    public void setListAuthors(Author[] listAuthors) {
        this.listAuthors = listAuthors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIconCover() {
        return iconCover;
    }

    public void setIconCover(String iconCover) {
        this.iconCover = iconCover;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}

