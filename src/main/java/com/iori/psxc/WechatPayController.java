package com.iori.psxc; /**
 * Created by He on 2016/5/18.
 */

import com.alibaba.fastjson.JSONObject;
import com.iori.psxc.service.ICustomService;
import constant.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.WechatPayService;
import utils.QRCodeUtil;
import utils.ResponseHandler;
import utils.TenpayUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
@RequestMapping("/api/weixin")
@ConfigurationProperties("product")
public class WechatPayController {

	private String price;

	@Autowired
	private ICustomService customService;

	@RequestMapping("/pay")
	public String getCodeUrl(HttpServletResponse response, HttpServletRequest request,Custom custom)
			throws Exception {

		String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = TenpayUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;


		String body = "菩萨心肠DeSlim益生菌膳食纤维*"+custom.getCount();
		String out_trade_no = randomTradeNo();
		String order_price =  new BigDecimal(price).multiply(new BigDecimal(custom.getCount())).intValue()+"";
		//String spbill_create_ip = request.getRemoteAddr();
		String spbill_create_ip = custom.getIp();
		String notify_url = GlobalConfig.URL + "api/weixin/result";

		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", GlobalConfig.APPID);
		packageParams.put("mch_id", GlobalConfig.MCH_ID);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("total_fee", order_price);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", GlobalConfig.TRADE_TYPE);

		WechatPayService wechatPayService = new WechatPayService();
		String code_url = wechatPayService.getUrlCode(packageParams);

		if (code_url.equals("")) {
			System.err.println(wechatPayService.getResponseMessage());
			return ret_error("出错");
		}

		return ret_success(code_url);
	}

	private String randomTradeNo() {
		return System.currentTimeMillis()+""+(customService.maxid()+1);
	}

	@RequestMapping("/QRcode")
	@ResponseBody
	public void getQrCode(String code_url, HttpServletResponse response) throws Exception {
		ServletOutputStream sos = response.getOutputStream();
		QRCodeUtil.encode(code_url, sos);
	}

	/**
	 * 微信回调接口
	 */
	@RequestMapping(value = "/result")
	public void wechatOrderBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ok!!!!!");
		// 创建支付应答对象
		ResponseHandler resHandler = new ResponseHandler(request, response);
		resHandler.setKey(GlobalConfig.KEY);
		// 判断签名是否正确
		if (resHandler.isTenpaySign()) {
			String resXml = "";
			if ("SUCCESS".equals(resHandler.getParameter("result_code"))) {
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
			} else {
				System.out.println("支付失败,错误信息：" + resHandler.getParameter("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			out.write(resXml.getBytes());
			out.flush();
			out.close();
		} else {
			System.out.println("通知签名验证失败");
		}
	}

	/**
	 * 微信退款接口
	 */
	@RequestMapping(value = "/refund")
	public String wechatRefund(HttpServletResponse response, HttpServletRequest request)
			throws UnrecoverableKeyException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException,
			IOException, CertificateException {

		String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		String strRandom = TenpayUtil.buildRandom(4) + "";
		String nonce_str = strTime + strRandom;

		SortedMap<String, String> parameters = new TreeMap<String, String>();
		parameters.put("appid", GlobalConfig.APPID);
		parameters.put("mch_id", GlobalConfig.MCH_ID);
		parameters.put("nonce_str", nonce_str);
		parameters.put("out_trade_no", "");
		parameters.put("out_refund_no", "" + strTime);
		parameters.put("total_fee", "");
		parameters.put("refund_fee", "");
		parameters.put("op_user_id", GlobalConfig.MCH_ID);
		WechatPayService wechatPayService = new WechatPayService();

		Map map = wechatPayService.forRefund(parameters);
		if (map != null) {
			String return_code = (String) map.get("return_code");
			String result_code = (String) map.get("result_code");
			if (return_code.equals("SUCCESS") && result_code.equals("SUCCESS")) {
				// 退款成功
				return "退款成功";
			} else {
				return  (String) map.get("err_code_des");
			}
		} else {
			return "未知的错误";
		}
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	private String ret_success(Object obj){
		JSONObject ret = new JSONObject();
		ret.put("ret_code","1");
		ret.put("ret_msg",obj);
		return ret.toJSONString();
	}

	private String ret_error(Object obj){
		JSONObject ret = new JSONObject();
		ret.put("ret_code","2");
		ret.put("ret_msg",obj);
		return ret.toJSONString();
	}
}

