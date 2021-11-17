package com.group4.controller.spendingMonthLimitServlet;

import com.group4.model.account.Account;
import com.group4.model.limit.SpendingMonthLimit;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import com.group4.service.spending_month_limit.ISpendingMonthLimitService;
import com.group4.service.spending_month_limit.SpendingMonthLimitService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SpendingMonthLimitServlet", value = "/limit")
public class SpendingMonthLimitServlet extends HttpServlet {
    ISpendingMonthLimitService limitService = new SpendingMonthLimitService();
    IAccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteLimit(request, response);
                break;
            case "findById":
                showFindById(request, response);
                break;
            default:
                listLimit(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action ="";
        }

        switch (action) {
            case "create":
                createLimit(request, response);
                break;
            case "edit":
                editLimit(request, response);
                break;
            case "findById":
                findLimitById(request, response);
                break;
        }
    }


    private void listLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SpendingMonthLimit> listLimit = null;
        try {
            listLimit = limitService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listLimit", listLimit);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createLimit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double amount = Double.parseDouble(request.getParameter("amount"));
        int account_id = Integer.parseInt(request.getParameter("account_id"));

        Account account = accountService.findById(account_id);

        SpendingMonthLimit limit = new SpendingMonthLimit(amount, account);

        limitService.save(limit);

        listLimit(request, response);

    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        SpendingMonthLimit existingLimit = null;
        try {
            existingLimit = limitService.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/edit.jsp");
        request.setAttribute("limit", existingLimit);
        requestDispatcher.forward(request, response);


    }

    private void editLimit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        int account_id = Integer.parseInt(request.getParameter("account_id"));

        Account account = accountService.findById(account_id);

        SpendingMonthLimit limit = new SpendingMonthLimit(id, amount, account);

        try {
            limitService.update(limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        listLimit(request, response);

    }


    private void deleteLimit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            limitService.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<SpendingMonthLimit> limits = null;
        try {
            limits = limitService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/list.jsp");
        request.setAttribute("listLimit", limits);
        requestDispatcher.forward(request, response);


    }

    private void showFindById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/find.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findLimitById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        SpendingMonthLimit limit = null;
        try {
            limit = limitService.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if  (limit == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/find.jsp");
            request.setAttribute("message", "Couldn't find the limit");
            requestDispatcher.forward(request, response);



        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/limit/find.jsp");
            request.setAttribute("limit", limit);
            request.setAttribute("result", true);
            requestDispatcher.forward(request, response);

        }
    }

}
