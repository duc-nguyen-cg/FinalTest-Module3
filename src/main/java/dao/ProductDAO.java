package dao;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    public static final String SELECT_ALL_PRODUCTS = "call selectALLProduct()";
    public static final String FIND_PRODUCT_BY_ID = "call selectProductByID(?)";
    public static final String ADD_PRODUCTS = "call createProduct(?,?,?,?,?,?)";
    public static final String UPDATE_PRODUCT = "call editProduct(?,?,?,?,?,?,?)";
    public static final String DELETE_PRODUCT = "delete from product where id = ?";


    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            CallableStatement preparedStatement = connection.prepareCall(SELECT_ALL_PRODUCTS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String category = rs.getString("category");
                if (category == null){
                    category = "Undefined";
                }
                products.add(new Product(id, name, price, quantity, color, description, category ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }


    @Override
    public Product findById(int findId) {
        Product foundProduct = null;
        Connection connection = SQLConnection.getConnection();
        try {
            CallableStatement preparedStatement = connection.prepareCall(FIND_PRODUCT_BY_ID);
            preparedStatement.setInt(1, findId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String category = rs.getString("category");
                foundProduct = new Product(id, name, price, quantity, color, description, category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return foundProduct;
    }

    @Override
    public boolean create(Product product) {
        int rowAdded = 0;
        Connection connection = SQLConnection.getConnection();
        try {
            CallableStatement preparedStatement = connection.prepareCall(ADD_PRODUCTS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setFloat(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getCategory());
            rowAdded = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowAdded != 0;
    }


    @Override
    public boolean update(int id, Product product) {
        int rowUpdated = 0;
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, product.getName());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getColor());
            preparedStatement.setString(6, product.getDescription());
            preparedStatement.setString(7, product.getCategory());
            rowUpdated = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdated != 0;
    }


    @Override
    public boolean delete(int id) {
        int rowDeleted = 0;
        Connection connection = SQLConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDeleted != 0;
    }


    public List<Category> findAllCategory(){
        List<Category> categories = new ArrayList<>();
        Connection connection = SQLConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("categoryId");
                String category = rs.getString("category");
                categories.add(new Category(id, category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }
}
