package bulgogi1216.gmail.spaceforeveryone;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Dell on 2017-08-11.
 */

public class HttpConnector {

    Retrofit retrofit;
    ApiService apiService;
     String resultJson="";

    public String accessPhoto(String url, MultipartBody.Part body, RequestBody name, String id, int start, int end, int mile, final JsonCallback callback) {


        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);

        Map map = new HashMap();
        //map.put("date","2017-10-14");
        Call<ResponseBody> req = apiService.postImage(body, name,start,end,mile,id,/*User.get().getToday()*/"2017-10-16");
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.v("접속 성공","굳굳");


                String json = response.body()+"";
                Log.v("jsonjson",json);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.v("받음","끝");

                t.printStackTrace();
            }
        });


        return "111";
    }






    public String accessServerMap(String url, Map map, final JsonCallback callback) {


        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL).build();
        apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> comment2 = apiService.getPostJoin(map);

        if(url.equals("join")){
            comment2 = apiService.getPostJoin(map);
        }

        else if(url.equals("check")){
            comment2 = apiService.getPostJoinCheck(map);
        }

        else if(url.equals("weekselect")){
            comment2 = apiService.getPostWeek(map);
        }
        else if(url.equals("weekinsert")){
            comment2 = apiService.getPostWeekSubmit(map);

        }
        else if(url.equals("weekdelete")){
            comment2 = apiService.getPostWeekDelete(map);
        }
        else if(url.equals("weekupdate")){
            comment2 = apiService.getPostWeekUpdate(map);
        }
        else if(url.equals("dayselect")){
            comment2 = apiService.getPostDaySelect(map);
        }
        else if(url.equals("dayinsert")){
            Log.v("dadadadayyer","09090901");
            comment2 = apiService.getPostDayInsert(map);
        }
        else if(url.equals("dayupdate")){
            comment2 = apiService.getPostDayUpdate(map);
        }

        else if(url.equals("daydelete")){
            comment2 = apiService.getPostDayDelete(map);
        }
        else if(url.equals("selectposition")){
            comment2 = apiService.getPostPosition(map);
        }
        else if(url.equals("challenge")){
            comment2 = apiService.getPostChallenge(map);
        }
        else if(url.equals("reportselect")){
            comment2 = apiService.getPostReportSelect(map);
        }
        else if(url.equals("reportdelete")){
            comment2 = apiService.getPostReportDelete(map);
        }
        else if(url.equals("reportinsert")){
            comment2 = apiService.getPostReportInsert(map);
        }
        else if(url.equals("reportupdate")){
            comment2 = apiService.getPostReportUpdate(map);
        }
        else if(url.equals("consultselect")){
            comment2 = apiService.getPostAdviceSelect(map);
        }
        else if(url.equals("consultinsert")){
            comment2 = apiService.getPostAdviceInsert(map);
        }
        else if(url.equals("consultupdate")){
            comment2 = apiService.getPostAdviceUpdate(map);
        }
        else if(url.equals("consultdelete")){
            comment2 = apiService.getPostAdviceDelete(map);
        }

        else if(url.equals("checkemail")){
            comment2 = apiService.getPostEmailCheck(map);
        }
        else if(url.equals("getPo")){
            comment2 = apiService.getPo(map,1,"0964bcd8-f1f6-325c-9903-0210ac72ef61");
        }
        else if(url.equals("customerselect")){
            comment2 = apiService.getPostCustomerSelect(map);
        }
        else if(url.equals("customerinsert")){
            comment2 = apiService.getPostCustomerInsert(map);
        }
        else if(url.equals("customerupdate")){
            comment2 = apiService.getPostCustomerUpdate(map);
        }
        else if(url.equals("customerdelete")){
            comment2 = apiService.getPostCustomerDelete(map);
        }
        else if(url.equals("memberselect")){
            comment2 = apiService.getPostMemberSelect(map);
        }
        else if(url.equals("teamweekselect")){
            comment2 = apiService.getWeekPlan(map);
        }
        else if(url.equals("approvalselect")){
            comment2 = apiService.getApproval(map);
        }
        else if(url.equals("teamdayselect")){
            comment2 = apiService.getDayPlan(map);
        }
        else if(url.equals("idsearch")){
            comment2 = apiService.getSearchId(map);
        }
        else if(url.equals("pwsearch")){
            comment2 = apiService.getSearchPw(map);
        }
        else if(url.equals("chartsales")){
            comment2 = apiService.getChartSale(map);
        }
        else if(url.equals("mypage")){

            comment2 = apiService.getMypage(map);
        }



        comment2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {


                    Log.v("callcall",response.message());

                    String json = response.body().string();
                    Log.v("jontest",json);
                    resultJson = json;
                    callback.callback(json);






                } catch (IOException e) {

                    e.printStackTrace();


                }
                catch(NullPointerException e){
                    callback.callback("JsonException");
                    e.printStackTrace();
                    Log.v("NNNNNUUUULLLL","nono");

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //callback.callback(t.getMessage()+"");
                callback.callback("ConnectFail");
                Log.v("failmessage",t.getMessage()+"");

            }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }







    public String accessServerGet(String req, String key, String search, int version, final JsonCallback callback) {

        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL2).build();
        apiService = retrofit.create(ApiService.class);

        Call<ResponseBody> comment2 = apiService.getPoi(req,key,search,version);
        comment2.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {


                        Log.v("callcall",response.message());

                        String json = response.body().string();
                        Log.v("jontest",json);
                        resultJson = json;
                        callback.callback(json);






                    } catch (IOException e) {

                        e.printStackTrace();


                    }
                    catch(NullPointerException e){
                        callback.callback("JsonException");
                        e.printStackTrace();
                        Log.v("NNNNNUUUULLLL","nono");

                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callback.callback("ConnectFail");
                    Log.v("insfinef","fail");

                }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }



    public String accessTmap(String address, final JsonCallback callback) {

        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL2).build();
        apiService = retrofit.create(ApiService.class);

       // =&page=&addressFlag=&fullAddr=서울특별시%20동작구%20보라매로가길%209&callback=&coordType=WGS84GEO&format=&version=1&appKey=0964bcd8-f1f6-325c-9903-0210ac72ef61

       // (@Query("coordType") String req,@Query("appKey") String key,@Query("fullAddr") String search,@Query("version") int version);

        Call<ResponseBody> comment2 = apiService.getPosition("WGS84GEO","0964bcd8-f1f6-325c-9903-0210ac72ef61",address,1);

        comment2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.v("callcall",call.request().body().toString());
            //    Log.v("callcall2",call.request().headers().toString());
                try {


                  //  Log.v("callcall",response.message());

                    String json = response.body().string();
                    Log.v("jontest",json);
                    resultJson = json;
                    callback.callback(json);






                } catch (IOException e) {

                    e.printStackTrace();


                }
                catch(NullPointerException e){
                    callback.callback("JsonException");
                    e.printStackTrace();
                    Log.v("NNNNNUUUULLLL",e.getMessage()+"");

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.callback("ConnectFail");
                Log.v("insfinef","fail");

            }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }




    public String accessParking(String name, final JsonCallback callback) {


        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL3).build();
        apiService = retrofit.create(ApiService.class);

        // =&page=&addressFlag=&fullAddr=서울특별시%20동작구%20보라매로가길%209&callback=&coordType=WGS84GEO&format=&version=1&appKey=0964bcd8-f1f6-325c-9903-0210ac72ef61

        // (@Query("coordType") String req,@Query("appKey") String key,@Query("fullAddr") String search,@Query("version") int version);

        Call<ResponseBody> comment2 = apiService.getParking(name);

        comment2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.v("callcall",call.request().body().toString());
                //    Log.v("callcall2",call.request().headers().toString());
                try {


                    //  Log.v("callcall",response.message());

                    String json = response.body().string();
                    Log.v("jontest",json);
                    resultJson = json;
                    callback.callback(json);






                } catch (IOException e) {

                    e.printStackTrace();


                }
                catch(NullPointerException e){
                    callback.callback("JsonException");
                    e.printStackTrace();
                    Log.v("NNNNNUUUULLLL",e.getMessage()+"");

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.callback("ConnectFail");
                Log.v("insfinef","fail");

            }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }



    public String accessParkingPos(String name, final JsonCallback callback) {


        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL4).build();
        apiService = retrofit.create(ApiService.class);

        // =&page=&addressFlag=&fullAddr=서울특별시%20동작구%20보라매로가길%209&callback=&coordType=WGS84GEO&format=&version=1&appKey=0964bcd8-f1f6-325c-9903-0210ac72ef61

        // (@Query("coordType") String req,@Query("appKey") String key,@Query("fullAddr") String search,@Query("version") int version);

        Call<ResponseBody> comment2 = apiService.getParkingPos(name);

        comment2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.v("callcall",call.request().body().toString());
                //    Log.v("callcall2",call.request().headers().toString());
                try {


                    //  Log.v("callcall",response.message());

                    String json = response.body().string();
                    Log.v("jontest",json);
                    resultJson = json;
                    callback.callback(json);






                } catch (IOException e) {

                    e.printStackTrace();


                }
                catch(NullPointerException e){
                    callback.callback("JsonException");
                    e.printStackTrace();
                    Log.v("NNNNNUUUULLLL",e.getMessage()+"");

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.callback("ConnectFail");
                Log.v("insfinef","fail");

            }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }
    public String accessPublic(String name, final JsonCallback callback) {


        retrofit = new Retrofit.Builder().baseUrl(ApiService.API_URL5).build();
        apiService = retrofit.create(ApiService.class);

        // =&page=&addressFlag=&fullAddr=서울특별시%20동작구%20보라매로가길%209&callback=&coordType=WGS84GEO&format=&version=1&appKey=0964bcd8-f1f6-325c-9903-0210ac72ef61

        // (@Query("coordType") String req,@Query("appKey") String key,@Query("fullAddr") String search,@Query("version") int version);

        Call<ResponseBody> comment2 = apiService.getParkingPos(name);

        comment2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.v("callcall",call.request().body().toString());
                //    Log.v("callcall2",call.request().headers().toString());
                try {


                    //  Log.v("callcall",response.message());

                    String json = response.body().string();
                    Log.v("jontest",json);
                    resultJson = json;
                    callback.callback(json);






                } catch (IOException e) {

                    e.printStackTrace();


                }
                catch(NullPointerException e){
                    callback.callback("JsonException");
                    e.printStackTrace();
                    Log.v("NNNNNUUUULLLL",e.getMessage()+"");

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.callback("ConnectFail");
                Log.v("insfinef","fail");

            }
        });
        if(resultJson.equals("")){
            Log.v("result","null");
        }
        return resultJson;
    }


}
