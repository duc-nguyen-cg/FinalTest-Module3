package service;

import dao.ProductDAO;
import dao.IProductDAO;
import model.Category;
import model.Product;

import java.util.List;

public class ProductService implements IProductService {
    private IProductDAO productDAO = new ProductDAO();

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public boolean update(int id, Product product) {
        return productDAO.update(id, product);
    }

    @Override
    public boolean delete(int id) {
        return productDAO.delete(id);
    }

    public List<Category> findAllCategory(){ return this.productDAO.findAllCategory();}
}
