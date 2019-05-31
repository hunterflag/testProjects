package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.dao.OrdersDao;
import tw.com.pubu.hunter.utils.JpaUtils;
import tw.idv.hunter.tool.HunterDebug;

public class OrdersDaoImpl implements OrdersDao {
	EntityManagerFactory emFactory;
	
	public OrdersDaoImpl() {
		HunterDebug.traceMessage();
		emFactory = JpaUtils.getEntityManagerFactory();
	}
	
	public void closeFactory() {
		HunterDebug.traceMessage();
		emFactory.close();
	}
	
	@Override
	public Object insert(OrdersBean insObj) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		Object key = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
//			em.persist(insObj); //XXX persistence 不能用 Detached Entity?
			OrdersBean persistenceObj = em.merge(insObj);
			key = persistenceObj.getOd_id();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}

	@Override
	public Object insert(int ctm_id) {
		HunterDebug.traceMessage();
		CustomersDao ctmDao = new CustomersDaoImpl();
		CustomersBean ctmBean = ctmDao.getById(ctm_id);
		
		OrdersBean insObj = new OrdersBean(ctmBean); 
		Object key = insert(insObj);
		return key;
	}
	
	@Override
	public boolean update(int od_id, int od_total_price) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		boolean isSuccess = false;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			OrdersBean bean = em.find(OrdersBean.class, od_id);
			bean.setOd_total_price(od_total_price);
			bean.setOd_state("close");
			isSuccess = true;
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return isSuccess;
	}

	@Override
	public OrdersBean getById(Integer od_id) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		OrdersBean result = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			result = em.find(OrdersBean.class, od_id);
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	public OrdersBean getById(int od_id) {
		HunterDebug.traceMessage();
		OrdersBean result = null;
		result = getById(Integer.valueOf(od_id));
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBean> getAllsByCustomer(int ctmId){
		HunterDebug.traceMessage();
		List<OrdersBean> result = null;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			String qryHqlStr = "FROM OrdersBean AS ob WHERE ob.ctmBean.ctm_id = :ctmId";
			Query query = em.createQuery(qryHqlStr);
			query.setParameter("ctmId", ctmId);
			result = query.getResultList();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdersBean> getAlls(){
		HunterDebug.traceMessage();
		List<OrdersBean> result = null;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		try {
			etx = em.getTransaction();
			etx.begin();
			result = em.createQuery("FROM Orders")
							.getResultList();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}

	
}
