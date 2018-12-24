package com.pizzeria.rest;

import java.util.ArrayList;
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
import com.pizzeria.model.Pizza;
import com.pizzeria.service.IPizzaService;
import com.pizzeria.utils.Utility;

@Component("PizzaRest")
@Path("/pizza")
@Produces("application/json")
@Consumes("application/json")
public class PizzaRestImpl {

@Autowired
private IPizzaService pizzaService;
	
	@POST
	@Path("/create")
	public Response create(Pizza pizza) {
		System.out.println("In the create method of pizza @class:"+this.getClass().getName());
		
		Pizza createdPizza = pizzaService.create(pizza);
		System.out.println("created Pizza  = "+createdPizza);
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		pizzaList.add(createdPizza);
		return Response.ok(pizzaList.toString()).build();
	}
	
	@POST
	@Path("/update")
	public Pizza update(Pizza pizza) {
		System.out.println("In the update method of pizza @class:"+this.getClass().getName());
		Pizza updatedPizza = pizzaService.update(pizza);
		System.out.println("updated Pizza  = "+updatedPizza);
		return updatedPizza;
	}
	
	@GET
	@Path("/search")
	public Map<Object,Object> search(@QueryParam("condition")String searchString,@QueryParam("orderBy")String orderBy, @QueryParam("orderType")String orderType) {
		System.out.println("In the pizza rest impl going to find the list");
		System.out.println("In the search Rest  method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,condition = "+searchString);
		
		Map<Object,Object> pizzaListJson = pizzaService.search(orderBy, orderType,searchString);
		System.out.println("pizzaList in rest search with map object = "+pizzaListJson);
		return pizzaListJson;
	}
	
	@GET
	@Path("/findByPk/{PkId}")
	public Pizza findByPk(@PathParam("PkId") Integer PkId) {
		System.out.println("In the method findByPk of pizza @params: PkId = "+ PkId +" @class:"+this.getClass().getName());
		Pizza pizza = pizzaService.findById(PkId);
		System.out.println("In the rest, pizza = "+pizza);
		return pizza;
	}
	
	@GET
	@Path("/findAll")
	public List<Pizza> findAll() {
		System.out.println("In the pizza rest impl going to find the list");
		List<Pizza> pizzaList = pizzaService.findAll();
		System.out.println("pizzaList in rest = "+pizzaList);
		return pizzaList;
	}
	
	@GET
	@Path("/deleteByPk/{PkID}")
	public Map<String,String> deleteByPk(@PathParam("PkID") Integer PkID) {
		try {
			System.out.println("In the pizza rest impl going to delete entity with id: "+PkID);
			return pizzaService.deleteById(PkID);
		} catch (Exception e) {
			e.printStackTrace();
			return Utility.getFailureMap();
		}
	}
	
	@POST
	@Path("/delete")
	public Map<String,String> deleteByPk(Pizza pizza) {
		try {
			System.out.println("In the pizza rest impl going to delete entity : "+pizza);
			return pizzaService.delete(pizza);
		} catch (Exception e) {
			e.printStackTrace();
			return Utility.getFailureMap();
		}
	}
	
	@GET
	@Path("/getAllPizza")
	public List<Pizza> getAllPizza() {
		System.out.println("In the getAllPizza method of pizza @class:"+this.getClass().getName());
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		pizzaList = pizzaService.getAllPizza();
		System.out.println("pizzaList with updated mappings = "+pizzaList);
		return pizzaList;
	}
	
	@GET
	@Path("/getOrderedPizza/{pizzaType}/{crust}/{toppings}")
	public Response getOrderedPizza(@PathParam("pizzaType")String pizzaType,@PathParam("crust")String crust,@PathParam("toppings") String toppings) {
		 List<JSONObject> orderedPizza = pizzaService.getOrderedPizza(pizzaType,crust, toppings);
		 return Response.ok(orderedPizza.toString()).build();
	}
	
	
	
}
