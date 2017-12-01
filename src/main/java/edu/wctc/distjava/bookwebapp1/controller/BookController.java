/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.controller;

import edu.wctc.distjava.bookwebapp1.model.Author;
import edu.wctc.distjava.bookwebapp1.model.AuthorService;
import edu.wctc.distjava.bookwebapp1.model.Book;
import edu.wctc.distjava.bookwebapp1.model.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author alexsmith
 */
@WebServlet(name = "BookController", urlPatterns = {"/bookController"}) // was modified from book
public class BookController extends HttpServlet {

    public static final String ACTION = "action";
    public static final String LIST_ACTION = "list";
    public static final String ADD_EDIT_DELETE_ACTION = "addEditDelete";
    public static final String SUBMIT_ACTION = "submit";
    public static final String SAVE_ACTION = "save";
    public static final String CANCEL_ACTION = "cancel";
    public static final String ADD_ACTION = "Add";
    public static final String ADD_UPDATE_ACTION = "addUpdate";
    public static final String REMOVE_ACTION = "Remove";
    public static final String FIND_ACTION = "find";
    public static final String UPDATE_ACTION = "Edit";

    public static final String ADD_EDIT_PAGE = "/editbook.jsp";

    public static final String LIST_PAGE = "/booklist.jsp";

    
    private BookService bookService;
    
   
    private AuthorService authorService;

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
        try {
            String action = request.getParameter(ACTION);

//            AuthorServiceOld authorService
//                    = null;
            List<Book> bookList = null;
            Book book = null;
            if (action.equalsIgnoreCase(LIST_ACTION)) {
                bookList = bookService.findAll();
                request.setAttribute("bookList", bookList);

            } else if (action.equalsIgnoreCase(ADD_EDIT_DELETE_ACTION)) {
                String submit = request.getParameter(SUBMIT_ACTION);
                switch (submit) {
                    case ADD_UPDATE_ACTION:
                        String[] bookIds = request.getParameterValues("bookId");
                        if (bookIds == null) {
                            //Go to create page  
                        } else {
                            //Must be an edit
                            String bookId = bookIds[0];
                            book = bookService.findById(bookId);
                            request.setAttribute("book", book);

                        }
                        List<Author> authorList = authorService.findAll();
                        request.setAttribute("authorList", authorList);
                        destination = ADD_EDIT_PAGE;
                        break;
                    case REMOVE_ACTION:
                        String[] ids = request.getParameterValues("bookId");

                        for (String s : ids) {
                          //  bookService.deleteBook(s);

                        }

                        break;

                    case SAVE_ACTION:

                        String bookId = request.getParameter("bookId");
                        String title = request.getParameter("title");
                        String isbn = request.getParameter("isbn");
                        String authorId = request.getParameter("authorId");

                      //  bookService.addOrUpdateNewBook(bookId, title, isbn, authorId);

                        break;

                    default:
                        System.out.println("Debug");
                }

                this.refreshListPage(request, bookService);
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
    
    @Override
    public void init() throws ServletException {
        ServletContext sctx = getServletContext();
        
        WebApplicationContext ctx 
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        bookService = (BookService) ctx.getBean("bookService");
        authorService = (AuthorService) ctx.getBean("authorService");

    }

}
