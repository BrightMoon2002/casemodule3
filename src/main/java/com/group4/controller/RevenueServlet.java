package com.group4.controller;

import com.group4.model.account.Account;
import com.group4.model.financial.Revenue;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import com.group4.service.financial.Revenue.IRevenueService;
import com.group4.service.financial.Revenue.RevenueService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "RevenueServlet", value = "/revenue")
public class RevenueServlet extends HttpServlet {
    IRevenueService revenueService = new RevenueService();
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
                deleteRevenue(request, response);
                break;
            case "findById":
                showFindById(request, response);
                break;
            default:
                listRevenue(request, response);
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
                createRevenue(request, response);
                break;
            case "edit":
                editRevenue(request, response);
                break;
            case "findById":
                findRevenueById(request, response);
                break;
        }
    }


    private void listRevenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Revenue> listRevenue = revenueService.findAll();
        request.setAttribute("listRevenue", listRevenue);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("revenue/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("revenue/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createRevenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));
        Date date = Date.valueOf(request.getParameter("date"));
        String description = request.getParameter("description");
        int account_id = Integer.parseInt(request.getParameter("account_id"));

        Account account = accountService.findById(account_id);

        Revenue revenue = new Revenue(type,description, amount, date, account);

        revenueService.save(revenue);

    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Revenue existingRevenue = revenueService.findById(id);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("revenue/edit.jsp");
        request.setAttribute("revenue", existingRevenue);
        requestDispatcher.forward(request, response);


    }

    private void editRevenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));
        Date date = Date.valueOf(request.getParameter("date"));
        String description = request.getParameter("description");
        int account_id = Integer.parseInt(request.getParameter("account_id"));

        Account account = accountService.findById(account_id);

        Revenue revenue = new Revenue(id, type,description, amount, date, account);

        revenueService.update(revenue);

    }


    private void deleteRevenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        revenueService.deleteById(id);
        List<Revenue> revenues = revenueService.findAll();


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("revenue/list.jsp");
        request.setAttribute("listRevenue", revenues);
        requestDispatcher.forward(request, response);


    }

    private void showFindById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("revenue/find.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findRevenueById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Revenue revenue = revenueService.findById(id);

        if  (revenue == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("revenue/find.jsp");
            request.setAttribute("message", "Couldn't find the revenue");
            requestDispatcher.forward(request, response);



        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("revenue/find.jsp");
            request.setAttribute("revenue", revenue);
            request.setAttribute("result", true);
            requestDispatcher.forward(request, response);

        }
    }
}
