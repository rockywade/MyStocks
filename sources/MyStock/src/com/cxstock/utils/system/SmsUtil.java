package com.cxstock.utils.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通过短信接口发送短信
 */
public class SmsUtil {

	public static void main(String[] args) {

		// sendSms2("13511111111","您的验证码是：1111。请不要把验证码泄露给其他人。");
		// sendSmsAll(List<PageData> list)

		sendSms("18625162408", "求是学院学生服务平台");
	}

	/**
	 * 给一个人发送单条短信
	 * 
	 * @param mobile
	 *            手机号
	 * @param content
	 *            短信内容
	 */
	public static void sendSms(String mobile, String content) {
		Pattern p = Pattern.compile("^1[3|4|5|7|8]\\d{9}$");  

		Matcher m = p.matcher(mobile);  

		if(!m.matches()){
			return;
		}

		String username = "90002464";
		String password = "88206178";
		String param = "";
		String msgtype = "1";
		if (content.length() > 64) {
			msgtype = "4";
		}
		String source = "zjuSend";
		try {
			param = "username=" + username + "&password=" + password
					+ "&to=" + mobile + "&text="
					+ URLEncoder.encode(content, "GBK") + "&msgtype=" + msgtype
					+ "&source=" + source;
		} catch (UnsupportedEncodingException e) {
			System.out.println("短信提交失败");
		}
		// System.out.println(PostData);
//		String ret = SMS("http://zdt-sms.zju.edu.cn:8086/smsInterface/sendsms",param);
//		System.out.println(ret);

	}


	public static String SMS(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection
					.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}
