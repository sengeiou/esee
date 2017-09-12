package com.nong.nongo2o.network.api;

import com.nong.nongo2o.entities.response.Fans;
import com.nong.nongo2o.entity.bean.ApiListResponse;
import com.nong.nongo2o.entity.bean.ApiResponse;
import com.nong.nongo2o.entity.domain.Address;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-8-19.
 */

public interface UserService {

    /**
     * 更新用户信息
     * @param body 请求参数
     * @return 返回
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"/*,"Accept: application/json"*/})
    @PUT("user")
    Observable<ApiResponse<Void>> updateUser(@Body RequestBody body);

    /**
     * 查询关注列表
     * @param userCode 用户编码（自己）
     * @param page  页码
     * @param pageSize  每页数量
     * @return  返回关注列表
     */
    @GET("follow/search")
    Observable<ApiResponse<ApiListResponse<Fans>>> searchFocus(@Query("userCode") String userCode, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 查询粉丝列表
     * @param targetCode  目标用户编码（自己）
     * @param page  页码
     * @param pageSize  每页数量
     * @return  返回粉丝列表
     */
    @GET("follow/search")
    Observable<ApiResponse<ApiListResponse<Fans>>> searchFans(@Query("targetCode") String targetCode, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 取消关注
     * @param userCode 自己的userCode
     * @param targetCode 对方的userCode
     * @return 返回成功/失败
     */
    @DELETE("followByCode")
    Observable<ApiResponse<String>> deleteFocus(@Query("userCode") String userCode, @Query("targetCode") String targetCode);

    /**
     * 关注
     * @param body 请求体
     * @return 返回成功/失败
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"/*,"Accept: application/json"*/})
    @POST("follow")
    Observable<ApiResponse<String>> focus(@Body RequestBody body);

    /**
     * 查询收货地址列表
     * @param userCode  用户编号
     * @param page  页码
     * @param pageSize  每页数量
     * @return  返回地址列表
     */
    @GET("address/search")
    Observable<ApiResponse<ApiListResponse<Address>>> searchAddress(@Query("userCode") String userCode, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 添加地址
     * @param body  请求体
     * @return  返回成功/失败
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"/*,"Accept: application/json"*/})
    @POST("address")
    Observable<ApiResponse<String>> addAddress(@Body RequestBody body);

    /**
     * 编辑地址
     * @param body  请求体
     * @return  返回成功/失败
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"/*,"Accept: application/json"*/})
    @PUT("address")
    Observable<ApiResponse<String>> updataAddress(@Body RequestBody body);

    /**
     * 删除地址
     * @param addressCode  地址编号
     * @return  返回成功/失败
     */
    @Headers({"Content-Type: application/json;charset=UTF-8"/*,"Accept: application/json"*/})
    @POST("address")
    Observable<ApiResponse<String>> deleteAddress(@Query("addressCode") String addressCode);
}