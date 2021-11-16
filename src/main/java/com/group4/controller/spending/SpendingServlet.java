package com.group4.controller.spending;

import com.group4.model.account.Account;
import com.group4.model.financial.Spending;
import com.group4.service.accountService.AccountService;
import com.group4.service.spendingService.ISpendingDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SpendingServlet", value = "/spending")
public class SpendingServlet extends HttpServlet {
    private AccountService accountService = new AccountService();
    private ISpendingDAO spendingDAO = new ISpendingDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action==null){
        action = "";
    }
    switch (action){
        case "sort":
            try {
                showSortByAmount(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
        case "create":
            showCreateNew(request,response);
            break;
        case "search":
            try {
                showSearch(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            break;
        case "edit":
            try {
                editSpending(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        Date date = Date.valueOf(request.getParameter("date"));
        List<Spending> spendingList = spendingDAO.findByDate(date);
        request.setAttribute("spendings",spendingList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/findByDate.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showSortByAmount(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Spending> spendingList = spendingDAO.sortByAmount();
        request.setAttribute("spendings",spendingList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/sort.jsp");
        requestDispatcher.forward(request,response);
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

    private void editSpending(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id_account = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(id_account);
        String type = request.getParameter("type");
        Double amount = Double.valueOf(request.getParameter("amount"));
        Date date = Date.valueOf(request.getParameter("date"));
        String description = request.getParameter("description");
        Spending spending = new Spending(type,description,amount,date,account);
        spendingDAO.update(spending);
        response.sendRedirect("/spending");
    }

    private void createNewSpending(HttpServletRequest request, HttpServletResponse response) {
    }
}
