package com.dlz.framework.ssme.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlz.framework.ssme.util.config.ConfigUtil;
import com.dlz.framework.util.StringUtils;
import com.dlz.framework.util.JacksonUtil;

public class DistanceUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DistanceUtil.class);
    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点坐标计算距离
     *
     * @param lat1 纬度1
     * @param lon1 经度1
     * @param lat2 纬度2
     * @param lon2 经度2
     * @return 单位 km
     */
    public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    /**
     * 根据经纬度和距离返回一个矩形范围
     *
     * @param lon      经度
     * @param lat      纬度
     * @param distance 距离(单位为米)
     * @return [lon1, lat1, lon2, lat2] 矩形的左下角(lon1,lat1)和右上角(lon2,lat2)
     */
    public static double[] getRectangle(double lon, double lat, long distance) {
        double w = 0.00000899;
        double h = 0.00002164;

        return new double[]{lon - distance * w, lat - distance * h, lon + distance * w, lat + distance * h};
    }
    
    /**
	 * 根据地址获取经纬度
	 * @author  wangsl: 
	 * @date 创建时间：2015-5-29 下午5:43:37 
	 * @param city
	 * @param address
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Double> getPointByAddress(String city, String address) {
		Map<String, Double> map = new HashMap<String, Double>();
		//String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&city=武汉市&ak=CEc48be10c4d996d6f28eacf7cbd4608";
		String url = ConfigUtil.getConfig("map.addressserver");
		if (StringUtils.isNotEmpty(city)) {
			url = url + "&city=" + city;
		}
		url = url + "&address=" + address;
		//String json = "{status: 0,result:{location:{lng: 116.30814954222,lat: 40.056885091681},precise: 1,confidence: 80,level: \"商务大厦\"}}";
		String json = getURLContent(url);
		Map obj = JacksonUtil.readValue(json, Map.class);
		if (obj.get("status").toString().equals("0")) {
			double lng = (double)((Map)((Map)obj.get("result")).get("location"))
					.get("lng");
			double lat = (double)((Map)((Map)obj.get("result")).get("location"))
					.get("lat");
			map.put("lng", lng);
			map.put("lat", lat);
		} else {
			logger.info(city + ":" + address + ":未找到对应的经纬度");
		}
		return map;
	}
	
	/**
	 * 根据经纬度获取地址
	 * @author  wangsl: 
	 * @date 创建时间：2015-10-8 下午2:23:28 
	 * @param lon  经度
	 * @param lat  纬度
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getAddressByPoint(Double lon, Double lat) {
		//String url = "http://api.map.baidu.com/geocoder/v2/?ak=CEc48be10c4d996d6f28eacf7cbd4608&location=30.494351,114.447858&output=json";
		String url = ConfigUtil.getConfig("map.addressserver");
		if(lon == null || lat == null){
			logger.info("经纬度不能为空");
			return "";
		}
		url = url + "&location=" + lat +","+lon;
		String json = getURLContent(url);
		Map obj = JacksonUtil.readValue(json, Map.class);
		String address = "";
		if (obj.get("status").toString().equals("0")) {
			address = String.valueOf(((Map)obj.get("result")).get("formatted_address"));
		} else {
			logger.info(lon + ":" + lat + ":未找到对应的地址");
		}
		return address;
	}
	
	/**  
     * 程序中访问http数据接口  
     */  
    private static String getURLContent(String urlStr) {             
        /** 网络的url地址 */      
     URL url = null;            
        /** http连接 */  
//     HttpURLConnection httpConn = null;          
         /**//** 输入流 */ 
     BufferedReader in = null; 
     StringBuffer sb = new StringBuffer(); 
     try{   
      url = new URL(urlStr);   
      in = new BufferedReader( new InputStreamReader(url.openStream(),"UTF-8") ); 
      String str = null;  
      while((str = in.readLine()) != null) {  
       sb.append( str );   
             }   
         } catch (Exception ex) { 
           
         } finally{  
          try{           
           if(in!=null) {
            in.close();   
                 }   
             }catch(IOException ex) {    
             }   
         }   
         String result =sb.toString();   
         return result;  
         } 
    
    
}
