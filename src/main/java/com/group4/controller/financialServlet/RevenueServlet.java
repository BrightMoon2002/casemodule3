package com.group4.controller.financialServlet;

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
import java.sql.SQLException;
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
        HttpSession session = request.getSession(false);
        Account accountLogging = null;
        if (session != null) {
            accountLogging = (Account) session.getAttribute("accountLogging");

            double revenueTotalUser = 0;
            double revenueTotalAdmin = 0;
            List<Revenue> listRevenue = null;
            List<Revenue> listRevenueUser = null;
            System.out.println(accountLogging.getId());
            if (accountLogging.getRole().getId() == 1) {
                listRevenue = revenueService.findAllByAccountId(accountLogging.getId());
                listRevenueUser = revenueService.findAllNotByAccountId(accountLogging.getId());
                for (Revenue r : listRevenueUser) {
                    revenueTotalUser += r.getAmount();
                }
                for (Revenue r : listRevenue) {
                    revenueTotalAdmin += r.getAmount();
                }


                request.setAttribute("listRevenue", listRevenue);
                request.setAttribute("listRevenueUser", listRevenueUser);
                request.setAttribute("revenueTotalUser", revenueTotalUser);
                request.setAttribute("revenueTotalAdmin", revenueTotalAdmin);
                request.setAttribute("accountLogging", accountLogging);
                request.setAttribute("role", accountLogging.getRole().getId());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/list.jsp");
                requestDispatcher.forward(request, response);
            } else if (accountLogging.getRole().getId() == 2) {
                listRevenue = revenueService.findAllByAccountId(accountLogging.getId());
                for (Revenue r : listRevenue) {
                    revenueTotalUser += r.getAmount();
                }
                request.setAttribute("listRevenue", listRevenue);
                request.setAttribute("accountLogging", accountLogging);
                request.setAttribute("revenueTotalUser", revenueTotalUser);
                request.setAttribute("role", accountLogging.getRole().getId());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/list.jsp");
                requestDispatcher.forward(request, response);
            }



            } else {
            response.sendRedirect("view/error/error404.jsp");
        }
        }


    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createRevenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account accountLogging = null;
        if (session != null) {
            accountLogging = (Account) session.getAttribute("accountLogging");
            String type = request.getParameter("type");
            double amount = Double.parseDouble(request.getParameter("amount"));
            Date date = Date.valueOf(request.getParameter("date"));
            String description = request.getParameter("description");
            int account_id = accountLogging.getId();

            Account account = accountService.findById(account_id);

            Revenue revenue = new Revenue(type,description, amount, date, account);

            revenueService.save(revenue);

            listRevenue(request, response);
        }


    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Revenue existingRevenue = null;
        try {
            existingRevenue = revenueService.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession(false);
        Account accountLogging = null;
        if (session != null) {
            accountLogging = (Account) session.getAttribute("accountLogging");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/edit.jsp");
            request.setAttribute("revenue", existingRevenue);
            request.setAttribute("role", accountLogging.getRole().getId());
            requestDispatcher.forward(request, response);
        }




    }

    private void editRevenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Account accountLogging = null;
        if (session != null) {
            accountLogging = (Account) session.getAttribute("accountLogging");
            if (accountLogging.getRole().getId() == 1) {
                int id = Integer.parseInt(request.getParameter("id"));
                String type = request.getParameter("type");
                double amount = Double.parseDouble(request.getParameter("amount"));
                Date date = Date.valueOf(request.getParameter("date"));
                String description = request.getParameter("description");
                int account_id = Integer.parseInt(request.getParameter("account_id"));

                Account account = accountService.findById(account_id);

                Revenue revenue = new Revenue(id, type,description, amount, date, account);

                try {
                    revenueService.update(revenue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                listRevenue(request, response);
            } else if (accountLogging.getRole().getId() == 2) {
                int id = Integer.parseInt(request.getParameter("id"));
                String type = request.getParameter("type");
                double amount = 0;
                try {
                    amount = revenueService.findById(id).getAmount();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Date date = null;
                try {
                    date = revenueService.findById(id).getDate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String description = request.getParameter("description");
                int account_id = accountLogging.getId();

                Account account = accountService.findById(account_id);

                Revenue revenue = new Revenue(id, type,description, amount, date, account);

                try {
                    revenueService.update(revenue);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                listRevenue(request, response);
            }
        }


    }


    private void deleteRevenue(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            revenueService.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Revenue> revenues = null;
        try {
            revenues = revenueService.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/list.jsp");
        request.setAttribute("listRevenue", revenues);
        requestDispatcher.forward(request, response);


    }

    private void showFindById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/find.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findRevenueById(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Account accountLogging = null;
        if (session != null) {
            accountLogging = (Account) session.getAttribute("accountLogging");

            int id = Integer.parseInt(request.getParameter("id"));
            Revenue revenue = null;
            try {
                revenue = revenueService.findById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if  (revenue == null || revenue.getAccount().getId() != accountLogging.getId() ) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/find.jsp");
                request.setAttribute("message", "Couldn't find the revenue");
                requestDispatcher.forward(request, response);



            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/revenue/find.jsp");
                request.setAttribute("revenue", revenue);
                request.setAttribute("result", true);
                requestDispatcher.forward(request, response);

            }
        }
    }

}
