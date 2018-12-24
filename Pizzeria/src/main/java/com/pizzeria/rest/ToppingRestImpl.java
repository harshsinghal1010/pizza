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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.model.Pizza;
import com.pizzeria.model.Topping;
import com.pizzeria.service.IToppingService;
import com.pizzeria.utils.Utility;

@Component("ToppingRest")
@Path("/topping")
@Produces("application/json")
@Consumes("application/json")
public class ToppingRestImpl {

	@Autowired
	private IToppingService toppingService;
	
	@POST
	@Path("/create")
	public Response create(Topping topping) {
		System.out.println("In the create method of topping @class:"+this.getClass().getName());
		
		Topping createdTopping = toppingService.create(topping);
		System.out.println("updated Topping  = "+createdTopping);
		List<Topping> ToppingList = new ArrayList<Topping>();
		ToppingList.add(createdTopping);
		return Response.ok(ToppingList.toString()).build();
	}
	
	@POST
	@Path("/update")
	public Topping update(Topping topping) {
		System.out.println("In the update method of topping @class:"+this.getClass().getName());
		Topping updatedTopping = toppingService.update(topping);
		System.out.println("updated Topping  = "+updatedTopping);
		return updatedTopping;
	}
	
	@GET
	@Path("/search")
	public Map<Object,Object> search(@QueryParam("condition")String searchString,@QueryParam("orderBy")String orderBy, @QueryParam("orderType")String orderType) {
		System.out.println("In the topping rest impl going to find the list");
		System.out.println("In the search Rest  method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,condition = "+searchString);
		
		Map<Object,Object> toppingListJson = toppingService.search(orderBy, orderType,searchString);
		System.out.println("toppingList in rest search with map object = "+toppingListJson);
		return toppingListJson;
	}
	
	@GET
	@Path("/findByPk/{PkId}")
	public Topping findByPk(@PathParam("PkId") Integer PkId) {
		System.out.println("In the method findByPk of topping @params: PkId = "+ PkId +" @class:"+this.getClass().getName());
		Topping topping = toppingService.findById(PkId);
		System.out.println("In the rest, topping = "+topping);
		return topping;
	}
	
	@GET
	@Path("/findAll")
	public List<Topping> findAll() {
		System.out.println("In the pizza rest impl going to find the list");
		List<Topping> toppingsList = toppingService.findAll();
		System.out.println("toppingsList in rest = "+toppingsList);
		return toppingsList;
	}
	
	@GET
	@Path("/deleteByPk/{PkID}")
	public Map<String,String> deleteByPk(@PathParam("PkID") Integer PkID) {
		try {
			System.out.println("In the topping rest impl going to delete entity with id: "+PkID);
			return toppingService.deleteById(PkID);
		} catch (Exception e) {
			e.printStackTrace();
			return Utility.getFailureMap();
		}
	}
	
	@POST
	@Path("/delete")
	public Map<String,String> deleteByPk(Topping topping) {
		try {
			System.out.println("In the pizza rest impl going to delete entity : "+topping);
			return toppingService.delete(topping);
		} catch (Exception e) {
			e.printStackTrace();
			return Utility.getFailureMap();
		}
	}
	
	@GET
	@Path("/getToppingsByPizzaType/{pizzaType}")
	public Response getToppingsByPizzaType(@PathParam("pizzaType") String pizzaType) {
		System.out.println("In the getToppingsByPizzaType method of pizza @class:"+this.getClass().getName());
		List<JSONObject> toppingsList = new ArrayList<JSONObject>();
		toppingsList = toppingService.getToppingsByPizzaType(pizzaType);
		System.out.println("toppingsList in rest = "+toppingsList);
		return Response.ok(toppingsList.toString()).build();
	}
	
	@GET
	@Path("/getAllToppings")
	public List<Topping> getAllToppings() {
		System.out.println("In the getAllToppings method of pizza @class:"+this.getClass().getName());
		List<Topping> toppingsList = new ArrayList<Topping>();
		toppingsList = toppingService.getAllToppings();
		System.out.println("toppingsList at rest = "+toppingsList);
		return toppingsList;
	}
	
}
