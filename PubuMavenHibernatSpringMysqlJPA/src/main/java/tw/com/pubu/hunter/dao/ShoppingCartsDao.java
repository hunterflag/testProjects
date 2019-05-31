package tw.com.pubu.hunter.dao;

import java.util.List;

import tw.com.pubu.hunter.bean.ShoppingCartsBean;

public interface ShoppingCartsDao {
	public Integer insert(ShoppingCartsBean insObj);
	public boolean delete(ShoppingCartsBean delObj);
	public boolean delete(int id);
	public int deleteAllByCustomer(int ctmId);
	public boolean isItemExist(int ctmId, int pdId);
	public ShoppingCartsBean getById(Integer id);
	public ShoppingCartsBean getById(int id);
	public List<ShoppingCartsBean> getItemsByCustomer(int ctmId);
	public List<ShoppingCartsBean> getAlls();
	public boolean update(ShoppingCartsBean updObj);
}
