package controller;

import model.Category;
import service.CategoryManage;
import service.ProductManage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/categories")
public class CategoryServlet extends HttpServlet {
    private final CategoryManage categoryManage = CategoryManage.getInstance();
    private final ProductManage productManage = ProductManage.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createGet(request, response);
                break;
            case "update":
                updateGet(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                findAdd(request, response);
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
                createPost(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
        }
    }

    private void findAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/home.jsp");
        request.setAttribute("categories", categoryManage.getCategoryList());
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryManage.getCategoryList());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int numberOfProducts = Integer.parseInt(request.getParameter("number"));

        Category category = new Category(id, name, numberOfProducts);
        categoryManage.addCategory(category);
        response.sendRedirect("/categories");
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Category category = categoryManage.getById(id);

        if (category != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/category/update.jsp");
            request.setAttribute("categories", category);
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        Category category = categoryManage.getById(id);

        if (category != null) {
            category.setName(name);
            response.sendRedirect("/categories");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryManage.getById(id);
        if (category != null) {
            categoryManage.deleteById(id);
            productManage.deleteByClasses(category);
            response.sendRedirect("/categories");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    protected void getNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList;
    }
}
