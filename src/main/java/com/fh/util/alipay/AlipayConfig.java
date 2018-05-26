package com.fh.util.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fh.util.DateUtil;

/**
 * 支付宝支付的配置
 *
 */
public class AlipayConfig {
	// 网管网址
	 public final static String URL="https://openapi.alipay.com/gateway.do";//正式环境
//	public final static String URL = "https://openapi.alipaydev.com/gateway.do";// 沙河测试

	// APPID
	//沙盒环境
//	public final static String AppID = "2016090800463803";
	//测试环境
//	public final static String AppID = "2017122501206678";
	//正式环境
	public final static String AppID = "2018022602275557";

	// 私钥
	//沙盒环境
//	public final static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCd9fiAlRH9WmrUwXZUYgXzBcY9OQnZHn0lsz2qfbgP9wjPPLfjAvt8J8gKhC1BNF06v7qYOo/TVvIh1sHYE2Iy1Fe4K+zvZ5e24/XWft2IybrJqhsWjp2gtVw3kEFEhUoDMG0MKZfTgoeLobIWLKHEe6Ml1KYniXQLKrd+skrwCeU4qjv7VuhsFk8KW9bAmv1lo86P1SAjeAQFsd3LT9blGdoCyawy17qZD/zDsEr+TbPXj5tZHyOSuqyVSim3L8RJ+eIR8NDGDzsof5feXQgVh/UUSWw2t6RCDYJslIOLp1FPRv6oCUQa4ahBEbjBogzAE3ZzLQSrD9DwQ9KDw34nAgMBAAECggEBAJHtyBr6HLmDS4isBeZk/HwMTJ0I0clMum8WzRRxupa9MN1LidJmUfDcS5kzjR9dAfghYn/6Mh8bhuuuHDzDg4fT1gomQmjVujpTFS2I3J7fRvnBde776DGs+rm2QiXMQZj6pvOChWn4f8KGqiCEMRp04j8z2iBSgutCFm0sx8BC3BUbPma6Lmevp8TV13K5qM0KHBSf1YIHMES7IkTqCWGTpqQh8+cwRvFNLHkiWpMnLRRG1GkNQvbfRFe25JnhJzwyyI7KOwIzl1QREsxtvSeQ8KPaIfCHbNoMBKh/RXzuNjRw6qJ9HZFdvbBMTsR3flpqrGVa4ZW6XaOnNN9ndIECgYEA6oRrmYdsDzoy+JYQDtbvwhqrON96iK5Rk54MY46bprDzpdvyosBB9NY50F2UW21sDbpoOEiexhQSutI9M0+WRGeOJxVxYIC2/oCojlWEavmese0Fk2nP6VlVIkiyWtguIcl15S6bJKnfRQtowVlZvKwLHFKdaJKy1NWu199srMECgYEArG5AvgnAc3r8ZqI977cYHZPya16N5717+bwgFZd2UJ6Cz2W9xdzMVVrFKxdNtTfRhF/rIIvE71GDn2qoacT9f1Zn+PdJBqN/PYpwb1LVA1NvsMWbGiU3EDdon5Qscxebuci4VH2c68QOJnuxVXlytu1EkDK0CVV7oTEi2hTunOcCgYEAsvLIiCZltljafAVFy40G9j4TWRpuDc22QcXf/jfbXZ64zF7BDp9VtNWi1/QQOJOOWAtda1U+kjbO8+9o87ZPG3NJbVlXYiPVfu39sF5g5KAFEA+kqY+cLpsT8001i0xaJz3q0C54A8n7rFvAW4kqiGVfNQV1R05pVy6Wc/LwogECgYAyEO1BgKldnocs8czUDrC03eFUpabEc/NMZwBV5J2Y+P14ZM0+Z0b5PZrkUSbiCGwQaJ1n3M14w41ZO2ndFDKax6b21i5g/BYU816EJGPvTEmw5R+M7uNXFQ+OzkI9xMwVnJ9RAQwQNOP67o1mCRZy4N+4ktBNqYF1I0RAWDWUXwKBgBSDYAx5HhaEJevyExonllyApW2sPLwO0rQb4GH1OObMwW+s2CYdjv8B0P77YvJM5BJ4p1HJN8QlEmlwn6SJ0Hiqi1pk8ZRMq41W+WRCw8BhQJVhA0RrzQbx0s1mGwvB/2becB2RNMbTy/Rs6wXxRNU2ssREF5E3u6ty9Q/9DdkL";
	//测试环境
//	public final static String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCC89Rt8v5tD4rynVLKuJKci4o6LrWzQt6iDg2mxMA/rfakgsxWYnA/Z/fhjPEd8VAACmy2Xi902jePoaoFxo3IqJrh2NsEGtaPkl1jzB2CUVoKE/gDhuYYJT0NU9f9n4zkU3Jfh+1/WAc8UMHy0RBPYMKM8klYoG5+Y69BahH2zjuglgcpNN50ul2yIF5L+NNAVbeYAWEK9rweNBQndJxR5eoljPoB1t0mDquAOsfXpMspwfJh4H9RETaALPP/KXdtyG8K6SgXAz6tmGzGLu2ainc0hT7cwqmTOndTT2i81iITvBPbDkma0nzgLd/a5qNybFC0NjjaSszpPHXQdqZ9AgMBAAECggEANjzXV1K1nmY5npMan2wjDUOeVMrT5xNjMquva8tB9R4QccLa70yGQTzGplzB4Sz5fYrTzBTmgvd+27aRvSKnxtxOigxPajvTclOk6pi4ACnBqTrsFknhM075InP/mcRjjo4haP/blkrXdFveeLuujbH94nLXfiW0nhSbmfRczGO3AwwIO/thglPZm6Ng7ARSQJ6S3gBoCaa8gN36MCpmZaeYH9YPc6+LK3ZJkPgy866Dc/YxLFjc1Td19aGoPrNigB4Q3faMtiJ3CgHzqUSR8dU099kwDsTauVfPAMGzhvMjM45LteDxbosxXb5xbrBvWiCKz9V/ZcnfjobPr1jR4QKBgQC8bJ2gu20zHqBVOlzP2kpx2v5NiAokA8TTgrNtgMt4yG9+CxJOz21/dTOa0CFilvCApA3wRPF5OpoxnJmlXbIQu3Ecfwb5uyXdMXlsSPMUunzsp28jWzKdRxfuG7iKXDU0xacJKZW3zkgGmQrVV80Lkvc4zJTWfSf0Fdqhy5AqrwKBgQCx6q4gfK9SoR+UR850Hi9DspgKpq7TTdPD3gaLgbj/K3i06uM/lutznkgKz1GRgUZx4jBM71fP25LnyvuF2zn5Vlzle+TL7bdhhKM2Gnff6DWdvjiqB9D+9bitWiN3Tq8qqGGN75jxn+gYfEFN1w6/O7uq4HW0zJAfcVAwnO0ckwKBgCsrW2Xi6x7mKme8al04rwus18ydmW0s6+BltoQlGPkAOwMOuFAUGAs2p+8VNkDnQgzHCWZ99TEIKyGotk77y3sn5r9XnxqCNSebWS1k/URHga2aoszf7RW5nxLviPUyMQJ5Mk7YFzQ4b6oLUD8W0aSQNFHwFT2bCRdUFnYxNEODAoGAA7ErC+GKgdx2G02iKya1NGJwZUgwOJJU4quo1xxKCoGMb7SwIpGvMScHt7G1r59PJu3asr4ExHt6APVlYtG50kLIirqXdOPp+kwWVvkVo7/d3PgllOG0pJXUz0QfmEo5uFjmLVvbgKgg2OE7ObJMzAshSdp+ltHn678eOuQGx6ECgYEAhsFGo4IJJUhLklxx2BC2YADKiP2vppj0L8KL/saNQ1N9xZC0deImav+CxqpXj4oD18//35/yMvGS0OH7LvHUKs8/lIHWrRDq89ITzhS4NlljUZSF2YxoayrVI9wcRuESpIQzQVtTNzbD2apopT1IB2qkwFyeKMd5ANjadvkIv/U=";
	//正式环境
	public final static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDQdpIEAe1ZuADAC9Kg1gqcYFn+lKjVvmr4n4vq/MmxWO6V/qWEE5XfD3qwypJaIJPTE1dQpEh64DazCIV4VZoOHRyWiyDbwbJEfLr6h+0rmR84pWNkE9kUVdlu8B2SpfLimhn0Jy8MToZdY7QCRqhtf+yAL9XzXGbp85FoSEyXtd0Fwm1p+EuIkQ0Jl3oUEUJ4+rIpVJXuKYP5RwZ+50oBeoKd+EgL5IY3KXvvu3JralobJ8aPqLdQyb75jtM4TZt0eJftdHnUdY1t3D9XFpw6w9/OngIhvjQS8+BElYT2V0OBUDt8WttBRASbFiAFOrsX7bqtq/CROQhlXz4v190pAgMBAAECggEAIh1VVeyO0CDploQYyxNcFy05bQ0c+CW6UceiWSlXRHueR9OteIjP/PoGhQoRm0g7iM5geqT364vnx7K8TO4NUQP4mrZYAT6Yktaqrrcx2/GqB1Yhhs4st0Q5WgtXe5lGv0iiuK705xmuqXfYjIrNo2XM6UlqxPG5Mc4XXK6AQk9H2PNQkKkFzABS79+igEyy54sq0/ndDQFhmyf1KFckKOUJHSRbV8tp+3dUnCHN2bDfbkwn1LnnP0CC4LJ4dRrRv6IwiXKKRMWJxID2qD1SXJGMd7SzalpViX0x8D2ILucfsDl9K66HpPqlo08cjRIg3CvPcXnS9c2ULr2kd/+PJQKBgQD9Aj1ODhax0uwvmc7XPFKHwJGz+Ofvim10m1ggkq+6nrhbWu7vshNwPohPI38GhN4tRGv04Z2JMiOrfPtQmbVFsNFzPUpz+84lKN0u6cJd6zv62lMyN1SbRb61WeYJMsqsJ2eifkieb9RfsBYDSYqUFjN5mHuyLA1gHsOk8+8pvwKBgQDS7YIsm2hhYIXJ1cnF3F710Ls8/H3oLU1KhsjFZ8g4kll2MaX3CAu27QWFOR5eyNAUs+TlNTkItmqjV8j5YVbjcU4CuwXeDGhU0J2Bfes6LxIMyUyflCd7KPHGIST1dsD4d72+FN+snEu0uHmKH1/npmktyha3hIvHri58etojFwKBgBdKanxR/bLW+NkU7p6PtgU7K+6GXkuF0benSUCOYFDspYn7X/1+xh1SFFg8q0fdWVqPbuS3FDp5/CCgO75at/43XcCfBsiQiJSRI3NbH4Jn0jiwenIn98jnbbviD7QPEesxIXAq8eJr5v/8BLq+vVwOgySRbemj9oM/scDwl7afAoGAH3Xbd6nKXNPXwK4JhWCELWfvtX33jZzMLXSDF6aOH5n8i/uFxJ3HmYYqz58gX4TWPHBEkMdqdVk3+JsMV93j5tKsjw20J1V0bkobZAgTAL+qkounyRmA9ga6YRHqgE4xJB5AB8z3O7ioNhFZ13csh9GzWGgPEVpMuwDI4Do2tQsCgYBxgiviwRy+rXMceYt6H0oZUE4TBpTR9rHaV4EYMnEial7faRPFQHzY4SS4VqX7cTlvykaJfEyPu8uEU5rHdF65ah4tkqrhKIkggYZDMcIdIcmlZp5TqphHkKC1YXKlJo1d2RKx5jQqjdxw8cuQzd3EcS9kWMANTgX/KyBFRpPPWA==";

	// 支付宝公钥
	//沙盒环境
//	public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2PKBdK5VMkLsmZrI38PScMlJmcpZB+x/nKb7V05uWlZUSJLVYYKwnxJbnNohrhSYA9PVUpApuqN4szBflrL0kAIS+qZX1Rz14heA/2wJa37OjUs30h8gulH1fbVV7E3n0pvbOJQ9X8Z0JAy1z//arZiC3WFYwogt2ITLgRQBQptrOJDofdZoOm6hTDmL7spPYMPk6zJlbQW25IQLZaHOwzt3LG6nfMG9o2NzS0Rudu7W4ZkY8NoW7323kT6VZ/sNCByMpxwrk9BljXSZKfcJ2g3tuO7iSAXoSs3Xercih8vm+nBDCyWO3owRb4fBvGIaORq5d8mwVI44l8XAMsyLoQIDAQAB";
	//测试环境
//	public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAju9KMDbaA5Gio2TshvnffpYao/peSA+2QM3unLAxsEy4k7KEQm6cU7TBt5V4zHelIHCvS9i14YsnG55d3SZZhS0CouttEY9a87pTQON9U/TLp3zVLsLFizPyrdUl236lQg5uRB6DB0lwyalR1E0LLTE+Vxzjio3iWJFDE9iMWkg8Rj2wVI5ZkFrN5FhM/jEioYe9dNT8n2G6GVBf2TQEQk5Zz9+5yeWkSVfccrc+55jlpWqXqbTCwSsn03zdpPTBwqpIhODfHEZZy3N+S3jSNT6OzAnA/tqi0+Wyspuns+YxRlN72kA9V72BQbhMSVic4gq3c1QQWbL8Ch8pJgkCQQIDAQAB";
	//正式环境
	public final static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlHUsDQZhq+EcJpJoPwGOvy7aHytf0Ud0MggZxgx+1A7V7iJRizhWoQ97njR+0jXcP///grnjEMgfTFkirTFDNQKyIBZ2pf3EW7qrSRc4NRRF1SSogiNcrOCf2Qqv8PWfA1w8j9AzM/BwUlrn+KBgukIEPfIIg9KDrFV0X8+Ar1YSNPLC68NSBo7lk5GgeJfkUbcZAY/ao1OijhReXjPZ0lFlXFXvKhX62LHJnb/E3XCsq7zYYpmbgqVaf+XxFRdQhKKQLxDk4kR49hKFWZX/iz6q04KwTmtB5/OvUBTMTPT0B/v0/v39VVv8/i9L0o1aLjEgEYxeb3h50F9IFq2sywIDAQAB";

	// 签名算法类型(根据生成私钥的算法,RSA2或RSA)
	public final static String SIGNTYPE = "RSA2";

	// 请求数据格式
	public final static String FORMAT = "json";

	// 编码集
	public final static String CHARSET = "utf-8";

	public final static String METHOD = "alipay.trade.app.pay";

	public final static String VERSION = "1.0";

	//沙盒环境
	// public final static String PID ="2088102174689271";
	//测试环境
	// public final static String PID ="2088521510747675";
	//正式环境
	public final static String PID ="2088021297819629";

	//沙盒环境
//	public final static String TARGETID ="vlfaqk6808@sandbox.com";
	//测试环境
//	public final static String TARGETID ="fabmagic@fabmagic.cn";
	//正式环境
	public final static String TARGETID ="huatex@huatex.com";
	
	// 接收通知的接口名（应该是客户端支付成功之后，回调到服务端的接口的链接）
	//测试环境
	// public final static String SERVICECALLBACK = "http://47.100.116.158:8080/app/alipay/getAlipayCallBack";
	//正式环境
	public final static String SERVICECALLBACK = "http://106.14.82.154/bumofang/app/alipay/getAlipayCallBack";

	/**
	 * 构造支付订单参数列表
	 * @param biz_content 业务数据
	 * @param notify_url 支付宝异步回调的数据
	 * @return
	 */
	public static Map<String, String> buildOrderParamMap(String biz_content,String notify_url) {
		Map<String, String> keyValues = new HashMap<String, String>();
		keyValues.put("app_id", AppID);
//		keyValues.put("pid", PID);
		keyValues.put("biz_content", biz_content);
		keyValues.put("charset", CHARSET);
		keyValues.put("method", METHOD);
		keyValues.put("sign_type", SIGNTYPE);
		keyValues.put("timestamp", DateUtil.getTime());
//		keyValues.put("target_id", TARGETID);
		keyValues.put("version", VERSION);
		keyValues.put("notify_url", notify_url);//异步回调的地址
		return keyValues;
	}

	/**
	 * 构造支付订单参数信息
	 * 
	 * @param map 支付订单参数
	 * @return
	 */
	public static String buildOrderParam(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());

		boolean isEncode = true;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(buildKeyValue(key, value, isEncode));
			sb.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, isEncode));

		return sb.toString();
	}

	/**
	 * 拼接键值对
	 * 
	 * @param key
	 * @param value
	 * @param isEncode
	 * @return
	 */
	private static String buildKeyValue(String key, String value,boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * 对支付参数信息进行签名
	 * 
	 * @param map
	 *            待签名授权信息
	 * 
	 * @return
	 */
	public static String getSign(Map<String, String> map, String rsaKey,boolean rsa2) {
		List<String> keys = new ArrayList<String>(map.keySet());
		// key排序
		Collections.sort(keys);

		StringBuilder authInfo = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			authInfo.append(buildKeyValue(key, value, false));
			authInfo.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		authInfo.append(buildKeyValue(tailKey, tailValue, false));

		String oriSign = SignUtils.sign(authInfo.toString(), rsaKey, rsa2);
		String encodedSign = "";

		try {
			encodedSign = URLEncoder.encode(oriSign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "sign=" + encodedSign;
	}

}
