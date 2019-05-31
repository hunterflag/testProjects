package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.OrderDetailsDao;
import tw.com.pubu.hunter.utils.JpaUtils;
import tw.idv.hunter.tool.HunterDebug;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
	EntityManagerFactory emFactory;
	
	public OrderDetailsDaoImpl() {
		HunterDebug.traceMessage();
		emFactory = JpaUtils.getEntityManagerFactory();
	}
	
	public void closeFactory() {
		HunterDebug.traceMessage();
		emFactory.close();
	}
	
	@Override
	public Object insert(OrderDetailsBean insObj) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		Object key = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			//XXX Detached again!
			HunterDebug.showKeyValue("insObj: ", insObj.toString());
			HunterDebug.showKeyValue("Managed? ", String.valueOf(em.contains(insObj)));
			HunterDebug.showKeyValue("Managed? ", String.valueOf(em.contains(insObj.getOdBean())));
			HunterDebug.showKeyValue("Managed? ", String.valueOf(em.contains(insObj.getPdtBean())));
			em.merge(insObj);
//			em.persist(insObj);
			key = insObj.getOddt_id(); 
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return key;
	}

	@Override
	public Object insert(ProductsBean pdtBean, int number, int price, OrdersBean odBean) {
		HunterDebug.traceMessage();
		Object key = null;
		OrderDetailsBean insObj = new OrderDetailsBean(pdtBean, number, price, odBean);
		key = insert(insObj);
		return key;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetailsBean> getAllsById(int od_id){
		HunterDebug.traceMessage();
		List<OrderDetailsBean> result = null;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			String qryHqlStr = "FROM OrderDetailsBean AS oddtb WHERE oddtb.odBean.od_id = :od_id";
			Query query = em.createQuery(qryHqlStr);
			query.setParameter("od_id", od_id);
			result = query.getResultList();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
}
