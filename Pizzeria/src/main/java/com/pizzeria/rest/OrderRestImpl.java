package com.pizzeria.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.model.Order;
import com.pizzeria.service.IOrderService;
import com.pizzeria.utils.Utility;

@Component("OrderRestImpl")
@Path("/order")
@Produces("application/json")
@Consumes("application/json")
public class OrderRestImpl {

	@Autowired
	private IOrderService orderService;
	
	@POST
	@Path("/create")
	public Order create(Order Order) {
		System.out.println("In the create method of Order @class:"+this.getClass().getName());
		Order createdOrder = orderService.create(Order);
		System.out.println("updated Order  = "+createdOrder);
		return createdOrder;
	}
	
	@POST
	@Path("/update")
	public Order update(Order order) {
		System.out.println("In the update method of Order @class:"+this.getClass().getName());
		Order updatedOrder = orderService.update(order);
		System.out.println("updated Order  = "+updatedOrder);
		return updatedOrder;
	}
	
	@GET
	@Path("/findByPk/{PkId}")
	public Order findByPk(@PathParam("PkId") Integer PkId) {
		System.out.println("In the method findByPk of order @params: PkId = "+ PkId +" @class:"+this.getClass().getName());
		Order order = orderService.findById(PkId);
		System.out.println("In the rest, order = "+order);
		return order;
	}
	
	@GET
	@Path("/findAll")
	public List<Order> findAll() {
		System.out.println("In the order rest impl going to find the list");
		List<Order> orderList = orderService.findAll();
		System.out.println("orderList in rest = "+orderList);
		return orderList;
	}
	
	@GET
	@Path("/search")
	public Map<Object,Object> search(@QueryParam("condition")String searchString,@QueryParam("orderBy")String orderBy, @QueryParam("orderType")String orderType) {
		System.out.println("In the order rest impl going to find the list");
		System.out.println("In the search Rest  method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,condition = "+searchString);
		
		Map<Object,Object> orderListJson = orderService.search(orderBy, orderType,searchString);
		System.out.println("orderList in rest search with map object = "+orderListJson);
		return orderListJson;
	}
	
	
	
	@GET
	@Path("/deleteByPk/{PkID}")
	public Map<String,String> deleteByPk(@PathParam("PkID") Integer PkID) {
		Map<String, String> deleteByIdMap;
		try {
			System.out.println("In the order rest impl going to delete entity with id: "+PkID);
			deleteByIdMap = orderService.deleteById(PkID);
			System.out.println("deleteByIdMap in rest = "+deleteByIdMap);
			return deleteByIdMap;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error @class: "+this.getClass().getName());
			Map<String, String> restfailureMap = Utility.getFailureMap();
			return restfailureMap;
		}
		
	}
	
	@POST
	@Path("/delete")
	public Map<String,String> deleteByPk(Order order) {
		try {
			System.out.println("In the order rest impl going to delete entity : "+order);
			Map<String, String> deleteMap = orderService.delete(order);
			System.out.println("deleteMap in rest = "+deleteMap);
			return deleteMap;
		} catch (Exception e) {
			e.printStackTrace();
			return Utility.getFailureMap();
		}
	}
	
	@GET
	@Path("/getAllOrders")
	public List<Order> getAllPizza() {
		try {
			System.out.println("In the getAllOrders method of pizza @class:"+this.getClass().getName());
			List<Order> OrderList = new ArrayList<Order>();
			OrderList = orderService.getAllOrder();
			System.out.println("All OrderList direct = "+OrderList);
			return OrderList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GET
	@Path("/getOrdersCountPizzaTypeWise")
	public Response getOrdersCountPizzaTypeWise() {
		System.out.println("In the getOrdersCountPizzaTypeWise method of pizza @class:"+this.getClass().getName());
		List<JSONObject> OrderList = new ArrayList<JSONObject>();
		OrderList = orderService.getOrdersCountPizzaTypeWise();
		System.out.println("OrderList count pizza type wise = "+OrderList);
		return Response.ok(OrderList.toString()).build();
	}
	
}
