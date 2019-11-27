package com.kru.batfinder2.interfaces;

import com.kru.batfinder2.models.BatDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BatFinderApi {
    @GET("/api/bats")
    Call<List<BatDTO>> loadBats();
}
