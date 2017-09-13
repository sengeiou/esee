package com.nong.nongo2o.network.api;

import com.nong.nongo2o.entity.bean.ApiListResponse;
import com.nong.nongo2o.entity.bean.ApiResponse;
import com.nong.nongo2o.entity.domain.Follow;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017-9-13.
 */

public interface FollowService {


    /**
     *
     * @param type 1：我的粉丝；2：我的关注
     * @param page 页码
     * @param pageSize  分页大小
     * @return
     */
    @GET("user/follow/search")
    Observable<ApiResponse<ApiListResponse<Follow>>> userFollowSearch(@Query("type") int type, @Query("page") int page, @Query("pageSize") int pageSize);


    @GET("follow/search")
    Observable<ApiResponse<ApiListResponse<Follow>>> getFollowList();

    @GET("user/follow/code")
    Observable<ApiResponse<ApiListResponse<String>>> getFollowCodes();

}
