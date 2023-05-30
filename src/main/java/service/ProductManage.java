package service;

import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductManage {
    private final List<Product> productList;
    private static ProductManage productManage;

    public ProductManage() {
        productList = new ArrayList<>();
    }
    public static ProductManage getInstance() {
        if (productManage == null){
            productManage = new ProductManage();
        }
        return productManage;
    }
    public List<Product> getProductList() {
        return productList;
    }
    public void addProduct(Product product) {
        productList.add(product);
    }
    public Product getById (int id) {
        for (Product product:productList) {
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
    public void deleteById (int id) {
        Product product = getById(id);
        if(product != null){
            productList.remove(product);
        }
    }
    public List<Product> searchByName(String name) {
        List<Product> productsSearch = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                productsSearch.add(product);
            }
        }
        return productsSearch;
    }
    public void deleteByClasses(Category category) {
        List<Product> productsDelete = new ArrayList<>();
        for (Product p : productList) {
            if (p.getCategory().equals(category)) {
                productsDelete.add(p);
            }
        }
        productList.removeAll(productsDelete);
    }
}
