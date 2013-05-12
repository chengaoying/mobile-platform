package cn.ohyeah.statics.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RechargeInfo {

	@Id
	private int productId;
	private int sum;
	private String t;
	
	public String getT() {
		return t;
	}
	public void setT(String t) {
		this.t = t;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
}
