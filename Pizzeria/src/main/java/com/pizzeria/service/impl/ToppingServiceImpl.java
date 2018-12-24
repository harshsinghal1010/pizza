package com.pizzeria.service.impl;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.dao.IToppingDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Topping;
import com.pizzeria.service.IToppingService;

@Service("ToppingService")
public class ToppingServiceImpl extends GenericImpl<Topping, Integer> implements IToppingService {

	@Autowired
	private IToppingDao toppingDao;
	
	ToppingServiceImpl(){
		super(Topping.class);
	}
	
	@Override
	public Topping create(Topping topping) {
		System.out.println("In the create method of ToppingServiceImpl");
		Topping createdToppings = super.create(topping);
		return createdToppings;
	}

	@Override
	public Topping update(Topping topping) {
		System.out.println("In the update method of ToppingServiceImpl class after spring integration");
		Topping createdToppings = super.update(topping);
		return createdToppings;
	}

	@Override
	public Map<Object,Object> search(String orderBy,String orderType,String searchString){
		System.out.println("In the search method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,searchString = "+searchString);
		return super.search(orderBy, orderType,searchString);
	}
	
	@Override
	public Topping findById(Integer pkID) {
		System.out.println("In the method findById @class :"+this.getClass()+" with @params pkID: "+pkID);
		Topping topping = super.findById(pkID);
		System.out.println("Found pizza at @class :"+this.getClass()+" pizza = "+topping);
		return topping;
	}

	@Override
	public List<Topping> findAll() {
		System.out.println("In the service impl going to retrieve the list");
		 List<Topping> list = super.findAll();
		 System.out.println("list in service= "+list);
		return list;
	}

	@Override
	public Map<String,String> delete(Topping topping) {
		return super.delete(topping);
	}

	@Override
	public Map<String,String> deleteById(Integer pkID) {
		return super.deleteById(pkID);
	}

	@Override
	public List<JSONObject> getToppingsByPizzaType(String pizzaType) {
		try {
			System.out.println("In the service @class : "+this.getClass().getName()+" @Method : getToppingsByPizzaType with params : "+pizzaType);
			List<JSONObject> toppingsList=toppingDao.getToppingsByPizzaType(pizzaType);
			System.out.println("In the service after response = "+toppingsList);
			return toppingsList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("In the error @class : "+this.getClass().getName()+" @Method : getToppingsByPizzaType with params : "+pizzaType);
			return null;
		}
	}

	@Override
	public List<Topping> getAllToppings() {
		try {
			System.out.println("In the service @class : "+this.getClass().getName()+" @Method : getAllToppings with params : ");
			List<Topping> toppingsList=toppingDao.getAllToppings();
			return toppingsList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("In the error @class : "+this.getClass().getName()+" @Method : getToppingsByPizzaType with params : ");
			return null;
		}
	}
	
}