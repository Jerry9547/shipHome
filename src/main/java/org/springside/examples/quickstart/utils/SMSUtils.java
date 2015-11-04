package org.springside.examples.quickstart.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
@author Jerry
@date 2015-10-25
 */

public class SMSUtils
{

	/**
	 * 发送短信验证码
	 * @param phone
	 * @param content
	 * @return String
	 */
	public static String sendMessage(String phone,String content) {
		String inputline = "";
		// 发送内容
		String sign = "航运宝";
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://web.1xinxi.cn/asmx/smsservice.aspx?");
		// 向StringBuffer追加用户名
		sb.append("name=chenminmin008@126.com");
		// 向StringBuffer追加密码（登陆网页版，在管理中心--基本资料--接口密码，是28位的）
		sb.append("&pwd=B57C526FC74B32DEA9A9FBE7ED7A");
		// 向StringBuffer追加手机号码
		sb.append("&mobile="+phone);
		// 向StringBuffer追加消息内容转URL标准码
		sb.append("&content=" + URLEncoder.encode("欢迎注册为航运宝会员，您的验证码是"+content+",5分钟内有效,如不是本人操作,请忽略。谢谢!"));
		// 追加发送时间，可为空，为空为及时发送
		sb.append("&stime=");
		// 加签名
		sb.append("&sign=" + URLEncoder.encode(sign));
		// type为固定值pt extno为扩展码，必须为数字 可为空
		sb.append("&type=pt&extno=");
		// 创建url对象
		try {
			URL url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			// 返回发送结果
			inputline = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputline;
	}
	
	public static String createRandom(boolean numberFlag, int length) {
		String retStr = "";
		String strTable = numberFlag ? "1234567890"
				: "1234567890abcdefghijkmnpqrstuvwxyz";
		int len = strTable.length();
		boolean bDone = true;
		do {
			retStr = "";
			int count = 0;
			for (int i = 0; i < length; i++) {
				double dblR = Math.random() * len;
				int intR = (int) Math.floor(dblR);
				char c = strTable.charAt(intR);
				if (('0' <= c) && (c <= '9')) {
					count++;
				}
				retStr += strTable.charAt(intR);
			}
			if (count >= 2) {
				bDone = false;
			}
		} while (bDone);

		return retStr;
	}

	public static void main(String[] args)
	{
		String res = SMSUtils.sendMessage("15026814977",SMSUtils.createRandom(true,6));
		System.out.println(res);
	}
}
