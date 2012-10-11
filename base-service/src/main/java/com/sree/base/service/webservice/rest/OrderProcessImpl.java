package com.sree.base.service.webservice.rest;

import javax.jws.WebService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

@WebService(endpointInterface = "com.sree.base.service.webservice.rest.OrderProcess")
public class OrderProcessImpl implements OrderProcess {

    Map<String, Order> orders = new HashMap<String, Order>();
	private int i;

	public Orders getOrders() {
		Orders o = new Orders();
		Order order = new Order();
		order.setName("sree");
		order.setOrderID("1250");
		Collection<Order> collection = new ArrayList<Order>();
		collection.add(order);
		order.setName("Padma");
		order.setOrderID("2300");
		collection.add(order);
		o.setOrder(collection);
		return o;
	}

	public Order getOrder(GetOrder order) {
		String orderID = order.getId();
		return orders.get(orderID);

	}

	public void addOrder(Order order) {
		String orderID = "ORD0" + (++i);
		// Added as a POST request
		String customerName = order.getName(); 

		Order newOrder = new Order();
		newOrder.setOrderID(orderID);
		newOrder.setName(customerName);

		orders.put(orderID, newOrder);
		System.out.println("Order added successfully");
    }
}
