package controller;

import model.Category;
import model.Product;
import service.ProductService;
import service.IProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ContactServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showUpdateForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
            default:
                listProducts(request, response);
                break;
        }
    }



    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products;
        String search = request.getParameter("search");
        if (search == null || search == ""){
            products = this.productService.findAll();
        } else {
            products = this.productService.searchByName(search);
        }

        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = this.productService.findAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product editProduct = this.productService.findById(id);
        request.setAttribute("product", editProduct);
        List<Category> categories = this.productService.findAllCategory();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/edit.jsp");
        dispatcher.forward(request, response);
    }


    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product deleteProduct = this.productService.findById(id);
        request.setAttribute("product", deleteProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/delete.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){ action = "";}
        switch (action){
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                editProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                break;
        }
    }



    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Product newProduct = new Product(name, price, quantity, color, description, category);
        boolean isCreated = this.productService.create(newProduct);

        List<Category> categories = this.productService.findAllCategory();
        request.setAttribute("categories", categories);

        if (isCreated == false){
            request.setAttribute("message", "Error!");
        } else {
            request.setAttribute("message", "Added!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }


    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");

        Product editedProduct = new Product(id, name, price, quantity, color, description, category);
        boolean isEdited = this.productService.update(id, new Product(id, name, price, quantity, color, description, category));
        request.setAttribute("product", editedProduct);

        List<Category> categories = this.productService.findAllCategory();
        request.setAttribute("categories", categories);

        if (isEdited == false){
            request.setAttribute("message", "Error!");
        } else {
            request.setAttribute("message", "Edited!");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/edit.jsp");
        dispatcher.forward(request, response);
    }


    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.productService.delete(id);

        List<Product> products = this.productService.findAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/product/list.jsp");
        dispatcher.forward(request, response);
    }
}
