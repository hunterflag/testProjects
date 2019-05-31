package tw.com.pubu.hunter.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="orders")
public class OrdersBean implements Serializable {
	private Integer od_id;	
	private Timestamp od_time = new Timestamp(new Date().getTime());	//下單時間
	private CustomersBean ctmBean;
	private Integer od_total_price = 0;
	private String od_state = "open";	//XXX DB端用 enum, Java端若改用 enum 要怎麼做?

	public OrdersBean() {
		super();
	}

	//XXX 這應該用不到?
	public OrdersBean(Integer od_id, Timestamp od_time, CustomersBean ctmBean, Integer od_total_price,
			String od_state) {
		super();
		this.od_id = od_id;
		this.od_time = od_time;
		this.ctmBean = ctmBean;
		this.od_total_price = od_total_price;
		this.od_state = od_state;
	}
	
	public OrdersBean(CustomersBean ctmBean) {
		super();
		this.ctmBean = ctmBean;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOd_id() {
		return od_id;
	}

	public void setOd_id(Integer od_id) {
		this.od_id = od_id;
	}

	public Timestamp getOd_time() {
		return od_time;
	}

	public void setOd_time(Timestamp od_time) {
		this.od_time = od_time;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="ctm_id")
	public CustomersBean getCtmBean() {
		return ctmBean;
	}

	public void setCtmBean(CustomersBean ctmBean) {
		this.ctmBean = ctmBean;
	}

	public Integer getOd_total_price() {
		return od_total_price;
	}

	public void setOd_total_price(Integer od_total_price) {
		this.od_total_price = od_total_price;
	}

	public String getOd_state() {
		return od_state;
	}

	public void setOd_state(String od_state) {
		this.od_state = od_state;
	}

	@Override
	public String toString() {
		return "OrdersBean [od_id=" + od_id + ", od_time=" + od_time + ", ctmBean=" + ctmBean + ", od_total_price="
				+ od_total_price + ", od_state=" + od_state + "]";
	}
	
	
	
}
