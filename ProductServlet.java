import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
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

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("product/error.jsp");
        }else{
            this.productService.remove(id);
            try{
                response.sendRedirect("/products");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));

        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404");
        }else{
            product.setName(name);
            product.setPrice(price);
            this.productService.update(id, product);
            request.setAttribute("product", product);
            request.setAttribute("message", "Customer information was update!");
            dispatcher = request.getRequestDispatcher("product/edit.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch(ServletException |IOException e){
            e.printStackTrace();
        }

    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int id = (int)(Math.random() * 1000);

        Product product = new Product(id, name, price);
        this.productService.save(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("message", "New Product was created!");
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                shoWDeleteForm(request, response);
                break;
            case "view":
                viewProduct(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;

        if (product == null){
            dispatcher = request.getRequestDispatcher("error.jsp");
        }else{
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/view.jsp");
        }

        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void shoWDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;

        if (product == null){
            dispatcher = request.getRequestDispatcher("product/error.jsp");
        }else{
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/delete.jsp");
        }
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher = request.getRequestDispatcher("error-404");
        }else{
            request.setAttribute("product", product);
            dispatcher = request.getRequestDispatcher("product/edit.jsp");
        }

        try{
            dispatcher.forward(request, response);
        }catch(ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try{
            dispatcher.forward(request, response);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = this.productService.findAll();
        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        try{
            dispatcher.forward(request, response);
        }catch (ServletException |IOException e){
            e.printStackTrace();
        }
    }
}
