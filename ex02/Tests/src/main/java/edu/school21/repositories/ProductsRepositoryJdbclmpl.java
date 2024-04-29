package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbclmpl implements ProductsRepository{
    private DataSource dataSource;

    public ProductsRepositoryJdbclmpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM butik.product");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3));
                products.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> product = Optional.empty();
        try (Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.execute("SELECT * FROM butik.product WHERE identifier = " + id);
            ResultSet resultSet = statement.getResultSet();
            if(!resultSet.next()){
                return product;
            } else {
                product = Optional.of(new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return product;
    }

    @Override
    public void update(Product product) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE butik.product SET name = ?, price = ? WHERE identifier = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setLong(3, product.getIdentifier());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO butik.product(name, price) VALUES (?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM butik.product WHERE identifier = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
