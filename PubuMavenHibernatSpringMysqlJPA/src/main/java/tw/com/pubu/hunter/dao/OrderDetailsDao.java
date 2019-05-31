package tw.com.pubu.hunter.dao;

import java.util.List;

import tw.com.pubu.hunter.bean.OrderDetailsBean;
import tw.com.pubu.hunter.bean.OrdersBean;
import tw.com.pubu.hunter.bean.ProductsBean;

public interface OrderDetailsDao {
	public Object insert(OrderDetailsBean insObj);
	public Object insert(ProductsBean pdtBean, int number, int price, OrdersBean odBean);
	public List<OrderDetailsBean> getAllsById(int od_id);
}
