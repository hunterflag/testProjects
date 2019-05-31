package tw.com.pubu.hunter.dao;

import tw.com.pubu.hunter.bean.CustomersBean;

public interface CustomersDao {
	public CustomersBean getByAccount(String account);
	public boolean isAccountExist(String account);
	public int getIdByAccount(String account);
	public CustomersBean getById(Integer id);
	public CustomersBean getById(int id);
}
