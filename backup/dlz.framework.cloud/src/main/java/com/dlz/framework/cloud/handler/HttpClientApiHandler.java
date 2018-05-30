package com.dlz.framework.cloud.handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dlz.framework.cloud.annotation.AnnoCloud;
import com.dlz.framework.exception.RemoteException;
import com.dlz.framework.springframework.iproxy.ApiProxyHandler;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.ValUtil;

@Component
public class HttpClientApiHandler extends ApiProxyHandler {
	
    @Value("${gateway}")
    private String gateway;
     
	@Override
	public Object done(Class<?> clazz,Method method, Object[] args) throws Exception {
		AnnoCloud annotation = clazz.getAnnotation(AnnoCloud.class);
		String servive=null;
		if(annotation!=null){
			servive=annotation.value();
		}
		if("".equals(servive)){
			servive=clazz.getSimpleName().replaceAll("i(.*)Api", "$1");
		}
		String ret= sendHttpPost(gateway+"/"+servive+"/"+method.getName(), getParaMap(method, args));
		return ValUtil.getObj(ret, method.getReturnType());
	}
	
	protected Map<String,String> getParaMap(Method method,Object[] args){
		Map<String,String> m=new HashMap<>();
		Parameter[] parameters = method.getParameters();
		for(int i=0;i<parameters.length;i++){
			if(args[i] instanceof CharSequence || args[i] instanceof Number){
				m.put(parameters[i].getName(), args[i].toString());
			}else{
				m.put(parameters[i].getName(), JacksonUtil.getJson(args[i]));
			}
		}
		return m;
	}
	
	static String charsetNameSend="UTF-8";
	static String charsetNamere="UTF-8";
	public static String sendHttpPost(String url, Map<String, String> para) throws Exception {
		String result = null;
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		int statusCode=-1;
		try {
			if (para != null) {
				NameValuePair[] nameValuePairs = new NameValuePair[para.size()];
				int i = 0;
				for (String strNm : para.keySet()) {
					nameValuePairs[i++] = new NameValuePair(strNm, para.get(strNm));
				}
				postMethod.setRequestBody(nameValuePairs);
			}

			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charsetNameSend);
			statusCode = httpClient.executeMethod(postMethod);

			switch(statusCode){
				case HttpStatus.SC_OK:
					StringBuffer sb = new StringBuffer();
					InputStream in = postMethod.getResponseBodyAsStream();
					BufferedReader br = new BufferedReader(new InputStreamReader(in, charsetNamere));
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					result = sb.toString();
					if (br != null) {
						br.close();
					}
					return result;
				case HttpStatus.SC_NOT_FOUND:
					throw RemoteException.buildException("地址无效:"+url,null);
				case HttpStatus.SC_FORBIDDEN:
					throw RemoteException.buildException("无访问权限:"+url,null);
				default :
					throw RemoteException.buildException("访问异常:"+url+" 返回码:"+statusCode,null);
			}
		} catch (Exception e) {
			throw RemoteException.buildException("访问异常:"+url+" "+e.getMessage(),e);
		} finally {
			postMethod.releaseConnection();
		}
	}
	
	public static void main(String[] args) {
		String[] a=new String[]{"12","23"};
		String json = JacksonUtil.getJson(a);
		System.out.println(json);
		System.out.println(a.getClass());
		System.out.println(ValUtil.getObj(json, a.getClass()));
	}
}
