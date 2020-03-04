package com.sideeg.elegant.NetWorkApis;



import com.sideeg.elegant.model.BaseRespnse;
import com.sideeg.elegant.model.GetParentAndSupervisorRespnse;
import com.sideeg.elegant.model.LoginResponse;
import com.sideeg.elegant.model.getParentResponse;
import com.sideeg.elegant.model.getStudentResponse;
import com.sideeg.elegant.model.getSuperVisorsResponse;
import com.sideeg.elegant.model.getUsersResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface NetWorkApis {


    @POST("login")
    @FormUrlEncoded
    Call<LoginResponse> login(@Field("schoolname") String schoolname, @Field("password") String password);

    @GET("parent")
    Call<getParentResponse> getparent(@Query("school_id") String schoolname);

    @GET("parentandsupervisor")
    Call<GetParentAndSupervisorRespnse> getparentandsupervisor(@Query("school_id") String schoolname);

    @GET("student")
    Call<getStudentResponse> getstudent(@Query("school_id") String schoolid);

    @GET("supervisor")
    Call<getSuperVisorsResponse> getsupervisor(@Query("school_id") String schoolid);

    @GET("user")
    Call<getUsersResponse> getUser();

    @GET("studentWithSupervisor")
    Call<getStudentResponse> studentWithSupervisor(@Query("school_id") String schoolid);

    @GET("studentWithNoSupervisor")
    Call<getStudentResponse> studentWithNoSupervisor(@Query("school_id") String schoolid);


    @POST("studentsave")
    Call<BaseRespnse> CreateStudent(@Query("name")String studentName,@Query("className")String className,@Query("schoolid") String schoolid,@Query("supervisor_id") int supervisor_id,@Query("parent_id") int parent_id);

    @POST("usersave")
    Call<BaseRespnse> CreateUser(@Query("name")String userName,@Query("schoolname")String schoolname,@Query("password") String password,@Query("mangerPhone") String mangerPhone);

    @POST("supervisorsave")
    Call<BaseRespnse> CreateSupervisor(@Query("schoolid") String schoolid,@Query("name")String userName,@Query("discission")String discission,@Query("password") String password,@Query("phone") String supervisorPhone);

    @POST("parentsave")
    Call<BaseRespnse> CreateParent(@Query("schoolid") String schoolid,@Query("name")String parentName,@Query("password") String password,@Query("phone") String parentPhone);

}
