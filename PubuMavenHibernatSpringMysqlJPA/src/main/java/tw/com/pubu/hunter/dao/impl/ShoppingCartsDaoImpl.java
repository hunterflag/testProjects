package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;
import tw.com.pubu.hunter.dao.ShoppingCartsDao;
import tw.com.pubu.hunter.utils.JpaUtils;
import tw.idv.hunter.tool.HunterDebug;

public class ShoppingCartsDaoImpl implements ShoppingCartsDao {
	EntityManagerFactory emFactory;
	
	public ShoppingCartsDaoImpl() {
		HunterDebug.traceMessage();
		emFactory = JpaUtils.getEntityManagerFactory();
	}
	
	public void closeFactory() {
		HunterDebug.traceMessage();
		emFactory.close();
	}
	
	@Override
	public Integer insert(ShoppingCartsBean insObj) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		Integer key = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			em.persist(insObj);
			key = insObj.getSc_id();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}
	
	@Override
	public boolean delete(ShoppingCartsBean delObj) {
		HunterDebug.traceMessage();
		HunterDebug.showKeyValue("delObj=", delObj.toString());
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		boolean isSuccess = false;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			//先從 Detached 恢復到 Persistent
			ShoppingCartsBean rmvObj = em.merge(delObj);
			em.remove(rmvObj);
			isSuccess = true;
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}
	
	@Override
	public boolean delete(int id) {
		HunterDebug.traceMessage();
		boolean result = false;
		ShoppingCartsBean scBean = getById(id);
		HunterDebug.showKeyValue("scBean=", scBean.toString());
		result = delete(scBean);
		return result;
	}
	
	@Override
	public int deleteAllByCustomer(int ctmId) {
		HunterDebug.traceMessage();
		int result = 0;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		
		String hqlStr = "DELETE FROM ShoppingCartsBean AS scb WHERE scb.ctm_id = :ctmId";
		try {
			etx = em.getTransaction();
			etx.begin();
			result = em.createQuery(hqlStr)
					   .setParameter("ctmId", ctmId)
					   .executeUpdate();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	@Override
	public boolean isItemExist(int ctmId, int pdId) {
		HunterDebug.traceMessage();
		boolean isExist = true;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		
		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctm_id = :ctmId AND scb.pdtBean.pd_id = :pdId";
		try {
			etx = em.getTransaction();
			etx.begin();
			int no = em.createQuery(hqlStr)
						.setParameter("ctmId", ctmId)
						.setParameter("pdId", pdId)
						.getResultList()
						.size();
			if(no > 0) isExist = true;
			else isExist = false;
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isExist;
	}

	
	@Override
	public ShoppingCartsBean getById(Integer id) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		ShoppingCartsBean persistentBean = null;

		try {
			etx = em.getTransaction();
			etx.begin();
			persistentBean = (ShoppingCartsBean) em.find(ShoppingCartsBean.class, id);
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public ShoppingCartsBean getById(int id) {
		HunterDebug.traceMessage();
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId) {
		HunterDebug.traceMessage();
		List<ShoppingCartsBean> result = null;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		
		String hqlStr = "FROM ShoppingCartsBean AS scb WHERE scb.ctm_id = :ctmId";
		try {
			etx = em.getTransaction();
			etx.begin();
			
			result = em.createQuery(hqlStr)
						.setParameter("ctmId", ctmId)
						.getResultList();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShoppingCartsBean> getAlls() {
		HunterDebug.traceMessage();
		List<ShoppingCartsBean> result = null;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			result = em.createQuery("FROM ShoppingCartsBean")
					  .getResultList();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	@Override
	public boolean update(ShoppingCartsBean updObj) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		boolean isSuccess = false;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			ShoppingCartsBean persistentBean = em.find(ShoppingCartsBean.class, updObj.getSc_id());
			persistentBean.setCtm_id(updObj.getCtm_id());
			persistentBean.setPdtBean(updObj.getPdtBean());
			persistentBean.setSc_number(updObj.getSc_number());
			persistentBean.setSc_price(updObj.getSc_price());
//			session.saveOrUpdate(updObj);
//			session.merge(updObj);
			isSuccess = true;
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}
		
}
