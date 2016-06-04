package com.example.pc.myapplication.backend;


import org.apache.commons.codec.binary.Base64;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdk.nashorn.internal.codegen.CompilerConstants;


public class DataBaseService {

    public static final String className = "com.mysql.jdbc.Driver";
    public static final String chaine = "jdbc:mysql://localhost:3306/bd1";
    public static final String username = "root";
    public static final String password = "";

    public Connection connecter() {

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

    public int addBooks(List <Book> bookList) {
        Connection conn = connecter();
        PreparedStatement  pst = null;
        String query ="insert into book values(?,?,?)";
        int i=-1;
        try {
            conn.setAutoCommit(false);
            pst = conn.prepareStatement(query);
        for(int j=0;j<bookList.size();j++) {
            Book book = bookList.get(j);
            pst.setString(1,book.getTitle());
            pst.setString(2,book.getAuthor());
            pst.setString(3,book.getCategory());
            pst.addBatch();
        }
            i  =  pst.executeBatch()[0];
           conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        try {
            if(pst!=null)
                pst.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public int addBook(Book book)   {
        Connection conn = connecter();
        String query = "insert into book values(?,?,?)";
        PreparedStatement st = null;
        int i=-1;
        try {
            st = conn.prepareStatement(query);
            st.setString(1,book.getTitle());
            st.setString(2,book.getCategory());
            st.setString(3, book.getAuthor());
             i = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(st!=null)
                st.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    public List<Book> getBookList()  {
        Connection connection = connecter();
        List <Book> list = new ArrayList<>();
        String query="select * from livre";
        Statement st = null;
        try {
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.first()) {
            while (!rs.isAfterLast()) {
                Book book = null;
                book = new Book(rs.getString(1), rs.getString(2),
                        rs.getString(3));

                list.add(book);
                rs.next();
            }
            }
            st.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


return list;

    }
}