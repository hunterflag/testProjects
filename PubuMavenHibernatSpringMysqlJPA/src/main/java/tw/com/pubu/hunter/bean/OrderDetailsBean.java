package tw.com.pubu.hunter.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="order_details")
public class OrderDetailsBean implements Serializable {
	private Integer oddt_id;
	private ProductsBean pdtBean;	//資料表 欄位名稱為 pd_id
	private Integer oddt_price;
	private Integer oddt_number;
	private OrdersBean odBean;		//資料表 欄位名稱為 od_id

	public OrderDetailsBean() {
		super();
	}
	
	public OrderDetailsBean(Integer oddt_id, ProductsBean pdtBean, Integer oddt_price, Integer oddt_number,
			OrdersBean odBean) {
		super();
		this.oddt_id = oddt_id;
		this.pdtBean = pdtBean;
		this.oddt_price = oddt_price;
		this.oddt_number = oddt_number;
		this.odBean = odBean;
	}

	public OrderDetailsBean(ProductsBean pdtBean, Integer oddt_price, Integer oddt_number, OrdersBean odBean) {
		super();
		this.pdtBean = pdtBean;
		this.oddt_price = oddt_price;
		this.oddt_number = oddt_number;
		this.odBean = odBean;
	}

	public OrderDetailsBean(ProductsBean pdtBean, int oddt_price, int oddt_number, OrdersBean odBean) {
		super();
		this.pdtBean = pdtBean;
		this.oddt_price = oddt_price;
		this.oddt_number = oddt_number;
		this.odBean = odBean;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOddt_id() {
		return oddt_id;
	}

	public void setOddt_id(Integer oddt_id) {
		this.oddt_id = oddt_id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pd_id")
	public ProductsBean getPdtBean() {
		return pdtBean;
	}

	public void setPdtBean(ProductsBean pdtBean) {
		this.pdtBean = pdtBean;
	}

	public Integer getOddt_price() {
		return oddt_price;
	}

	public void setOddt_price(Integer oddt_price) {
		this.oddt_price = oddt_price;
	}

	public Integer getOddt_number() {
		return oddt_number;
	}

	public void setOddt_number(Integer oddt_number) {
		this.oddt_number = oddt_number;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="od_id")
	public OrdersBean getOdBean() {
		return odBean;
	}

	public void setOdBean(OrdersBean odBean) {
		this.odBean = odBean;
	}

	@Override
	public String toString() {
		return "OrderDetailsBean [oddt_id=" + oddt_id + ", pdtBean=" + pdtBean + ", oddt_price=" + oddt_price
				+ ", oddt_number=" + oddt_number + ", odBean=" + odBean + "]";
	}

}
