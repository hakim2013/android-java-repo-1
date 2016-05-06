package dz.esi.backend.database;

import org.apache.commons.codec.binary.Base64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dz.esi.backend.model.Author;
import dz.esi.backend.model.Book;

/**
 * Created by pc on 05/05/2016.
 */
public class DataBaseService {

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine = "jdbc:mysql://localhost:3306/bookdb";
    public static final String username = "root";
    public static final String password = "";

    public Connection connecter()   {

        Connection con = null;
        try {
            Class.forName(className);
            con = DriverManager.getConnection(chaine, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public List<Book> getBooks(String density)  {
        List<Book> listBooks = new ArrayList<Book>();
        List<Author> listAuthors = new ArrayList<Author>();
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "select * from book natural join bookimage natural join bookauth natural join author where density=?";
        try {
            conn = connecter();
            pst = conn.prepareStatement(query);
            pst.setString(1,density);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                while (!rs.isAfterLast()) {
                    Book book = new Book();
                    book.setIsbn(rs.getInt("isbn"));
                    book.setTitle(rs.getString("title"));
                    book.setEditor(rs.getString("editor"));
                    book.setYear(rs.getString("year"));
                    book.setSummary(rs.getString("summary"));
                    book.setCover(Base64.encodeBase64String(rs.getBytes("cover")));
                    book.setIconCover(Base64.encodeBase64String(rs.getBytes("iconCover")));
                    // ajouter la liste des autheurs
                   while (!(rs.isAfterLast())&&(rs.getInt("isbn") == book.getIsbn()) ) {
                        Author author = new Author(rs.getString("firstName"), rs.getString("lastName"));
                        listAuthors.add(author);
                        rs.next();
                    }
                    book.setListAuthors(listAuthors.toArray(new Author[listAuthors.size()]));
                    listBooks.add(book);
                    listAuthors.clear();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

            try {
                if (pst!=null) pst.close();
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }



        return listBooks;

    }
}
