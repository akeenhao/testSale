package com.mycompany.myapp.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 微信支付及回调接口
 *
 * @author xby
 * @ClassName: PaymentTestService
 * @date 2018年7月12日 下午2:27:10
 */
@Service
public class PaymentService {
    public static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    /**
     * 方法描述：  微信支付
     *
     * @param request
     * @param orderNumber
     * @param openid
     * @return
     * @throws Exception
     * @Title: getPayConfig
     * @date 2018年7月12日 下午2:24:09
     * @author xby
     */
    public Map<String, Object> getPayConfig(String remoteAddr, String orderNumber, String openid) throws UnsupportedEncodingException {

        String appid = "wx145b7612e19e2bb8";
        String mchid = "1230000109";//商户id
        String mchKey = "123";//密钥串
        String payUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        //添加支付请求参数
        String nonce_str = WXPayUtil.create_pay_nonce_str();
        BigDecimal total_fee = new BigDecimal("100");
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("appid", appid);
        paraMap.put("body", "订单");
        paraMap.put("mch_id", mchid);
        paraMap.put("nonce_str", nonce_str);
        paraMap.put("openid", openid);
        paraMap.put("out_trade_no", orderNumber);
        paraMap.put("spbill_create_ip", remoteAddr);
        paraMap.put("total_fee", String.valueOf(total_fee.intValue()));
        paraMap.put("fee_type", "1");
        paraMap.put("trade_type", "JSAPI");
	    /*if("3".equals(xgOrder.getOrderType())){
	    	paraMap.put("notify_url",sysStore.getPayReturnUrl());
	    }else{
	    	paraMap.put("notify_url",xgStore.getPayReturnUrl());
	    }*/
        String sign = WXPayUtil.getSign(paraMap, mchKey);
        paraMap.put("sign", sign);
        String xml = WXPayUtil.arrayToXml(paraMap);

        //请求微信支付接口
        String xmlStr = WXPayUtil.postXml(payUrl, xml);
        logger.info("prepay payUrl:{} req:{} res:{}",payUrl, xml, xmlStr);

        //返回消息转为map
        Map<String, Object> orderMap = WXPayUtil.parseXml2Map(xmlStr);
        Map<String, Object> resMap = new HashMap<>();
        if (orderMap.get("prepay_id") == null) {
            resMap.put("err", "支付失败,请重新提交订单");
            return resMap;
        }
        String prepay_id = orderMap.get("prepay_id").toString();
        String timeStamp = create_timestamp();
        String noce_str_new = create_nonce_str();
        //添加参数 生成sign
        Map<String, String> payMap = new HashMap<>();
        payMap.put("appId", appid);
        payMap.put("timeStamp", timeStamp);
        payMap.put("nonceStr", noce_str_new);
        payMap.put("signType", "MD5");
        payMap.put("package", "prepay_id=" + prepay_id);
        String paySign = WXPayUtil.getSign(payMap, mchKey);
        //添加返回前台参数
        resMap.put("nonce_str", noce_str_new);
        resMap.put("sign", paySign);
        resMap.put("prepay_id", "prepay_id=" + prepay_id);
        resMap.put("time_stamp", timeStamp);
        resMap.put("appid", appid);
        resMap.put("reflash", 0);

        return resMap;
    }

    /**
     * 方法描述：   微信回调
     *
     * @param request
     * @return
     * @Title: wxPayNotify
     * @date 2018年6月30日 下午2:41:07
     * @author xby
     */
    @Transactional
    public String wxPayNotify(HttpServletRequest request) {
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream == null) {
                logger.error("微信支付回调,接受参数为空");
                return "接受参数为空";
            }
            //根据返回inputStream 转化为map
            Map<String, Object> map = WXPayUtil.parseXml(inputStream);
            logger.error("回调返回参数,++++++++++ " + map.toString());
            //判断交易状态状态  如果是成功状态 则做业务操作
            if ("SUCCESS".equals(map.get("return_code")) && "SUCCESS".equals(map.get("result_code"))) {
                String orderNumber = "";
                //判断返回订单号是否为空  为空返回错误信息
                if (null != map.get("out_trade_no") && !"".equals(map.get("out_trade_no"))) {
                    orderNumber = map.get("out_trade_no").toString();

                } else {
                    logger.error("微信支付回调,接受参数订单编号为空");
                    return "接受参数订单编号为空";
                }

            }
        } catch (Exception e) {
            logger.error("微信支付回调异常：" + e.getMessage());
        }
        return "<xml>\n" +
            "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
            "  <return_msg><![CDATA[OK]]></return_msg>\n" +
            "</xml>";
    }

    /**
     * 方法描述：  生成uuid
     *
     * @return
     * @Title: create_nonce_str
     * @date 2018年7月12日 下午2:24:59
     * @author xby
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    /**
     * 方法描述：   获取当前时间到秒
     *
     * @return
     * @Title: create_timestamp
     * @date 2018年7月12日 下午2:25:28
     * @author xby
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }


}
