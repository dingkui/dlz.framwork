package com.dlz.framework.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具
 * @author dk 2017-06-20
 */
public class EncryptUtil {
	/**
	 * all set GLOBAL_AUTH_KEY
	 */
	public static String GLOBAL_AUTH_KEY = "e317b362fafa0c96c20b8543d054b850";
	
	public static String decode(String $string, String $key) {
		$key=($key==null||"".equals($key))?GLOBAL_AUTH_KEY:$key;
		return authcode($string, "DECODE", $key, 0);
	}
	public static String decode(String $string) {
		return decode($string, null);
	}
	public static String encode(String $string) {
		return encode($string,null, null);
	}
	public static String encode(String $string,String $key) {
		return encode($string, $key, null);
	}
	public static String encode(String $string,Integer $expiry) {
		return encode($string, null, $expiry);
	}
	public static String encode(String $string, String $key,Integer $expiry) {
		$key=($key==null||"".equals($key))?GLOBAL_AUTH_KEY:$key;
		$expiry=($expiry==null||$expiry==0)?0:$expiry;
		return authcode($string, "ENECODE", $key, $expiry);
	}
	/**
	 * 字符串加密以及解密函数
	 *
	 * @param string $string	原文或者密文
	 * @param string $operation	操作(ENCODE | DECODE), 默认为 DECODE
	 * @param string $key		密钥
	 * @param int $expiry		密文有效期, 加密时候有效， 单位 秒，0 为永久有效
	 * @return string		处理后的 原文或者 经过 base64_encode 处理后的密文
	 *
	 * @example
	 *
	 * 	$a = authcode('abc', 'ENCODE', 'key');
	 * 	$b = authcode($a, 'DECODE', 'key');  // $b(abc)
	 *
	 * 	$a = authcode('abc', 'ENCODE', 'key', 3600);
	 * 	$b = authcode('abc', 'DECODE', 'key'); // 在一个小时内，$b(abc)，否则 $b 为空
	 */
	public static String authcode(String $string, String $operation, String $key,int $expiry ) {

		if($string != null){
			if($operation.equals("DECODE")){
				try{
					$string = $string.replaceAll("\\.0\\.", " ");
					$string = $string.replaceAll("\\.1\\.", "=");
					$string = $string.replaceAll("\\.2\\.", "+");
					$string = $string.replaceAll("\\.3\\.", "/");
				}catch(Exception ex){}
			}
		}
		
		int $ckey_length = 20;	//note 随机密钥长度 取值 0-32;
					//note 加入随机密钥，可以令密文无任何规律，即便是原文和密钥完全相同，加密结果也会每次不同，增大破解难度。
					//note 取值越大，密文变动规律越大，密文变化 = 16 的 $ckey_length 次方
					//note 当此值为 0 时，则不产生随机密钥

		$key = md5($key);
		String $keya = md5(substr($key, 0, 16));
		String $keyb = md5(substr($key, 16, 16));
		String $keyc = $ckey_length > 0? ($operation.equals("DECODE") ? substr($string, 0, $ckey_length): substr(md5(microtime()), -$ckey_length)) : "";

		String $cryptkey = $keya + md5( $keya + $keyc);
		int $key_length = $cryptkey.length();

		$string = $operation.equals("DECODE") ? base64_decode(substr($string, $ckey_length)) : sprintf("%010d", $expiry>0 ? $expiry + time() : 0)+substr(md5($string+$keyb), 0, 16)+$string;
		int $string_length = $string.length();

		StringBuffer $result1 = new StringBuffer();

		int[] $box = new int[256];
		for(int i=0;i<256;i++){
			$box[i] = i;
		}

		int[] $rndkey = new int[256];
		for(int $i = 0; $i <= 255; $i++) {
			$rndkey[$i] = (int)$cryptkey.charAt($i % $key_length);
		}

		int $j=0;
		for(int $i = 0; $i < 256; $i++) {
			$j = ($j + $box[$i] + $rndkey[$i]) % 256;
			int $tmp = $box[$i];
			$box[$i] = $box[$j];
			$box[$j] = $tmp;
		}

		$j=0;
		int $a=0;
		for(int $i = 0; $i < $string_length; $i++) {
			$a = ($a + 1) % 256;
			$j = ($j + $box[$a]) % 256;
			int $tmp = $box[$a];
			$box[$a] = $box[$j];
			$box[$j] = $tmp;
			
			$result1.append((char)( ((int)$string.charAt($i)) ^ ($box[($box[$a] + $box[$j]) % 256])));
			
		}

		if($operation.equals("DECODE")) {
			String $result = $result1.substring(0, $result1.length());
			if((Integer.parseInt(substr($result.toString(), 0, 10)) == 0 || Long.parseLong(substr($result.toString(), 0, 10)) - time() > 0) && substr($result.toString(), 10, 16).equals( substr(md5(substr($result.toString(), 26)+ $keyb), 0, 16))) {
				return substr($result.toString(), 26);
			} else {
				return "";
			}
		} else {
			String str = $keyc+base64_encode($result1.toString());
			try{
				str = str.replaceAll(" ",".0.");
				str = str.replaceAll("=",".1.");
				str = str.replaceAll("\\+",".2.");
				str = str.replaceAll("\\/",".3.");
			}catch(Exception ex){}
			//return $keyc+base64_encode($result1.toString()).replaceAll("=", "");
			return str;
		}
	}
	
	
	public static String md5(byte[] input){
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}	
		return byte2hex(md.digest(input));
	}
	public static String md5(String input){
		try {
			return md5(input.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String md5(long input){
		return md5(String.valueOf(input));
	}
	
	public static String base64_decode(String input){
		try {
			return new String(Base64.decode(input.toCharArray()),"UTF-8");
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public static String base64_encode(String input){
		try {
			return new String(Base64.encode(input.getBytes("UTF-8")));
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer();
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs.append("0").append(stmp);
			else
				hs.append(stmp);
		}
		return hs.toString();
	}
	private static String substr(String input,int begin, int length){
		return input.substring(begin, begin+length);
	}
	private static String substr(String input,int begin){
		if(begin>0){
			return input.substring(begin);
		}else{
			return input.substring(input.length()+ begin);
		}
	}
	private static long microtime(){
		return System.currentTimeMillis();
	}
	private static long time(){
		return System.currentTimeMillis()/1000;
	}	
	private static String sprintf(String format, long input){
		String temp = "0000000000"+input;
		return temp.substring(temp.length()-10);
	}
	
	public static void main(String[] args){
//		String number = "加密{xas}";
//		for(int i = 0; i < 100; i++){
//			number+=Math.random();
//		}
//		System.out.println("加密："+number.length()+" " + number);
//		Long d=new Date().getTime();
//		String key=""+Math.random()+Math.random()+Math.random()+Math.random()+Math.random()+Math.random()+Math.random()+Math.random();
//		int l=0;
//		for(int i = 0; i < 1000; i++){
//			String number2 = authcode(number, "ENCODE",key,0);
//			if(number2.length()>l){
//				l=number2.length();
//			}
//			boolean is=authcode(number2, "DECODE",key,0).equals(number);
//			if(!is){
//				System.out.println("error:"+i);
//			}
//		}
//		System.out.println(l);
//		System.out.println(new Date().getTime()-d);
		System.out.println(md5("1"));
	}
 
}
