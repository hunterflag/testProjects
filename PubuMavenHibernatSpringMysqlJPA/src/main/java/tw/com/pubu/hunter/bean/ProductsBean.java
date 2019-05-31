package tw.com.pubu.hunter.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="products")
public class ProductsBean implements Serializable {
	private Integer pd_id;
	private String pd_name;
	private Double pd_price;

	public ProductsBean() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPd_id() {
		return pd_id;
	}

	public void setPd_id(Integer pd_id) {
		this.pd_id = pd_id;
	}

	public String getPd_name() {
		return pd_name;
	}

	public void setPd_name(String pd_name) {
		this.pd_name = pd_name;
	}

	public Double getPd_price() {
		return pd_price;
	}

	public void setPd_price(Double pd_price) {
		this.pd_price = pd_price;
	}

	@Override
	public String toString() {
		return "ProductsBean [pd_id=" + pd_id + ", pd_name=" + pd_name + ", pd_price=" + pd_price + "]";
	}
	
	
}
