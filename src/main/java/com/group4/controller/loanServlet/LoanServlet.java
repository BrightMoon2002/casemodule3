package com.group4.controller.loanServlet;

import com.group4.model.loan.Loan;
import com.group4.service.loan.ILoanService;
import com.group4.service.loan.ServiceLoan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoanServlet", value = "/loans")

public class LoanServlet extends HttpServlet {

    private ILoanService loanService = new ServiceLoan();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(req, resp);
                break;
            case "delete":
                break;
            case "edit":
                break;
            case "search":
                break;
            default:
                showListLoan(req,resp);
                break;
        }
    }

    private void showListLoan(HttpServletRequest req, HttpServletResponse resp) {
        List<Loan> loanList;
        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("/view/loan/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/loan/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
