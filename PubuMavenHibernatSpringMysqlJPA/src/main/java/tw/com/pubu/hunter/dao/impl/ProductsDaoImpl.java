package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import tw.com.pubu.hunter.bean.ProductsBean;
import tw.com.pubu.hunter.dao.ProductsDao;
import tw.com.pubu.hunter.utils.JpaUtils;
import tw.idv.hunter.tool.HunterDebug;

public class ProductsDaoImpl implements ProductsDao {
	private EntityManagerFactory emFactory;
	
	public ProductsDaoImpl() {
		HunterDebug.traceMessage();
		emFactory= JpaUtils.getEntityManagerFactory();
	}
	
	public void closeFactory() {
		HunterDebug.traceMessage();
		emFactory.close();
	}

	@Override
	public ProductsBean getById(Integer id) {
		HunterDebug.traceMessage();
		EntityManager em = emFactory.createEntityManager();
		
		EntityTransaction etx = null;
		ProductsBean persistentBean = null;

		try {
			etx = em.getTransaction();
			etx.begin();
			persistentBean = (ProductsBean) em.find(ProductsBean.class, id);
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		
		return persistentBean;
	}

	@Override
	public ProductsBean getById(int id) {
		HunterDebug.traceMessage();
		return getById(Integer.valueOf(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsBean> getAlls() {
		HunterDebug.traceMessage();
		List<ProductsBean> result = null;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		
		try {
			etx = em.getTransaction();
			etx.begin();
			result = em.createQuery("FROM ProductsBean")
						  .getResultList();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}

		return result;
	}	
}
