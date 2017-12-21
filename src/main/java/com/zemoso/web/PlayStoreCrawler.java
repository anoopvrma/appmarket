package com.zemoso.web;

import com.squareup.okhttp.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class PlayStoreCrawler {
    private String appId;

    public PlayStoreCrawler(String appId){
        this.appId = appId;
    }

    public void crawl() throws IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        try (CloseableHttpClient client = HttpClients.custom().setUserAgent("Mozilla/5.0 Firefox/26.0")
                .build()){

            HttpUriRequest request = RequestBuilder.post()
                    .setUri("https://play.google.com/store/getreviews?authuser=0")
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .setHeader("referer","https://play.google.com/store/apps/details?id=com.wecreatestuff.interlocked")
                    .addParameter("reviewType", "0")
                    .addParameter("pageNum", "1")
                    .addParameter("id", appId)
                    .addParameter("reviewSortOrder", "4")
                    .addParameter("xhr", "1")
                    .addParameter("token", "YT6ukS1hM70PrRY3sW-aZo5hIdA:1513841514828").build();

            try(CloseableHttpResponse response = client.execute(request)) {
                System.out.println(EntityUtils.toString(response.getEntity()));
            }
        }
    }

    public void crawlPost() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "reviewType=0&pageNum=1&id=com.wecreatestuff.interlocked&reviewSortOrder=4&xhr=1&token=YT6ukS1hM70PrRY3sW-aZo5hIdA%3A1513841514828");
        Request request = new Request.Builder()
                .url("https://play.google.com/store/getreviews?authuser=0")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("referer", "https://play.google.com/store/apps/details?id=com.wecreatestuff.interlocked")
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "db4650ad-12c7-5a05-2a5e-97d1c46ce19d")
                .build();

        Response response = client.newCall(request).execute();
    }
}
