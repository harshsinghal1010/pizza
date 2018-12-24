package com.pizzeria.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzeria.dao.ICrustDao;
import com.pizzeria.generic.impl.GenericImpl;
import com.pizzeria.model.Crust;
import com.pizzeria.service.ICrustService;

@Service("CrustServiceImpl")
public class CrustServiceImpl extends GenericImpl<Crust, Integer> implements ICrustService {

	@Autowired
	private ICrustDao crustDao;
   
	CrustServiceImpl(){
		super(Crust.class);
	}
	
	@Override
	public Crust create(Crust t) {
		System.out.println("In the create method of CrustServiceImpl class");
		Crust createdCrusts = super.create(t);
		System.out.println("Now the transaction is commented");
		return createdCrusts;
	}

	@Override
	public Crust update(Crust t) {
		System.out.println("In the update method of CrustServiceImpl class");
		Crust createdCrusts = super.update(t);
		System.out.println("Now the transaction is commented");
		return createdCrusts;
	}

	@Override
	public Map<Object,Object> search(String orderBy,String orderType,String searchString){
		System.out.println("In the search method, orderBy = "+orderBy+" ,orderType = "+orderType+" ,searchString = "+searchString);
		return super.search(orderBy, orderType,searchString);
	}
	
	@Override
	public Crust findById(Integer pkID) {
		System.out.println("In the method findById @class :"+this.getClass()+" with @params pkID: "+pkID);
		Crust crust = super.findById(pkID);
		System.out.println("Found pizza at @class :"+this.getClass()+" crust = "+crust);
		return crust;
	}

	@Override
	public List<Crust> findAll() {
		System.out.println("In the service impl going to retrieve the list");
		 List<Crust> list = super.findAll();
		 System.out.println("list in service= "+list);
		return list;
	}
	
	@Override
	public Map<String,String> delete(Crust t) {
		return super.delete(t);
	}

	@Override
	public Map<String,String> deleteById(Integer k) {
		return super.deleteById(k);
	}

	@Override
	public List<Crust> getCrustByPizzaType(String pizzaType) {
		try {
			System.out.println("In the service @class : "+this.getClass().getName()+" @Method : getCrustByPizzaType with params : "+pizzaType);
			List<Crust> crustList=crustDao.getCrustByPizzaType(pizzaType);
			System.out.println("In the service after response = "+crustList);
			return crustList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("In the error @class : "+this.getClass().getName()+" @Method : getCrustByPizzaType with params : "+pizzaType);
			return null;
		}
	}

	@Override
	public List<Crust> getAllCrusts() {
		try {
			System.out.println("In the service @class : "+this.getClass().getName()+" @Method : getAllCrusts with params : ");
			List<Crust> crustList=crustDao.getAllCrusts();
			return crustList;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("In the error @class : "+this.getClass().getName()+" @Method : getCrustsByPizzaType with params : ");
			return null;
		}
	}

}