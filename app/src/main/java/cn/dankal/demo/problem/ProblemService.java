package cn.dankal.demo.problem;

import java.util.Map;

import cn.dankal.basic_lib.pojo.LoginResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by root on 18-8-29.
 */

public interface ProblemService {

    @GET("partner/Knowledge?category_uuid=")
    @Headers("X-Access-Token: ea9158e970206dcbead15ea4857f569b")
    Observable<String> getProblemList();


}
