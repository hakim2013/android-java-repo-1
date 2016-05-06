package dz.esi.backend.servlet;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.esi.backend.database.DataBaseService;
import dz.esi.backend.model.Book;

/**
 * Created by pc on 06/05/2016.
 */
public class GetBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String density=req.getParameter("density");
        List<Book> listBooks = new DataBaseService().getBooks(density);
        resp.getWriter().write(new Gson().toJson(listBooks));

    }
}
