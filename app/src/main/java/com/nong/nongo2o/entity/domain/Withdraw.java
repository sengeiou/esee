package com.nong.nongo2o.entity.domain;import com.nong.nongo2o.entity.base.PagingEntity;import com.nong.nongo2o.entity.bean.SimpleUser;/** * <p>Description: 实体类Entity <p> * <p>Module:	 <p> * * * @author wisdom * @date: 2017-07-18 21:20:30 * @since V1.0 */public class Withdraw extends PagingEntity {			/**	* 提现单编码	*/	private String withdrawCode;	/**	* 用户编码	*/	private SimpleUser user;	private String userCode;	/**	* 提现金额	*/	private java.math.BigDecimal withdrawMoney;	/**	* 状态 0：待审核；1：审核通过；2：驳回	*/	private Integer withdrawStatus;	public SimpleUser getUser() {		return user;	}	public void setUser(SimpleUser user) {		this.user = user;	}	public String getWithdrawCode() {	    return this.withdrawCode;	}	public void setWithdrawCode(String withdrawCode) {	    this.withdrawCode=withdrawCode;	}	public String getUserCode() {	    return this.userCode;	}	public void setUserCode(String userCode) {	    this.userCode=userCode;	}	public java.math.BigDecimal getWithdrawMoney() {	    return this.withdrawMoney;	}	public void setWithdrawMoney(java.math.BigDecimal withdrawMoney) {	    this.withdrawMoney=withdrawMoney;	}	public Integer getWithdrawStatus() {	    return this.withdrawStatus;	}	public void setWithdrawStatus(Integer withdrawStatus) {	    this.withdrawStatus=withdrawStatus;	}}