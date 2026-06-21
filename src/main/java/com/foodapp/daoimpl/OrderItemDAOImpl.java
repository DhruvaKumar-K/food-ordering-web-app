package com.foodapp.daoimpl;

import java.sql.*;
import java.util.*;

import com.foodapp.dao.OrderItemDAO;
import com.foodapp.model.OrderItem;
import com.foodapp.util.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {

    @Override
    public boolean insertOrderItem(OrderItem item) {

//        String sql = "INSERT INTO order_item(order_Id,menu_Id,quantity,total_price) VALUES(?,?,?,?)";
    	String sql = "INSERT INTO order_item(order_id,menu_id,quantity,total_price) VALUES(?,?,?,?)";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getOrderId());
            ps.setInt(2, item.getMenuId());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public OrderItem getOrderItemById(int orderItemId) {

        String sql = "SELECT * FROM order_item WHERE order_item_id=?";
        OrderItem item = null;

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                item = new OrderItem();

//                item.setOrderItemId(rs.getInt("orderItemId"));
//                item.setOrderId(rs.getInt("orderId"));
//                item.setMenuId(rs.getInt("menuId"));
//                item.setQuantity(rs.getInt("quantity"));
//                item.setPrice(rs.getDouble("price"));
                
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setMenuId(rs.getInt("menu_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("total_price"));
                
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {

        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM order_item";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {

                OrderItem item = new OrderItem();

//                item.setOrderItemId(rs.getInt("orderItemId"));
//                item.setOrderId(rs.getInt("orderId"));
//                item.setMenuId(rs.getInt("menuId"));
//                item.setQuantity(rs.getInt("quantity"));
//                item.setPrice(rs.getDouble("price"));
                
                
                item.setOrderItemId(rs.getInt("order_item_id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setMenuId(rs.getInt("menu_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("total_price"));

                list.add(item);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateOrderItem(OrderItem item) {

    	String sql = "UPDATE order_item SET quantity=?, total_price=? WHERE order_item_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, item.getQuantity());
            ps.setDouble(2, item.getPrice());
            ps.setInt(3, item.getOrderItemId());

            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {

    	String sql = "DELETE FROM order_item WHERE order_item_id=?";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderItemId);
            return ps.executeUpdate() > 0;

        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}