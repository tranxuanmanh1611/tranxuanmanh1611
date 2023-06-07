package service;

import model.Brand;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    private static final String url = "jdbc:mysql://localhost:3306/product_manager5?useSSL=false";
    private static final String username = "root";
    private static final String pass = "123456789";
    private static final String driver = "com.mysql.jdbc.Driver";

private Connection getConnection() throws ClassNotFoundException, SQLException {
    Connection cn = null;
    Class.forName(driver);
    cn= DriverManager.getConnection(url,username,pass);
    return cn;
}

    @Override
    public void addProduct(Product product) {
        final String sql_add_product = "insert into product(name,description,price,img,category_id,brand_id) " +
                "values(?,?,?,?,?,?)";
        try(Connection cn=getConnection();PreparedStatement stm=cn.prepareStatement(sql_add_product)){
            stm.setString(1,product.getName());
            stm.setString(2,product.getDescription());
            stm.setInt(3,product.getPrice());
            stm.setString(4,product.getImg());
            stm.setInt(5,product.getCategory_id());
            stm.setInt(6,product.getBrand_id());
            stm.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getAll() {
        final String sql_get_all = "select * from product join category on product.category_id = category.id"
                + " join brand on product.brand_id = brand.id";
        List<Product> productList = new ArrayList<>();
        try(Connection cn = getConnection(); PreparedStatement stm = cn.prepareStatement(sql_get_all); ResultSet rs = stm.executeQuery()){
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int price = rs.getInt(4);
                String img = rs.getString(5);
                String category = rs.getString("category.name");
                String brand =rs.getString("brand.name");
                productList.add(new Product(id,name,description,price,img,category,brand));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public List<Category> getAllCategory() {
        final String sql_getCategory = "select * from category";
        List<Category> categoryList = new ArrayList<>();
        try(Connection cn = getConnection();PreparedStatement stm = cn.prepareStatement(sql_getCategory);ResultSet rs = stm.executeQuery()){
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                categoryList.add(new Category(id,name));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public List<Brand> getAllBrand() {
        final String sql_getBrand = "select * from brand";
        List<Brand> brandList = new ArrayList<>();
        try(Connection cn = getConnection();PreparedStatement stm = cn.prepareStatement(sql_getBrand);ResultSet rs = stm.executeQuery()){
           while (rs.next()){
               int id = rs.getInt(1);
               String name = rs.getString(2);
               brandList.add(new Brand(id,name));
           }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return brandList;
    }

    @Override
    public void updateProduct(Product product) {
        final  String sql_update = "update product set name=?,description=?,price=?,img=?,category_id=?,brand_id=? " +
                "where id = ?";
        try(Connection cn=getConnection();PreparedStatement stm=cn.prepareStatement(sql_update)){
            stm.setString(1,product.getName());
            stm.setString(2,product.getDescription());
            stm.setInt(3,product.getPrice());
            stm.setString(4,product.getImg());
            stm.setInt(5,product.getCategory_id());
            stm.setInt(6,product.getBrand_id());
            stm.setInt(7,product.getId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(int id) {
        final String sql_delete = "delete from product where id=?";
        try(Connection cn=getConnection();PreparedStatement stm = cn.prepareStatement(sql_delete)){
            stm.setInt(1,id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getBySearch(String search) {
    String convert = "'%"+search+"%'";
        final String sql_search = "select * from product join category on product.category_id = category.id"
                + " join brand on product.brand_id = brand.id "
                +"where product.name like "+ convert
                +" or product.description like "+convert
                +" or product.price like "+convert
                +" or brand.name like "+convert
                +" or category.name like "+convert;
        List<Product> productList = new ArrayList<>();
        try (Connection cn = getConnection();PreparedStatement stm = cn.prepareStatement(sql_search);ResultSet rs = stm.executeQuery()){
            while (rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int price =rs.getInt(4);
                String img =rs.getString(5);
                String brand = rs.getString("brand.name");
                String category = rs.getString("category.name");
                productList.add(new Product(id,name,description,price,img,brand,category)   );
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }
}
