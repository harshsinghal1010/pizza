package com.pizzeria.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface IGeneric<Entity,PkID> {

	public Entity create(Entity t);

	public Entity update(Entity t);

	public Entity findById(PkID k);

	public List<Entity> findAll();

	public Map<String,String> delete(Entity t);
	
	public Map<String,String> deleteById(PkID k);
	
	public Map<Object, Object> search(String orderBy, String orderType, String searchString);


}
