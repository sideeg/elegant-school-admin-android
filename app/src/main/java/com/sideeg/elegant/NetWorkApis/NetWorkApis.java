package com.sideeg.elegant.NetWorkApis;



import com.sideeg.elegant.model.BaseRespnse;
import com.sideeg.elegant.model.LoginResponse;
import com.sideeg.elegant.model.getStudentResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NetWorkApis {


    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("schoolname") String schoolname, @Field("password") String password);

    @GET("parent")
    Call<LoginResponse> getparent(@Query("school_id") String schoolname);

    @GET("student")
    Call<getStudentResponse> getstudent(@Query("school_id") String schoolid);

    @GET("studentWithSupervisor")
    Call<LoginResponse> studentWithSupervisor(@Query("school_id") String schoolid);

    @GET("studentWithNoSupervisor")
    Call<LoginResponse> studentWithNoSupervisor(@Query("school_id") String schoolid);

}
