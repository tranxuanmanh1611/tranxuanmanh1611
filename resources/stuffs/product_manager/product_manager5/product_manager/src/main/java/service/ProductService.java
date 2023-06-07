package service;

import model.Brand;
import model.Category;
import model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    List<Product> getAll();
    List<Category> getAllCategory();
    List<Brand> getAllBrand();
    void updateProduct(Product product);
    void deleteProduct(int id);
    List<Product> getBySearch(String search);
}
