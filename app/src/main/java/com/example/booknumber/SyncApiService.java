package com.example.booknumber;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SyncApiService {

    @POST("add_contact.php")
    Call<ServerResponse> addPerson(@Body PersonRecord person);

    @GET("fetch_all.php")
    Call<List<PersonRecord>> fetchAll();

    @GET("find_contact.php")
    Call<List<PersonRecord>> searchRemote(@Query("query") String query);
}
