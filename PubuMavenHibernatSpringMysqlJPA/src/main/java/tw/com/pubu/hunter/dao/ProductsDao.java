package tw.com.pubu.hunter.dao;

import java.util.List;

import tw.com.pubu.hunter.bean.ProductsBean;

public interface ProductsDao {
	public ProductsBean getById(Integer id);
	public ProductsBean getById(int id);
	public List<ProductsBean> getAlls();
}
