package tw.com.pubu.hunter.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="customers")
public class CustomersBean implements Serializable{
	
	private Integer ctm_id;	
	private String ctm_account;
	private String ctm_password;
	private List<ShoppingCartsBean> scBeans = new ArrayList<>();	//此為關係, 由多方FK 維護, 本資料表無此欄位
	
	public CustomersBean() {
		super();
	}
	
	public CustomersBean(String ctm_account, String ctm_password) {
		super();
		this.ctm_account = ctm_account;
		this.ctm_password = ctm_password;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCtm_id() {
		return ctm_id;
	}
	public void setCtm_id(Integer ctm_id) {
		this.ctm_id = ctm_id;
	}
	
	public String getCtm_account() {
		return ctm_account;
	}
	public void setCtm_account(String ctm_account) {
		this.ctm_account = ctm_account;
	}
	
	public String getCtm_password() {
		return ctm_password;
	}
	public void setCtm_password(String ctm_password) {
		this.ctm_password = ctm_password;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	public List<ShoppingCartsBean> getScBeans() {
		return scBeans;
	}

	public void setScBeans(List<ShoppingCartsBean> scBeans) {
		this.scBeans = scBeans;
	}

	@Override
	public String toString() {
		return "CustomersBean [ctm_id=" + ctm_id + ", ctm_account=" + ctm_account + ", ctm_password=" + ctm_password
				+ ", scBeans=" + scBeans + "]";
	}

	
//	@Override
//	public String toString() {
//		return "CustomersBean [ctm_id=" + ctm_id + ", ctm_account=" + ctm_account + ", ctm_password=" + ctm_password
//				+ "]";
//	}
	
}
