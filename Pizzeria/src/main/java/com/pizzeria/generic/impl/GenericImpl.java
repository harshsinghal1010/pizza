package com.pizzeria.generic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pizzeria.generic.IGeneric;
import com.pizzeria.utils.Utility;

@Transactional
@Service("genericimpl")
public class GenericImpl<Entity, PkID> implements IGeneric<Entity, PkID> {

	private static Logger logger = Logger.getLogger(GenericImpl.class);
	
	private Class<Entity> type;
	
	protected GenericImpl(){
		
	}

	protected GenericImpl(Class<Entity> type){
		this.type=type;
		System.out.println("The type "+type+" is set");
	}
	
	@PersistenceContext(name = "PizzeriaPersistenceUnit", type = PersistenceContextType.TRANSACTION)
	EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Entity create(Entity entity) {
		try {
			logger.info("getEntityManager() is null ? === "
					+ getEntityManager() == null);
			System.out.println("In the create method of GenericImpl setter !!!!!!!!!!!! with annotation. t = "+entity);
			Entity mergeEntity = entityManager.merge(entity);
			System.out.println("Pizza Merged Successfully, now returning the merged entity");
			return mergeEntity;
		} catch (HibernateException e) {
			logger.error("Error Occurred @class " + this.getClass().getName(), e);
			System.out.println("Hibernate Error  = " + e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error Occurred @class " + this.getClass().getName(), e);
			System.out.println("Error  = " + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Entity update(Entity entity) {
		try {
			logger.info("getEntityManager() is null === "
					+ getEntityManager() == null);
			System.out.println("In the update method of GenericImpl");
			entityManager.merge(entity);
			System.out.println("Entity Saved Successfully @class \" + this.getClass().getName()");
		} catch (HibernateException e) {
			logger.error("Error Occurred @class " + this.getClass().getName(), e);
			System.out.println("Hibernate Error  = " + e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error Occurred @class " + this.getClass().getName(), e);
			System.out.println("Error  = " + e);
			e.printStackTrace();
		}
		return entity;
	}

	@Override
	public Map<Object,Object> search(String orderBy,String orderType,String searchString) {

		 
		try {
			System.out.println("In the generic Impl.....");
			 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			 System.out.println("In the method getResultsFromCriteria....................");
				
				CriteriaQuery<Entity> query = builder.createQuery(getType());
				Root<Entity> root = query.from(getType());
				Predicate searchCondition = null;
				Path<Object> path = null;

				if(searchString!=null) {
				
					//id=ge=0;name=='prakhar';pizza.name=='';
					String[] conditionsArray  = searchString.split(";");
					
					for (String condition : conditionsArray) {
						System.out.println("condition = "+condition);
						if(condition.indexOf("==")!=-1 && ((condition.indexOf("=="))==(condition.lastIndexOf("==")))) {
							String[] equalsCondition  = condition.split("==");
							String fieldNames = equalsCondition[0];

							String[] internalPaths = fieldNames.split("\\.");
							for (int i = 0; i < internalPaths.length; i++) {
								if(i==0) {
									path=root.get(internalPaths[i]);
									System.out.println("first path set");
								}else {
									path=path.get(internalPaths[i]);
									System.out.println("remaining path set");
								}
							}
							
							
							String fieldValue = equalsCondition[1];	
							System.out.println("now set dynamic....");
							searchCondition = builder.equal(path, fieldValue);
						}else {
							
							String[] split = condition.split("=");
							
							System.out.println("split = "+split);
							
							Path<Double> field = root.get(split[0]);
							
							String fieldValue = split[2];
							
							System.out.println("field = "+field+" & fieldValue = "+fieldValue);
							
							switch (split[1]) {
							case "gt":{
								searchCondition = builder.gt(field, Integer.parseInt(fieldValue));
								System.out.println("In the gt condition ++++++++++++++++++++++++++++++++++++++++");
								break;
							}case "ge":{
								searchCondition = builder.ge(field, Integer.parseInt(fieldValue));
								System.out.println("In the ge condition ++++++++++++++++++++++++++++++++++++++++");
								break;
							}case "lt":{
								searchCondition = builder.lt(field, Integer.parseInt(fieldValue));
								System.out.println("In the lt condition ++++++++++++++++++++++++++++++++++++++++");
								break;
							}case "le":{
								searchCondition = builder.le(field, Integer.parseInt(fieldValue));
								System.out.println("In the le condition ++++++++++++++++++++++++++++++++++++++++");
								break;
							}
								
							default:
								break;
							}
							
						}
					}
					
				
		}
//				System.out.println("field value = "+fieldValue+" set---------------");
				
				query.where(searchCondition);
				System.out.println("check made....................................................................");
				if(orderType==null) {
					orderType = "desc";
				}
				
				if(orderBy!=null) {
					if(orderType.equalsIgnoreCase("asc")) {
						query.orderBy(builder.asc(root.get(orderBy)));
						System.out.println("In the asc");
					}else {
						query.orderBy(builder.desc(root.get(orderBy)));
						System.out.println("In the desc");
					}
				}
				
				
				TypedQuery<Entity> q = entityManager.createQuery(query); 
				System.out.println("getting the result.....");
				List<Entity> result = q.getResultList();
				
				Long recordCount = getRecordCount(entityManager, builder, searchCondition);
				
				Map<Object,Object> hash = new HashMap<Object,Object>();
//				JSONObject json = new JSONObject();
				hash.put("data", result);
				hash.put("count",recordCount);
				return hash;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("In the error while searching the data");
			HashMap<Object, Object> hashMap = new HashMap<Object,Object>();
			hashMap.put("error",e.getMessage());
			return hashMap;
		}

		
	}

	public Long getRecordCount(EntityManager em, CriteriaBuilder builder, Predicate condition) {
		CriteriaQuery criteriaQuery = builder.createQuery();
		Root root = criteriaQuery.from(getType());
		criteriaQuery.select(builder.count(root));
		criteriaQuery.where(condition);
		Query countQuery = em.createQuery(criteriaQuery);
		Long count = (Long) countQuery.getSingleResult();
		System.out.println("total count = "+count);
		return count;
	}
	
	
	@Override
	public Entity findById(PkID pkID) {
		
		try {
			System.out.println("In the findById method of GenericImpl of type "+getType()+" with pkID = "+pkID);
			Entity entity = (Entity) entityManager.find(getType(), pkID);
			System.out.println("Entity Fetched Successfully @class " + this.getClass().getName());
			System.out.println("Entity = "+entity);
			return entity;
		} catch (HibernateException e) {
			logger.error("Error Occurred @class " + this.getClass().getName(), e);
			System.out.println("Hibernate Error  = " + e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Error Occurred @class " + this.getClass().getName(), e);
			System.out.println("Error  = " + e);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Entity> findAll() {
		logger.info("In the method findAll using criteria");
		System.out.println("In the method findAll using criteria and root...");
		CriteriaQuery<Entity> createQuery = entityManager.getCriteriaBuilder().createQuery(getType());
		Root<Entity> variableRoot = createQuery.from(getType());
		createQuery.select(variableRoot);
		List<Entity> resultList = entityManager.createQuery(createQuery).getResultList();
		return resultList;
	}

	@Override
	public Map<String,String> delete(Entity entity) {
		try {
			if(entity!=null) {
				System.out.println("Going to check & remove the entity");
				entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
				Map<String, String> successMap = Utility.getSuccessMap();
				System.out.println("successMap = "+successMap);
				return successMap;
			}else {
				Map<String, String> failureMap = Utility.getFailureMap();
				failureMap.put("reason", "entity cannot be empty");
				System.out.println("failureMap = "+failureMap);
				return failureMap;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, String> failureMap = Utility.getFailureMap();
			failureMap.put("reason", e.getMessage());
			System.out.println("failureMap = "+failureMap);
			return failureMap;
		}
	}

	@Override
	public Map<String,String> deleteById(PkID pkID) {
		try {
			if(pkID!=null) {
				System.out.println("In the deleteById method of GenericImpl of type "+getType()+" with pkID = "+pkID);
				Entity foundEntity = findById(pkID);
				entityManager.remove(foundEntity);
				Map<String, String> successMap = Utility.getSuccessMap();
				System.out.println("successMap = "+successMap);
				return successMap;
			}else {
				Map<String, String> failureMap = Utility.getFailureMap();
				failureMap.put("reason", "primary key cannot be empty");
				System.out.println("failureMap = "+failureMap);
				return failureMap;
			}
		} catch (Exception e) {
			logger.error("Error Occurred @class " + this.getClass().getName(), e);
			System.out.println("Error  = " + e);
			e.printStackTrace();
			Map<String, String> failureMap = Utility.getFailureMap();
			failureMap.put("reason", e.getMessage());
			System.out.println("failureMap = "+failureMap);
			return failureMap;
		}
		
	}

	
	public Class<Entity> getType() {
		return type;
	}

	public void setType(Class<Entity> type) {
		this.type = type;
	}
}
