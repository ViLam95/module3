package controller;

import model.Category;
import model.Product;
import service.CategoryManage;
import service.ProductManage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductManage productManage = ProductManage.getInstance();
    private final CategoryManage categoryManage = CategoryManage.getInstance();

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
                findAll(request, response);
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
            case "search":
                search(request, response);
                break;
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryManage.getCategoryList());
        request.setAttribute("products", productManage.getProductList());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/home.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categories", categoryManage.getCategoryList());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Date date = new Date();
        int categoryID = Integer.parseInt(request.getParameter("category"));

        Category category = categoryManage.getById(categoryID);
        if (category != null) {
            Product product = new Product(id, name, price, date, category);
            productManage.addProduct(product);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productManage.getById(id);
        if (product != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/update.jsp");
            request.setAttribute("products", product);
            request.setAttribute("categories", categoryManage.getCategoryList());
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Date date = new Date();
        int categoryID = Integer.parseInt(request.getParameter("category"));

        Category category = categoryManage.getById(categoryID);
        Product product = productManage.getById(id);

        if (product != null && category != null) {
            product.setName(name);
            product.setPrice(price);
            product.setDate(date);
            product.setCategory(category);
            response.sendRedirect("/products");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productManage.deleteById(id);
        response.sendRedirect("/products");
    }

    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Product> productList = productManage.searchByName(search);
        request.setAttribute("products", productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/product/home.jsp");
        requestDispatcher.forward(request, response);
    }
}
