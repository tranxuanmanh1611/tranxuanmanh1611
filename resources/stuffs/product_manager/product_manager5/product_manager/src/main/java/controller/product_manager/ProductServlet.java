package controller.product_manager;

import model.Product;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "productServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductServiceImpl manager = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null)   {
            action="" ;
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            default:
                showList(request,response);
                break;
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("searchValue");
        request.setAttribute("productList",manager.getBySearch(search));
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request,response);

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        manager.deleteProduct(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("products");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("edit",Integer.parseInt(request.getParameter("id")));
        request.setAttribute("productList",manager.getAll());
        request.setAttribute("categoryList",manager.getAllCategory());
        request.setAttribute("brandList",manager.getAllBrand());
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("create","...");
        request.setAttribute("productList",manager.getAll());
        request.setAttribute("categoryList",manager.getAllCategory());
        request.setAttribute("brandList",manager.getAllBrand());
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request,response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productList",manager.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null)   {
            action="" ;
        }
        switch (action){
            case "create":
                createProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "search":
                searchProduct(request,response);
                break;
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int brand_id = Integer.parseInt(request.getParameter("brand"));
        int category_id = Integer.parseInt(request.getParameter("category"));
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        String img = request.getParameter("img");
        Product product = new Product(id,name,description,price,img,category_id,brand_id);
        manager.updateProduct(product);
response.sendRedirect("products");
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int brand_id = Integer.parseInt(request.getParameter("brand"));
        int category_id = Integer.parseInt(request.getParameter("category"));
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        String img = request.getParameter("img");
        Product product = new Product(name,description,price,img,category_id,brand_id);
        manager.addProduct(product);
        response.sendRedirect("products");
    }
}
