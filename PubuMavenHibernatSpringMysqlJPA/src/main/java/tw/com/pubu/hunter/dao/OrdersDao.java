package tw.com.pubu.hunter.dao;

import java.util.List;

import tw.com.pubu.hunter.bean.OrdersBean;

public interface OrdersDao {
	public Object insert(OrdersBean insObj);
	public Object insert(int ctm_id);
	public boolean update(int od_id, int od_total_price);
	public OrdersBean getById(Integer id);
	public OrdersBean getById(int id);
	public List<OrdersBean> getAllsByCustomer(int ctmId);
	public List<OrdersBean> getAlls();

}
