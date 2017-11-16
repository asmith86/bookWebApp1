/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.controller;

import edu.wctc.distjava.bookwebapp1.model.Book;
import edu.wctc.distjava.bookwebapp1.model.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alexsmith
 */
@WebServlet(name = "BookController", urlPatterns = {"/bookController"}) // was modified from book
public class BookController extends HttpServlet {
    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";
    
    public static final String LIST_PAGE = "/booklist.jsp";
    
    @EJB
    private BookService bookService;
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String destination = LIST_PAGE;
        try  {
           String action = request.getParameter(ACTION);

//            AuthorServiceOld authorService
//                    = null;
            List<Book> bookList = null;
            Book book = null;
            if (action.equalsIgnoreCase(LIST_ACTION)) {
                bookList = bookService.findAll();
                request.setAttribute("bookList", bookList);

            }
            
        } catch (Exception e) {
            destination = LIST_PAGE;
            request.setAttribute("errMessage", e.getMessage());
            
        }
        
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }
    
    private void refreshListPage(HttpServletRequest request, BookService bookService) throws SQLException, ClassNotFoundException, Exception {
        List<Book> bookList = bookService.findAll();
        request.setAttribute("bookList", bookList);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
