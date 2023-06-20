/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package pos.mvc3.controller;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pos.mvc3.db.DbConnection;
import pos.mvc3.model.Customer;

/**
 *
 * @author HP™
 */
public class CustomerController {

    public String addCustomer(Customer customer) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO customer VALUES (?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getAddress());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPostalCode());

        if (preparedStatement.executeUpdate() > 0) {
            return "Success";
        } else {
            return "Fail";
        }

    }

    public List<Customer> getCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String query = "SELECT * FROM customer";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getInt("customer_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("address"),
                    resultSet.getString("postal_code"));

            customers.add(customer);

        }

        return customers;

    }
    
    public Customer getCustomer(Integer id) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        
        String query = "SELECT * FROM customer WHERE customer_id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while(resultSet.next()){
            return new Customer(resultSet.getInt("customer_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("address"),
                    resultSet.getString("postal_code"));
        }
        return null;  
    }

    public String updateCustomer(Customer customer) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String query = "UPDATE customer SET name = ?, address = ?, email = ?, postal_code = ? WHERE customer_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getEmail());
        preparedStatement.setString(4, customer.getPostalCode());
        preparedStatement.setInt(5, customer.getId());
        

        if (preparedStatement.executeUpdate() > 0) {
            return "Updated";
        } else {
            return "Failed";
        }

    }
    
    public String deleteCustomer(Integer id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String query = "DELETE FROM customer WHERE customer_id = ? ";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        if (preparedStatement.executeUpdate() > 0) {
            return "Deleted";
        } else {
            return "Failed";
        }

    }
}
