package com.nong.nongo2o.entity.request;import com.nong.nongo2o.entity.domain.Cart;import java.util.List;/** * * * @author wisdom * @date: 2017-07-18 21:20:27 * @since V1.0 */public class UpdateCartBatchRequest {	/**	* 商品数据量	*/	private List<Cart> cartList;	public UpdateCartBatchRequest() {	}	public UpdateCartBatchRequest(List<Cart> cartList) {		this.cartList = cartList;	}	public List<Cart> getCartList() {		return cartList;	}	public void setCartList(List<Cart> cartList) {		this.cartList = cartList;	}}