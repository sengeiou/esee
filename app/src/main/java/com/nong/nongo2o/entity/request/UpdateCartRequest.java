package com.nong.nongo2o.entity.request;import com.nong.nongo2o.entity.domain.Cart;import java.util.Date;/** * * * @author wisdom * @date: 2017-07-18 21:20:27 * @since V1.0 */public class UpdateCartRequest {	/**	* 商品数据量	*/	private Integer goodsNum;	private String specCode;	public Integer getGoodsNum() {		return goodsNum;	}	public void setGoodsNum(Integer goodsNum) {		this.goodsNum = goodsNum;	}	public String getSpecCode() {		return specCode;	}	public void setSpecCode(String specCode) {		this.specCode = specCode;	}}