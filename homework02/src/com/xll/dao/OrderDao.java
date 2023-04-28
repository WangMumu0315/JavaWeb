package com.xll.dao;

import com.xll.model.Order;

import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public interface OrderDao extends SimpleDao<Order> {

    Order findByOrderId(String orderId);

    List<Order> findByCustomerId(int customerId);

    int delete(String orderId);

}
