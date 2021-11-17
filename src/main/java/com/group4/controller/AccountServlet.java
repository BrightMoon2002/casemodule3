package com.group4.controller;


import com.group4.model.account.Account;
import com.group4.model.account.Role;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import com.group4.service.roleService.IRoleService;
import com.group4.service.roleService.RoleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "AccountServlet", urlPatterns = "/login")
public class AccountServlet extends HttpServlet {
    private final AccountService accountService = new AccountService();
    private final IRoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateAccount(request, response);
                break;
            case "edit":
//                    showEditForm(request, response);
                break;
            case "delete":
//                    deleteUser(request, response);
                break;
            case "checkLogin":
//                checkLogin(request,response);
                break;
            default:
                listAccount(request, response);
                break;
        }
    }


    private void showCreateAccount(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/createLogin.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listAccount(HttpServletRequest request, HttpServletResponse response) {
        List<Account> accountList = accountService.findAll();
        request.setAttribute("accountList", accountList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/login.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                CreateAccount(request, response);
                break;
            case "edit":
//                    updateUser(request, response);
                break;
            case "checkLogin":
                checkLogin(request,response);
                break;

        }

    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountService.checkLogin(username,password);
        if (account != null){
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/homepage.jsp");
            try {
                request.setAttribute("account",account);
                dispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("message", "Wrong input, please re-enter");
            RequestDispatcher dispatcher = request.getRequestDispatcher("view/login.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void CreateAccount(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int role_id = Integer.parseInt(request.getParameter("role_id"));
        Role role = roleService.findById(role_id);
        Account account = new Account(username, password, name, dob, email, address, status, role);

        accountService.save(account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/login.jsp");
        request.setAttribute("message", "Account successfully created");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
