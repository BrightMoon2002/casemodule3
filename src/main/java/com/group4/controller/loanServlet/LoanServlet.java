package com.group4.controller.loanServlet;

import com.group4.model.loan.Loan;
import com.group4.service.loan.IInterestService;
import com.group4.service.loan.ILoanService;
import com.group4.service.loan.InterestService;
import com.group4.service.loan.ServiceLoan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LoanServlet", value = "/loans")

public class LoanServlet extends HttpServlet {

    private ILoanService loanService = new ServiceLoan();
    private IInterestService iInterestService = new InterestService();
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
                showDeleteForm(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "search":

                break;
            default:
                showListLoan(req,resp);
                break;
        }
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Loan loanDelete = loanService.findById(id);
            req.setAttribute("loanDelete", loanDelete);
            RequestDispatcher dispatcher;
            if (loanDelete == null) {
                dispatcher = req.getRequestDispatcher("view/loan/error.jsp");
            } else {
                dispatcher = req.getRequestDispatcher("view/loan/delete.jsp");
            }
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            Loan loanEdit = loanService.findById(id);
            req.setAttribute("loanEdit", loanEdit);
            req.setAttribute("interest", iInterestService.findAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("view/loan/edit.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showListLoan(HttpServletRequest req, HttpServletResponse resp) {
        List<Loan> loanList;
        try {
            loanList = loanService.findAll();
            req.setAttribute("list", loanList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

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
