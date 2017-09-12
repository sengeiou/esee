package com.nong.nongo2o.entity.domain;import com.nong.nongo2o.entity.base.PagingEntity;import com.nong.nongo2o.entity.bean.SimpleUser;import java.util.List;/** * <p>Description: 实体类Entity <p> * <p>Module:	 <p> * * * @author wisdom * @date: 2017-07-18 21:20:29 * @since V1.0 */public class Order extends PagingEntity {			/**	* 订单编码	*/	private String orderCode;	/**	* 用户编码	*/	private SimpleUser user;	private String userCode;	/**	* 订单状态 0：待支付；1：支付成功；2：取消支付	*/	private Integer orderStatus;	/**	* 总价	*/	private java.math.BigDecimal totalPrice;	/**	* 支付方式	*/	private Integer payType;	/**	* 收货地址_省	*/	private String consigneeProvince;	/**	* 收货地址_市	*/	private String consigneeCity;	/**	* 收货地址_区县	*/	private String consigneeArea;	/**	* 收货地址_具体地址	*/	private String consigneeAddress;	/**	* 收货人	*/	private String consigneeUser;	/**	* 收货人联系电话	*/	private String consigneeTel;	/**	 * 推广者【userCode】	 */	private String spreader;	private List<OrderDetail> orderDetails;	public SimpleUser getUser() {		return user;	}	public void setUser(SimpleUser user) {		this.user = user;	}	public String getOrderCode() {	    return this.orderCode;	}	public void setOrderCode(String orderCode) {	    this.orderCode=orderCode;	}	public String getUserCode() {	    return this.userCode;	}	public void setUserCode(String userCode) {	    this.userCode=userCode;	}	public Integer getOrderStatus() {	    return this.orderStatus;	}	public void setOrderStatus(Integer orderStatus) {	    this.orderStatus=orderStatus;	}	public java.math.BigDecimal getTotalPrice() {	    return this.totalPrice;	}	public void setTotalPrice(java.math.BigDecimal totalPrice) {	    this.totalPrice=totalPrice;	}	public Integer getPayType() {	    return this.payType;	}	public void setPayType(Integer payType) {	    this.payType=payType;	}	public String getConsigneeProvince() {	    return this.consigneeProvince;	}	public void setConsigneeProvince(String consigneeProvince) {	    this.consigneeProvince=consigneeProvince;	}	public String getConsigneeCity() {	    return this.consigneeCity;	}	public void setConsigneeCity(String consigneeCity) {	    this.consigneeCity=consigneeCity;	}	public String getConsigneeArea() {	    return this.consigneeArea;	}	public void setConsigneeArea(String consigneeArea) {	    this.consigneeArea=consigneeArea;	}	public String getConsigneeAddress() {	    return this.consigneeAddress;	}	public void setConsigneeAddress(String consigneeAddress) {	    this.consigneeAddress=consigneeAddress;	}	public String getConsigneeUser() {	    return this.consigneeUser;	}	public void setConsigneeUser(String consigneeUser) {	    this.consigneeUser=consigneeUser;	}	public String getConsigneeTel() {	    return this.consigneeTel;	}	public void setConsigneeTel(String consigneeTel) {	    this.consigneeTel=consigneeTel;	}	public List<OrderDetail> getOrderDetails() {		return orderDetails;	}	public void setOrderDetails(List<OrderDetail> orderDetails) {		this.orderDetails = orderDetails;	}	public String getSpreader() {		return spreader;	}	public void setSpreader(String spreader) {		this.spreader = spreader;	}}