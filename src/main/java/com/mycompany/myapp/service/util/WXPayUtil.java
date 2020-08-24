//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mycompany.myapp.service.util;

import com.mycompany.myapp.service.util.WXPayConstants.SignType;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WXPayUtil {
    public static final Log logger = LogFactory.getLog(WXPayUtil.class);

    public WXPayUtil() {
    }

    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();

            for(int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == 1) {
                    Element element = (Element)node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }

            try {
                stream.close();
            } catch (Exception var10) {
                logger.info("流关闭错误", var10);
            }

            return data;
        } catch (Exception var11) {
            getLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", var11.getMessage(), strXML);
            throw var11;
        }
    }

    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        Element root = document.createElement("xml");
        document.appendChild(root);
        Iterator var5 = data.keySet().iterator();

        while(var5.hasNext()) {
            String key = (String)var5.next();
            String value = (String)data.get(key);
            if (value == null) {
                value = "";
            }

            value = value.trim();
            Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }

        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        Transformer transformer = factory.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty("encoding", "UTF-8");
        transformer.setOutputProperty("indent", "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString();

        try {
            writer.close();
        } catch (Exception var12) {
            logger.info("流关闭错误", var12);
        }

        return output;
    }

    public static String generateSignedXml(Map<String, String> data, String key) throws Exception {
        return generateSignedXml(data, key, SignType.MD5);
    }

    public static String generateSignedXml(Map<String, String> data, String key, SignType signType) throws Exception {
        String sign = generateSignature(data, key, signType);
        data.put("sign", sign);
        return mapToXml(data);
    }

    public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
        Map<String, String> data = xmlToMap(xmlStr);
        if (!data.containsKey("sign")) {
            return false;
        } else {
            String sign = (String)data.get("sign");
            return generateSignature(data, key).equals(sign);
        }
    }

    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        return isSignatureValid(data, key, SignType.MD5);
    }

    public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey("sign")) {
            return false;
        } else {
            String sign = (String)data.get("sign");
            return generateSignature(data, key, signType).equals(sign);
        }
    }

    public static String generateSignature(Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    public static String generateSignature(Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = (String[])keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        String[] var6 = keyArray;
        int var7 = keyArray.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            String k = var6[var8];
            if (!k.equals("sign") && ((String)data.get(k)).trim().length() > 0) {
                sb.append(k).append("=").append(((String)data.get(k)).trim()).append("&");
            }
        }

        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        } else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        } else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }

    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        byte[] var4 = array;
        int var5 = array.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            byte item = var4[var6];
            sb.append(Integer.toHexString(item & 255 | 256).substring(1, 3));
        }

        return sb.toString().toUpperCase();
    }

    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        byte[] var6 = array;
        int var7 = array.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            byte item = var6[var8];
            sb.append(Integer.toHexString(item & 255 | 256).substring(1, 3));
        }

        return sb.toString().toUpperCase();
    }

    public static Logger getLogger() {
        Logger logger = LoggerFactory.getLogger("wxpay java sdk");
        return logger;
    }

    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    public static String postXml(String url, String xml) {
        StringBuilder sb = new StringBuilder();
        HttpPost httpPost = new HttpPost(url);
        HttpEntity entity = null;
        HttpClient client = HttpClientBuilder.create().build();
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        try {
            StringEntity payload = new StringEntity(xml, "UTF-8");
            httpPost.setEntity(payload);
            HttpResponse response = client.execute(httpPost);
            entity = response.getEntity();
            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));

                String text;
                while((text = bufferedReader.readLine()) != null) {
                    sb.append(text);
                }
            }
        } catch (Exception var18) {
            logger.error("与[" + url + "]通信过程中发生异常", var18.getCause());
        } finally {
            client.getConnectionManager().shutdown();

            try {
                EntityUtils.consume(entity);
            } catch (IOException var17) {
                logger.error("net io exception");
            }

        }

        return sb.toString();
    }

    public static Map<String, Object> parseXml(InputStream inputStream) throws Exception {
        if (inputStream == null) {
            return null;
        } else {
            Map<String, Object> map = new HashMap();
            SAXReader reader = new SAXReader();
            org.dom4j.Document document = reader.read(inputStream);
            org.dom4j.Element root = document.getRootElement();
            List<org.dom4j.Element> elementList = root.elements();
            Iterator var6 = elementList.iterator();

            while(var6.hasNext()) {
                org.dom4j.Element e = (org.dom4j.Element)var6.next();
                map.put(e.getName(), e.getText());
            }

            inputStream.close();
            inputStream = null;
            return map;
        }
    }

    public static String map2Xmlstring(Map<String, String> map) {
        StringBuffer sb = new StringBuffer("");
        sb.append("<xml>");
        Set<String> set = map.keySet();
        Iterator it = set.iterator();

        while(it.hasNext()) {
            String key = (String)it.next();
            Object value = map.get(key);
            sb.append("<").append(key).append(">");
            sb.append(value);
            sb.append("</").append(key).append(">");
        }

        sb.append("</xml>");
        return sb.toString();
    }

    public static String postXmlSSL(String url, String xml, String mchId) {
        String jsonStr = null;

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            ClassPathResource classPathResource = new ClassPathResource("cert/apiclient_cert.p12");
            InputStream certStream = classPathResource.getInputStream();

            try {
                keyStore.load(certStream, mchId.toCharArray());
            } finally {
                certStream.close();
            }

            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            try {
                HttpPost httpost = new HttpPost(url);
                httpost.addHeader("Connection", "keep-alive");
                httpost.addHeader("Accept", "*/*");
                httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpost.addHeader("Host", "api.mch.weixin.qq.com");
                httpost.addHeader("X-Requested-With", "XMLHttpRequest");
                httpost.addHeader("Cache-Control", "max-age=0");
                httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
                httpost.setEntity(new StringEntity(xml, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpost);

                try {
                    HttpEntity entity = response.getEntity();
                    jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
                    EntityUtils.consume(entity);
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (Exception var30) {
            logger.error("与[" + url + "]通信过程中发生异常", var30.getCause());
        }

        return jsonStr;
    }

    public static byte[] postXmlIns(String url, String xml) {
        HttpPost httpPost = new HttpPost(url);
        HttpEntity entity = null;
        HttpClient client = HttpClientBuilder.create().build();
        InputStream ins = null;
        byte[] codeByte = null;

        try {
            StringEntity payload = new StringEntity(xml, "UTF-8");
            httpPost.setEntity(payload);
            HttpResponse response = client.execute(httpPost);
            entity = response.getEntity();
            ins = entity.getContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            boolean var11 = false;

            int temp;
            while((temp = ins.read(buff, 0, 1024)) > 0) {
                baos.write(buff, 0, temp);
            }

            codeByte = baos.toByteArray();
        } catch (Exception var24) {
            logger.error("与[" + url + "]通信过程中发生异常", var24.getCause());
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException var23) {
                    logger.error(var23.getMessage());
                }
            }

            try {
                EntityUtils.consume(entity);
            } catch (IOException var22) {
                logger.error("net io exception");
            }

        }

        return codeByte;
    }

    public static byte[] httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        HttpEntity entity = null;
        HttpClient client = HttpClientBuilder.create().build();
        InputStream ins = null;
        byte[] codeByte = null;

        try {
            HttpResponse response = client.execute(httpGet);
            entity = response.getEntity();
            ins = entity.getContent();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            boolean var9 = false;

            int temp;
            while((temp = ins.read(buff, 0, 1024)) > 0) {
                baos.write(buff, 0, temp);
            }

            codeByte = baos.toByteArray();
        } catch (Exception var22) {
            logger.error("与[" + url + "]通信过程中发生异常", var22.getCause());
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException var21) {
                    logger.error(var21.getMessage());
                }
            }

            try {
                EntityUtils.consume(entity);
            } catch (IOException var20) {
                logger.error("net io exception");
            }

        }

        return codeByte;
    }

    public static String create_pay_nonce_str() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";

        for(int i = 0; i < 16; ++i) {
            Random rd = new Random();
            res = res + chars.charAt(rd.nextInt(chars.length() - 1));
        }

        return res;
    }

    public static String arrayToXml(Map<String, String> arr) {
        String xml = "<xml>";

        String key;
        String val;
        for(Iterator iter = arr.entrySet().iterator(); iter.hasNext(); xml = xml + "<" + key + ">" + val + "</" + key + ">") {
            Entry<String, String> entry = (Entry)iter.next();
            key = (String)entry.getKey();
            val = (String)entry.getValue();
        }

        xml = xml + "</xml>";
        return xml;
    }

    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        Object[] var6 = keys;
        int var7 = keys.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Object key = var6[var8];
            if (key != null && !((String)params.get(key)).isEmpty()) {
                if (first) {
                    first = false;
                } else {
                    temp.append("&");
                }

                temp.append(key).append("=");
                Object value = params.get(key);
                String valueString = "";
                if (null != value) {
                    valueString = value.toString();
                }

                if (encode) {
                    temp.append(URLEncoder.encode(valueString, "UTF-8"));
                } else {
                    temp.append(valueString);
                }
            }
        }

        return temp.toString();
    }

    public static String getSign(Map<String, String> params, String paternerKey) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + paternerKey;
        String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
        return signValue;
    }

    public static Map<String, Object> parseXml2Map(String xmlStr) {
        HashMap map = new HashMap();

        try {
            org.dom4j.Document doc = DocumentHelper.parseText(xmlStr);
            org.dom4j.Element root = doc.getRootElement();
            List<org.dom4j.Element> childrenList = root.elements();
            if (childrenList != null && childrenList.size() > 0) {
                Iterator var5 = childrenList.iterator();

                while(var5.hasNext()) {
                    Object children = var5.next();
                    org.dom4j.Element child = (org.dom4j.Element)children;
                    map.put(child.getName(), child.getTextTrim());
                }
            }
        } catch (DocumentException var8) {
            ;
        }

        return map;
    }
}
