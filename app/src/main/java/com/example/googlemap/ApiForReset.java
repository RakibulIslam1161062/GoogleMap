package com.example.googlemap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiForReset {

    @POST("resetPass")
    Call<ResetPassCred> getResetPost(@Body ResetPassCred resetPassCred);
}
