package tw.com.pubu.hunter.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import tw.com.pubu.hunter.bean.CustomersBean;
import tw.com.pubu.hunter.dao.CustomersDao;
import tw.com.pubu.hunter.utils.JpaUtils;
import tw.idv.hunter.tool.HunterDebug;

@Repository
public class CustomersDaoImpl implements CustomersDao {
	private SessionFactory sessionFactory;
/*	
    private EntityManagerFactory emFactory;
	
	public CustomersDaoImpl() {
		HunterDebug.traceMessage();
		emFactory= JpaUtils.getEntityManagerFactory();
	}
	
	public void closeFactory() {
		HunterDebug.traceMessage();
		emFactory.close();
	}
*/
	@Override
	public CustomersBean getByAccount(String account) {
		CustomersBean result = null;
		//輸入資料檢查
		if(account.isEmpty()) return result;			//沒輸入
		if (!isAccountExist(account)) return result;	//無此帳號
		
//		EntityManager em = emFactory.createEntityManager();
//		EntityTransaction etx = null;
		Session session = sessionFactory.getCurrentSession();
		Transaction tx
		try {
//			etx = em.getTransaction();
//			etx.begin();
			
			String qryJpqlStr = "FROM CustomersBean WHERE ctm_account = :account";
//			Query<CustomersBean> query = em.createQuery(qryJpqlStr);
			Query query = em.createQuery(qryJpqlStr);
			query.setParameter("account", account);
			result = (CustomersBean) query.getSingleResult();
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isAccountExist(String account) {
		HunterDebug.traceMessage();
		boolean result = false;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		try {
			etx = em.getTransaction();
			etx.begin();
			String qryJpqlStr = "FROM CustomersBean WHERE ctm_account = :account";
			Query query = em.createQuery(qryJpqlStr);
			query.setParameter("account", account);
			List<CustomersBean> list = query.getResultList();
			if (list.size() > 0) result = true;
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public int getIdByAccount(String account) {
		HunterDebug.traceMessage();
		int id=0;
		CustomersBean bean = getByAccount(account);
		id = bean.getCtm_id();
		return id;
	}
	
	@Override
	public CustomersBean getById(Integer id) {
		HunterDebug.traceMessage();
		CustomersBean bean = null;
		EntityManager em = emFactory.createEntityManager();
		EntityTransaction etx = null;
		try {
			etx = em.getTransaction();
			etx.begin();
			bean = (CustomersBean) em.find(CustomersBean.class, id);
			etx.commit();
		}catch(Exception e) {
			if(etx!=null) etx.rollback();
			System.out.println(e.getMessage());
		}
		return bean;
	}

	@Override
	public CustomersBean getById(int id) {
		HunterDebug.traceMessage();
		return getById(Integer.valueOf(id));
	}

}

