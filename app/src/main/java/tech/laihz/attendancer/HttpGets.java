package tech.laihz.attendancer;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class HttpGets {
    private String Str;
    private OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
                //System.out.println("$$$￥￥￥"+response.body().string());
                String result=response.body().string();
                System.out.println("$$$"+result);
                return result;

        }
    }

    String runBaidu(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            //System.out.println("$$$￥￥￥"+response.body().string());
            int result=response.code();
            String results=""+result;
            System.out.println("$$$$$$$$$$$$"+result);
            return results;

        }
    }
    String runBaidud(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            //System.out.println("$$$￥￥￥"+response.body().string());
            String result=response.toString();
            System.out.println("$$$$$$$$$$$$"+result);
            return result;

        }
    }


}

