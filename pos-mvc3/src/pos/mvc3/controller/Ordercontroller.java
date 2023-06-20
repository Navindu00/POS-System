/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc3.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import pos.mvc3.db.DbConnection;
import pos.mvc3.model.Order;
import pos.mvc3.model.OrderDetail;

/**
 *
 * @author navindudulanjaya <your.name at your.org>
 */
public class Ordercontroller {
    
    public String placeOrder(Order order, List<OrderDetail> orderDetails) throws SQLException{
        Connection connection = DbConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            String orderInsertQuery = "INSERT INTO orders VALUES (?,?,?,?)";
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
            PreparedStatement preparedStatementForOrder = connection.prepareStatement(orderInsertQuery);
            preparedStatementForOrder.setInt(1,order.getOrderID());
            preparedStatementForOrder.setString(2, sdf.format(order.getOrderDate()));
            preparedStatementForOrder.setDouble(3,order.getTotal());
            preparedStatementForOrder.setInt(4, order.getCustomerID());
        
            if(preparedStatementForOrder.executeUpdate() > 0){
                String orderDetailInsertQuery = "INSERT INTO orderDetail (unit_price, quantity, sub_total, order_id, item_id) VALUES (?,?,?,?,?)";
                
                boolean isOrderDetailsSaved = true;
                PreparedStatement preparedStatementForOrderDetails = connection.prepareStatement(orderDetailInsertQuery);
                
                for(OrderDetail od : orderDetails){
                    preparedStatementForOrderDetails.setDouble(1,od.getUnitPrice());
                    preparedStatementForOrderDetails.setInt(2, od.getQuantity());
                    preparedStatementForOrderDetails.setDouble(3, od.getUnitPrice() * od.getQuantity());
                    preparedStatementForOrderDetails.setInt(4, order.getOrderID());
                    preparedStatementForOrderDetails.setInt(5, od.getItemID());
                    
                    if(!(preparedStatementForOrderDetails.executeUpdate()>0)){
                        isOrderDetailsSaved = false;
                    }
                }
                
                if(isOrderDetailsSaved){
                    String itemUpdateQuery = "UPDATE item SET quantity = quantity - ? WHERE item_id = ?";
                    boolean isItemUpdated = true;
                    
                    PreparedStatement preparedStatementForItem = connection.prepareStatement(itemUpdateQuery);
                    
                    for(OrderDetail od : orderDetails){
                        preparedStatementForItem.setInt(1,od.getQuantity());
                        preparedStatementForItem.setInt(2,od.getItemID());
                        
                        if(!(preparedStatementForItem.executeUpdate() > 0)){
                            isItemUpdated = false;
                        }
                    }
                    
                    if(isItemUpdated){
                        connection.commit();
                        return "Success";
                    }else{
                        connection.rollback();
                        return "Item Update Error";
                    }
                }else{
                    connection.rollback();
                    return "Order Details save Error";
                } 
            }else{
                connection.rollback();
                return "Order save Error";
            }
        }catch (Exception ex){
            connection.rollback();
            throw ex;
        }finally{
            connection.setAutoCommit(true);
        }
    }
}
