package ra.controller;

import ra.model.Product;
import ra.service.ProductService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServelet", value = "/ProductServelet")
public class ProductServelet extends HttpServlet {
    protected ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null) {
            listProduct(productService.findAll(), request, response);
        } else {
            switch (action) {
                case "ADD":
                    request.getRequestDispatcher("/WEB-INF/newProduct.jsp").forward(request, response);
                    break;
                case "DELETE":
                    int idDelete = Integer.parseInt(request.getParameter("id"));
                    productService.deleteById(idDelete);
                    listProduct(productService.findAll(), request, response);
                case "EDIT":
                    int idEdit = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("product", productService.findById(idEdit));
                    request.getRequestDispatcher("/WEB-INF/updateproduct.jsp").forward(request, response);
                    break;
                case "SEARCH":
                    String keywords = request.getParameter("search-name");
                    request.setAttribute("keyword", keywords);
                    listProduct(productService.searchByName(keywords), request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADD":
                    String name = request.getParameter("name");
                    String description = request.getParameter("description");
                    Double price = Double.valueOf(request.getParameter("price"));
                    int quantity = Integer.valueOf(request.getParameter("quantity"));
                    String urlFile = request.getParameter("urlFile");
                    Product newProduct = new Product(0, name, description, price, quantity, urlFile);
                    productService.save(newProduct);
                    listProduct(productService.findAll(), request, response);
                    break;
                case "EDIT":
                    int editId = Integer.valueOf(request.getParameter("id"));
                    String editName = request.getParameter("name");
                    String editDescription = request.getParameter("description");
                    double editPrice = Double.valueOf(request.getParameter("price"));
                    int editQuantity = Integer.valueOf(request.getParameter("quantity"));
                    String editUrlFile = request.getParameter("urlFile");
                    Product editProduct = new Product(editId, editName, editDescription, editPrice, editQuantity, editUrlFile);
                    productService.save(editProduct);
                    listProduct(productService.findAll(), request, response);
                    break;
            }
        }
    }

    protected void listProduct(List<Product> products, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("product", products);
        request.getRequestDispatcher("/WEB-INF/listproduct.jsp").forward(request, response);
    }


}