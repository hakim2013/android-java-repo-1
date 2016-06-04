package com.example.pc.myapplication.backend;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pc on 29/04/2016.
 */
public class AddbookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data ;
        StringBuilder result = new StringBuilder();
        InputStream inputStream = req.getInputStream();
        BufferedReader reader =
                new BufferedReader
                        (new InputStreamReader
                                (inputStream, "UTF-8"));
        while ((data = reader.readLine()) != null) {
            result.append(data);
        }
        Book book = new Gson()
                .fromJson(result.toString(),Book.class);
        int i = new DataBaseService().addBook(book);
        resp.getWriter().write(String.valueOf(i));


    }
}
