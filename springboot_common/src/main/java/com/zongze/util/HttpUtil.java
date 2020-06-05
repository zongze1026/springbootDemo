package com.zongze.util;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By xzz on 2020/6/5
 */
public class HttpUtil {


    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";

    // 超时设置
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .setSocketTimeout(10000)
            .build();

    // 编码设置
    private static final ConnectionConfig connectionConfig = ConnectionConfig.custom()
            .setMalformedInputAction(CodingErrorAction.IGNORE)
            .setUnmappableInputAction(CodingErrorAction.IGNORE)
            .setCharset(Consts.UTF_8)
            .build();

    private static HttpClientBuilder getBuilder() {
        List<Header> headers = new ArrayList<>();
        Header header = new BasicHeader("User-Agent", USER_AGENT);
        headers.add(header);
        return HttpClients.custom().setDefaultConnectionConfig(connectionConfig).setDefaultHeaders(headers).setDefaultRequestConfig(requestConfig);
    }

    /**
     * 发送HttpGet请求
     *
     * @param url 请求地址
     * @return
     */
    public static String sendGet(String url) {
        CloseableHttpClient httpclient = getBuilder().build();
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送HttpPost请求，参数为json字符串
     *
     * @param url     请求地址
     * @param jsonStr json字符串
     * @return
     */
    public static String sendPost(String url, String jsonStr) {
        CloseableHttpClient httpclient = getBuilder().build();
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        try {
            StringEntity stringEntity = new StringEntity(jsonStr, Consts.UTF_8);
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }



    /**
     * 提交表单
     * @param url:地址
     * @param headers:需要设置的请求头
     * @param body:表单内容
     */
    public static String sendForm(String url, Map<String, String> headers, Map<String, String> body) {
        CloseableHttpClient httpclient = getBuilder().build();
        HttpPost httpPost = new HttpPost(url);
        String result = null;
        try {
            headers.entrySet().stream().forEach(entry -> httpPost.setHeader(entry.getKey(), entry.getValue()));
            if (!body.isEmpty()) {
                List<NameValuePair> nameValuePairList = new ArrayList<>();
                for (String key : body.keySet()) {
                    nameValuePairList.add(new BasicNameValuePair(key, body.get(key)));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, Consts.UTF_8);
                formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
                httpPost.setEntity(formEntity);
                HttpResponse response = httpclient.execute(httpPost);
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        Map<String, String> headers = new HashMap();
        headers.put("Authorization", "APPCODE 86d51f5a987c4c6382389f7ac3d745f1");
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        String url = "http://yunyidata.market.alicloudapi.com/bankAuthenticate4";
        Map<String, String> body = new HashMap<>();
        body.put("ReturnBankInfo", "YES");
        body.put("cardNo", "6222081204001911076");
        body.put("idNo", "330481198910010033");
        body.put("name", "王占宇");
        body.put("phoneNo", "13967337731");
        String s = sendForm(url, headers, body);
        System.out.println(s);

    }

}





