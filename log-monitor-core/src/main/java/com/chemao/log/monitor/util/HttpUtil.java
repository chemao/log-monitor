package com.chemao.log.monitor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil {

	public static String post(String url, Map<String, String> params) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);
		if (params != null && !params.isEmpty()) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		}

		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			if (response.getStatusLine() == null || response.getStatusLine().getStatusCode() != 200) {
				throw new Exception("response status error");
			}
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} finally {
			response.close();
		}
	}
	public static String get(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try {
			if (response.getStatusLine() == null || response.getStatusLine().getStatusCode() != 200) {
				throw new Exception("response status error");
			}
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity);
		} finally {
			response.close();
		}
	}
}
