package tech.laihz.attendancer;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class HttpGets {

    private Request RequestGen(String url){
        return new Request.Builder().url(url).build();
    }
    private OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = RequestGen(url);
        try (Response response = client.newCall(request).execute()) {
                //System.out.println("$$$￥￥￥"+response.body().string());
                String result=response.body().string();
                System.out.println("$$$"+result);
                return result;

        }
    }

    String runBaidu() throws IOException {
        Request request  = RequestGen("https://www.baidu.com");
        try (Response response = client.newCall(request).execute()) {
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

