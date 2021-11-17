package com.group4.controller.spending;

import com.group4.model.account.Account;
import com.group4.model.financial.Revenue;
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
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "sort":
                try {
                    showSortByAmount(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "create":
                showCreateNew(request, response);
                break;
            case "search":
                try {
                    showSearch(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    ShowEditSpending(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    showDeleteSpending(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    listSpending(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void ShowEditSpending(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int account_id = Integer.parseInt(request.getParameter("id"));
        Spending spending = spendingDAO.findById(account_id);
        request.setAttribute("spending", spending);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showDeleteSpending(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Spending spending = spendingDAO.findById(id);
        request.setAttribute("spending", spending);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showSearch(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account account = null;
        if (session != null) {
            account = (Account) session.getAttribute("accountLogging");
        }
        if (account.getRole().getId() == 1) {
            Date date = Date.valueOf(request.getParameter("date"));
            List<Spending> spendingList = spendingDAO.findByDate(date);
            request.setAttribute("spendings", spendingList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/findByDate.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Date date = Date.valueOf(request.getParameter("date"));
            List<Spending> spendingList = spendingDAO.findByDateOfAccountId(date, account.getId());
            request.setAttribute("spendings", spendingList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/findByDate.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void showSortByAmount(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account account = null;
        if (session != null) {
            account = (Account) session.getAttribute("accountLogging");
        }
        if (account.getRole().getId() == 1) {
            List<Spending> spendingList = spendingDAO.sortByAmount();
            request.setAttribute("spendings", spendingList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/sort.jsp");
            requestDispatcher.forward(request, response);
        } else {
            List<Spending> spendingList = spendingDAO.sortByAmountOfAccountId(account.getId());
            request.setAttribute("spendings", spendingList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/sort.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    private void showCreateNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void listSpending(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        Account account = null;
        if (session != null) {
            account = (Account) session.getAttribute("accountLogging");
        }
        int id_account = account.getId();
        if (account.getRole().getId() == 1) {
            List<Spending> spendingList = spendingDAO.findAll();
            request.setAttribute("spendings", spendingList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/list.jsp");
            requestDispatcher.forward(request, response);
        } else {
            List<Spending> spendingList = spendingDAO.findAllSpendingByAccountId(id_account);
            request.setAttribute("spendings", spendingList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/spending/list.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createNewSpending(request, response);
                break;
            case "edit":
                try {
                    editSpending(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteSpending(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    listSpending(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void deleteSpending(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        spendingDAO.deleteById(id);
        response.sendRedirect("/spending");
    }

    private void editSpending(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        HttpSession session = request.getSession();
       Account accountLogging=(Account) session.getAttribute("accountLogging");
        int id = Integer.parseInt(request.getParameter("id"));
        String type = request.getParameter("type");
        double amount = Double.parseDouble(request.getParameter("amount"));
        Date date = Date.valueOf(request.getParameter("date"));
        String description = request.getParameter("description");
        Spending spending = new Spending(id, type,description, amount, date, accountLogging);
        spendingDAO.save(spending);
        response.sendRedirect("/spending");
    }

    private void createNewSpending(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        Double amount = Double.valueOf(request.getParameter("amount"));
        Date date = Date.valueOf("date");
        int account_id = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(account_id);
        Spending spending = new Spending(type, description, amount, date, account);
        spendingDAO.save(spending);
        response.sendRedirect("/spending");
    }
}
