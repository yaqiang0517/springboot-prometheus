package com.prometheus.prometheus.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpInvokeUtil {
	private static Log logger = LogFactory.getLog(HttpInvokeUtil.class);
	
	public static String getHttpGetReturn(String url) throws Exception {
		String ret = null;
		SSLContext sslContext = SSLContexts.createSystemDefault();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ret = EntityUtils.toString(entity, "UTF-8");
			}
		} finally {
			response.close();
		}
		return ret;
	}

	public static String getHttpPostReturn(String url, String json)
			throws Exception {
		logger.info("[DTCenter]Call-sp:" + url + "  " + json);
		String ret = null;
		SSLContext sslContext = SSLContexts.createSystemDefault();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslContext, NoopHostnameVerifier.INSTANCE);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();

		HttpPost httppost = new HttpPost(url);
		httppost.addHeader("Content-Type", "application/json");
		httppost.setEntity(new StringEntity(json, "UTF-8"));

		CloseableHttpResponse response = httpclient.execute(httppost);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ret = EntityUtils.toString(entity, "UTF-8");
			}
		} finally {
			response.close();
		}
		logger.info("[DTCenter]Call-sr:" + ret);
		return ret;
	}

	public static String getHttpPostReturn(String url,
			Map<String, String> param) throws Exception {
		logger.info("[DTCenter]Call-sp:" + url + "  " + param);
		String ret = null;
		SSLContext sslContext = SSLContexts.createSystemDefault();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				sslContext, NoopHostnameVerifier.INSTANCE);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).build();
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		Iterator<String> it = param.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			nameValuePairs.add(new BasicNameValuePair(key, (String) param
					.get(key)));
		}
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

		CloseableHttpResponse response = httpclient.execute(httppost);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				ret = EntityUtils.toString(entity, "UTF-8");
			}

		} finally {
			response.close();
		}
		logger.info("[DTCenter]Call-sr:" + ret);
		return ret;
	}




    public static String get() throws ClientProtocolException, IOException {
        //首先需要先创建一个DefaultHttpClient的实例
        HttpClient httpClient = new DefaultHttpClient();
        //先创建一个HttpGet对象,传入目标的网络地址,然后调用HttpClient的execute()方法即可:
        HttpGet httpGet = new HttpGet();
        String url = "http://10.100.103.16:8080/api/v1/clusters/fosun_hdp02/services/HDFS/components/NAMENODE";
        httpGet.setURI(URI.create(url));
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//        httpGet.addHeader("Accept-Encoding","gzip, deflate");
        httpGet.addHeader("Accept-Language","zh-CN,zh;q=0.9,en;q=0.8");
        httpGet.addHeader("Cache-Control","max-age=0");
        httpGet.addHeader("Connection","keep-alive");
        httpGet.addHeader("Cookie","grafana_sess=9f510720cb562c34; AMBARISESSIONID=1edoe3v94eghs1q3wmomwb87g1");
        httpGet.addHeader("Host","10.100.103.16:8080");
        httpGet.addHeader("Upgrade-Insecure-Requests","1");
        httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        httpGet.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        HttpResponse response = httpClient.execute(httpGet);
        String httpEntityContent = getHttpEntityContent(response);
        httpGet.abort();
        return httpEntityContent;
    }

    /**
     * 获得响应HTTP实体内容
     * @param response
     * @return
     * @throws java.io.IOException
     * @throws java.io.UnsupportedEncodingException
     */
    private static String getHttpEntityContent(HttpResponse response) throws IOException, UnsupportedEncodingException {
        //通过HttpResponse 的getEntity()方法获取返回信息
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            br.close();
            is.close();
            System.out.println(sb.toString());
            return sb.toString();
        }

        return "";
    }
}
