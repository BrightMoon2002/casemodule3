package com.group4.controller.financialServlet;

import com.group4.model.financial.Spending;
import com.group4.service.spending.ISpendingDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SpendingServlet", value = "/spending")
public class SpendingServlet extends HttpServlet {
    private ISpendingDAO spendingDAO = new ISpendingDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action==null){
        action = "";
    }
    switch (action){
        case "create":
            showCreateNew(request,response);
            break;
        default:
            try {
                listSpending(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
    }
    }

    private void showCreateNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher =request.getRequestDispatcher("view/spending/create.jsp");
        requestDispatcher.forward(request,response);
    }

    private void listSpending(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Spending> spendingList = spendingDAO.findAll();
        request.setAttribute("spendings",spendingList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/list.jsp");
        requestDispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                createNewSpending(request,response);
                break;
        }
    }

    private void createNewSpending(HttpServletRequest request, HttpServletResponse response) {
    }
}
