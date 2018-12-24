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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pizzeria.model.Crust;
import com.pizzeria.service.ICrustService;
import com.pizzeria.utils.Utility;

@Path("/crust")
@Produces("application/json")
@Consumes("application/json")
@Component("CrustRestImpl")
public class CrustRestImpl {

	@Autowired
	private ICrustService crustService;
	
	@POST
	@Path("/create")
	public Response create(Crust crust) {
		System.out.println("In the create method of topping @class:"+this.getClass().getName());
		Crust createdCrust = crustService.create(crust);
		System.out.println("updated Crust  = "+createdCrust);
		List<Crust> CrustList = new ArrayList<Crust>();
		CrustList.add(createdCrust);
		return Response.ok(CrustList.toString()).build();
	}
	
	@POST
	@Path("/update")
	public Crust update(Crust Crust) {
		System.out.println("In the update method of topping @class:"+this.getClass().getName());
		Crust updatedCrust = crustService.update(Crust);
		System.out.println("updated Crust  = "+updatedCrust);
		return updatedCrust;
	}
	
	@GET
	@Path("/search")
	public Map<Object,Object> search(@QueryParam("condition")String searchString,@QueryParam("orderBy")String orderBy, @QueryParam("orderType")String orderType) {
		System.out.println("In the crust rest impl going to find the list");
		System.out.println("In the search Rest  method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,condition = "+searchString);
		
		Map<Object,Object> crustListJson = crustService.search(orderBy, orderType,searchString);
		System.out.println("crustList in rest search with map object = "+crustListJson);
		return crustListJson;
	}
	
	@GET
	@Path("/findByPk/{PkId}")
	public Crust findByPk(@PathParam("PkId") Integer PkId) {
		System.out.println("In the method findByPk of crust @params: PkId = "+ PkId +" @class:"+this.getClass().getName());
		Crust crust = crustService.findById(PkId);
		System.out.println("In the rest, crust = "+crust);
		return crust;
	}
	
	@GET
	@Path("/findAll")
	public List<Crust> findAll() {
		System.out.println("In the crust rest impl going to find the list");
		List<Crust> crustList = crustService.findAll();
		System.out.println("crustList in rest = "+crustList);
		return crustList;
	}
	
	@GET
	@Path("/deleteByPk/{PkID}")
	public Map<String,String> deleteByPk(@PathParam("PkID") Integer PkID) {
		try {
			System.out.println("In the crust rest impl going to delete entity with id: "+PkID);
			return crustService.deleteById(PkID);
		} catch (Exception e) {
			e.printStackTrace();
			return Utility.getFailureMap();
		}
	}
	
	@POST
	@Path("/delete")
	public Map<String,String> deleteByPk(Crust crust) {
		try {
			System.out.println("In the crust rest impl going to delete entity : "+crust);
			return crustService.delete(crust);
		} catch (Exception e) {
			e.printStackTrace();
			return Utility.getFailureMap();
		}
	}
	
	@GET
	@Path("/getCrustByPizzaType/{pizzaType}")
	public List<Crust> getCrustsByPizzaType(@PathParam("pizzaType") String pizzaType) {
		System.out.println("In the getCrustsByPizzaType method of pizza @class:"+this.getClass().getName());
		List<Crust> crustList = crustService.getCrustByPizzaType(pizzaType);
		System.out.println("crustList in rest , direct List = "+crustList);
		return crustList;
	}
	
	@GET
	@Path("/getAllCrusts")
	public List<Crust> getAllCrusts() {
		System.out.println("In the getAllCrusts method of pizza @class:"+this.getClass().getName());
		List<Crust> toppingsList = new ArrayList<Crust>();
		toppingsList = crustService.getAllCrusts();
		System.out.println("toppingsList at rest = "+toppingsList);
		return toppingsList;
	}
	
}
