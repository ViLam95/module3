package service;


import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryManage {
    private final List<Category> categoryList;
    private static CategoryManage categoryManage;

    public CategoryManage() {
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "A", 10));
        categoryList.add(new Category(2, "B", 9));
        categoryList.add(new Category(3, "C",11));
    }
    public static CategoryManage getInstance() {
        if (categoryManage == null){
            categoryManage = new CategoryManage();
        }
        return categoryManage;
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }
    public void addCategory(Category category) {
        categoryList.add(category);
    }
    public Category getById (int id) {
        for (Category category: categoryList) {
            if(category.getId() == id){
                return category;
            }
        }
        return null;
    }

    public void deleteById (int id) {
        Category category = getById(id);
        if(category != null){
            categoryList.remove(category);
        }
    }
}

