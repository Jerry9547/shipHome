package org.springside.examples.quickstart.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springside.examples.quickstart.utils.LogUtils;

public class BaseController {
	private static final long serialVersionUID = -7274967205815060184L;
	protected HttpServletResponse response;
	protected HttpServletRequest request;


	public HttpServletResponse getResponse() {
		return response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

	public void setServletResponse(HttpServletResponse resp) {
		this.response = resp;
	}

	protected String getIpAddr() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public void writeJson(String result, boolean removeCache) {
		PrintWriter writer = null;
		try {
			if (removeCache) {
				setNocache();
			}
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentLength(result.getBytes("utf-8").length);
			writer = response.getWriter();
			writer.write(result);
			writer.flush();
		} catch (IOException e) {
			LogUtils.log4Error("writeResult error,result=" + result, e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public void writeCorrectJson(String result, boolean removeCache) {
		PrintWriter writer = null;
		try {
			if (removeCache) {
				setNocache();
			}
			JSONObject res = new JSONObject();
			res.put("rs", "500");
			res.put("rm", result);
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentLength(res.toString().getBytes("utf-8").length);
			writer = response.getWriter();
			writer.write(res.toString());
			writer.flush();
		} catch (IOException e) {
			LogUtils.log4Error("writeResult error,result=" + result, e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public void writeErrorJson(String exptionCode, boolean removeCache) {
		PrintWriter writer = null;
		try {
			if (removeCache) {
				setNocache();
			}
			JSONObject res = new JSONObject();
			res.put("rs", exptionCode);
			res.put("rm", exptionCode);
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentLength(res.toString().getBytes("utf-8").length);
			writer = response.getWriter();
			writer.print(res.toString());
			writer.flush();
		} catch (IOException e) {
			LogUtils.log4Error("writeResult error,exptionCode=" + exptionCode,e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	protected void setNocache() {
		response.setHeader("Pragma", "No-cache");// 清除缓存
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	protected void writeJSONResult(Object result, String callback) {
		PrintWriter out = null;
		try {
			response.setContentType("application/x-javascript;charset=utf-8");
			out = response.getWriter();
			out.print(callback + "(" + result + ");");
			response.flushBuffer();
		} catch (Exception e) {
			LogUtils.log4Error("writeJSONResult error,result=" + result, e);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception e) {
				}
			;
		}
	}

	public String getHeader(String key) {
		return this.request.getHeader(key);
	}

	public String getCookie(String key) {
		Cookie[] cookies = this.request.getCookies();
		String value = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					value = cookie.getValue();
					break;
				}
			}
		}
		return value;
	}

	public void setCookie(String key, String value, int saveSec) {
		if (StringUtils.isBlank(value))
			return;
		Cookie nCookie = new Cookie(key, value);
		if (saveSec > 0) {
			nCookie.setMaxAge(saveSec);// 设置一个没有时间的COOKIE,关闭窗口就自动删除了.
		}
		nCookie.setDomain("raxtone.com");// 主机名是指同一个域下的不同主机，例如：www.google.com和gmail.google.com就是两个不同的主机名。默认情况下，一个主机中创建的cookie在另一个主机下是不能被访问的，但可以通过domain参数来实现对其的控制
		nCookie.setPath("/");// 默认情况下，如果在某个页面创建了一个cookie，那么该页面所在目录中的其他页面也可以访问该cookie。如果这个目录下还有子目录，则在子目录中也可以访问;如果要使cookie在整个网站下可用，可以将cookie_dir指定为根目录
		this.response.addCookie(nCookie);
	}

	protected void removeCookie(String key) {
		// ie
		Cookie[] cookies = this.request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(key)) {
					cookie.setMaxAge(0);
					cookie.setValue("");
					cookie.setDomain("raxtone.com");
					cookie.setPath("/");
					this.response.addCookie(cookie);
					break;
				}
			}
		}

	}
	
}
