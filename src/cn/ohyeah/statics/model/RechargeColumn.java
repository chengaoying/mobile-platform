package cn.ohyeah.statics.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 充值界面中的列数（充过值的游戏数）
 * @author Administrator
 *
 */
@Entity
public class RechargeColumn {

	@Id
	private int productId;
	private String productName;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
