/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.distjava.bookwebapp1.controller;

import edu.wctc.distjava.bookwebapp1.model.Author;

import edu.wctc.distjava.bookwebapp1.model.AuthorServiceOld;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
@WebServlet(name = "AuthorController", urlPatterns = {"/authorController"})
public class AuthorController extends HttpServlet {

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

    public static final String LIST_PAGE = "/authorlist.jsp";
    public static final String ADD_EDIT_PAGE = "/editpage.jsp";

    @EJB
    private AuthorServiceOld authorService;

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
        String destination = LIST_PAGE; //default
        try {
            String action = request.getParameter(ACTION);

//            AuthorServiceOld authorService
//                    = null;
            List<Author> authorList = null;
            Author author = null;
            if (action.equalsIgnoreCase(LIST_ACTION)) {
                authorList = authorService.getAuthorList();
                request.setAttribute("authorList", authorList);

            } else if (action.equalsIgnoreCase(ADD_EDIT_DELETE_ACTION)) {
                String submit = request.getParameter(SUBMIT_ACTION);
                switch (submit) {
                    case ADD_UPDATE_ACTION:
                        String[] authorIds = request.getParameterValues("authorId");
                        if (authorIds == null) {
                            //Go to create page  
                        } else {
                            //Must be an edit
                            String authorId = authorIds[0];
                            author = authorService.getUniqueAuthor(authorId);
                            request.setAttribute("author", author);

                        }
                        destination = ADD_EDIT_PAGE;
                        break;
                    case REMOVE_ACTION:
                        String[] ids = request.getParameterValues("authorId");

                        for (String s : ids) {
                            authorService.deleteAuthorRecord(s);

                        }

                        break;
                    case SAVE_ACTION:

                        String authorId = request.getParameter("authorId");
                        String authorName = request.getParameter("authorName");
                        String dateAdded = request.getParameter("dateAdded");

                        if (authorId == null || authorId.isEmpty()) {
                            authorService.addAuthorRecord(authorName, dateAdded);
                            

                        } else {
                            //Update logic
                              authorService.updateAuthorRecords(authorId, authorName, dateAdded);
                            
                        }

                        break;

                    case CANCEL_ACTION:
                        System.out.println("Cancel");
                        break;

                    default:
                        System.out.println("Debug");
                }

                this.refreshListPage(request, authorService);

            }

        } catch (Exception e) {
            destination = LIST_PAGE;
            request.setAttribute("errMessage", e.getMessage());

        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }

    private void refreshListPage(HttpServletRequest request, AuthorServiceOld authorService) throws SQLException, ClassNotFoundException, Exception {
        List<Author> authorList = authorService.getAuthorList();
        request.setAttribute("authorList", authorList);
    }

    //pre Add-Commit comment prior to creation of new branch.
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

    }

}
