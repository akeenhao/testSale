package com.mycompany.myapp.web.rest;

import com.alibaba.fastjson.JSONArray;
import com.mycompany.myapp.domain.BusiValue;
import com.mycompany.myapp.repository.BusiValueRepository;
import com.mycompany.myapp.service.BusiValueService;
import com.mycompany.myapp.service.dto.BusiInfo;
import com.mycompany.myapp.service.dto.SingleBusi;
import com.mycompany.myapp.service.dto.Stock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.*;

@RestController
public class BaseApi {

    @Autowired
    private BusiValueService busiValueService;
    @Autowired
    private BusiValueRepository busiValueRepository;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/scyRule")
    public boolean scyRule(String code) {

        List<Float> busiValues =busiValueRepository.getBusiValueByCOrderByDay(code);

        return this.dealRule(busiValues);
    }


    @GetMapping("/dealRule")
    public boolean dealRule(List<Float> req) {
        logger.info("getValues");

        int size = req.size();

        List<Float> threeV = new ArrayList<>();
        List<Float> fiveV = new ArrayList<>();
        List<Float> tenV = new ArrayList<>();
        List<Float> tenfV = new ArrayList<>();

        int i = 1;
        Float vThree = 0f;
        Float vFive = 0f;
        Float vTen = 0f;
        Float vTenF = 0f;
        for (Float v : req) {
            vThree += v;
            if (i % 3 == 0) {
                threeV.add(vThree / 3);
                vThree = 0f;
            }
            vFive += v;
            if (i % 5 == 0) {
                fiveV.add(vFive / 5);
                vFive = 0f;
            }
            vTen += v;
            if (i % 10 == 0) {
                tenV.add(vTen / 10);
                vTen = 0f;
            }
            vTenF += v;
            if (i % 15 == 0) {
                tenfV.add(vTenF / 10);
                vTenF = 0f;
            }
            i++;
        }

        int lteNum = 0;
        int vSize = threeV.size();
        Float last = 0f;
        for (Float tv : threeV) {
            if (last > tv) lteNum++;
            last = tv;
        }
        DecimalFormat df = new DecimalFormat("0.00000");
        String scale = df.format((float) lteNum / vSize);
        logger.info("3pline avgSize:{} lteNum:{} scale:{}", vSize, lteNum, scale);

        lteNum = 0;
        vSize = fiveV.size();
        last = 0f;
        for (Float tv : fiveV) {
            if (last > tv) lteNum++;
            last = tv;
        }
        scale = df.format((float) lteNum / vSize);
        logger.info("5pline avgSize:{} lteNum:{} scale:{}", vSize, lteNum, scale);

        lteNum = 0;
        vSize = tenV.size();
        last = 0f;
        for (Float tv : tenV) {
            if (last > tv) lteNum++;
            last = tv;
        }
        scale = df.format((float) lteNum / vSize);
        logger.info("10pline avgSize:{} lteNum:{} scale:{}", vSize, lteNum, scale);

        lteNum = 0;
        vSize = tenfV.size();
        last = 0f;
        for (Float tv : tenfV) {
            if (last > tv) lteNum++;
            last = tv;
        }
        scale = df.format((float) lteNum / vSize);
        logger.info("15pline avgSize:{} lteNum:{} scale:{}", vSize, lteNum, scale);


        return false;
    }

    @GetMapping("/getFValues")
    public List<Float> getFValues(int size, Float max) {
        logger.info("getValues");
        List<Float> res = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            res.add(random.nextFloat() * max);
        }
        logger.info("res:{}", res);
        return res;
    }

    @GetMapping("/getValues")
    public List<Integer> getValues() {
        logger.info("getValues");
        List<Integer> res = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            res.add(random.nextInt(100));
        }
        logger.info("res:{}", res);
        return res;
    }


    @GetMapping("/getBusiInfos")
    public List<Stock> getBusiInfos(int pageNum) {
        int max = pageNum * 30;
        int limit = (pageNum - 1) * 30;
        if (limit < 0) limit = 0;
        return getBusiCodes(max, limit);
    }

    @GetMapping("/getBusiValue")
    public List<BusiInfo> getBusiValue(int pageSize, int pageNum, String startDate, String endDate, Float minValue, String queryCode) {
        List<BusiInfo> res = new ArrayList<>();
        String path = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=8188&from_mid=1&query=%E6%AF%94%E4%BA%9A%E8%BF%AA%20%E8%82%A1%E7%A5%A8&eprop=month&sitesign=5934590081bade4355da79a4c2a4b839&cb=jQuery110204333049919096178_1590385217396&_=1590385217401";
//        688016  200+
//        002549 50*

        if (StringUtils.isEmpty(startDate))
            startDate = "20200301";
        if (StringUtils.isEmpty(endDate))
            endDate = "20200601";
        int max = pageSize * pageNum;
        int limit = (pageNum - 1) * pageSize;
        if (limit < 0) limit = 0;
        List<String> codes = new ArrayList<>();
//        codes.add("688016");//200+
//        codes.add("002594");//50*
//        codes.add("300228");//4.28
//        codes.add("002752"); //5.26
//        codes.add("000796"); //10
//        codes.add("600519"); //1100

        if (StringUtils.isEmpty(queryCode)) {
            List<Stock> stocks = getBusiCodes(max, limit);
            int index = 0;
            for (Stock stock : stocks) {
                if (stock.getCode().startsWith("3")) continue;
                codes.add(stock.getCode());
                if (index++ > max) break;
//                }
            }
        } else {
            codes.add(queryCode);
        }

        for (String code : codes) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            path = "http://q.stock.sohu.com/hisHq?code=cn_" + code + "&start=" + startDate + "&end=" + endDate + "&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp";
            path = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sh" + code + "&scale=60&ma=no&datalen=1023";
            logger.info("index:{} net path:{}", codes.indexOf(code), path);

            String str = sendGet(path, null, "GBK");
            logger.info("net res:{}", str);
            if (org.apache.commons.lang.StringUtils.isBlank(str) || "null".equals(str)) {
                path = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sz" + code + "&scale=60&ma=no&datalen=1023";
                logger.info("index:{} net path:{}", codes.indexOf(code), path);

                str = sendGet(path, null, "GBK");
                logger.info("net res:{}", str);
                if (org.apache.commons.lang.StringUtils.isBlank(str) || "null".equals(str))
                    continue;
            }

            List<SingleBusi> singleBusiList = (List<SingleBusi>) JSONArray.parseArray(str, SingleBusi.class);
            if (singleBusiList.isEmpty()) continue;

            List<String> xData = new ArrayList<>();
            List<Float> yHighData = new ArrayList<>();
            List<Float> yLowData = new ArrayList<>();
            List<Float> yOpenData = new ArrayList<>();
            List<Float> yCloseData = new ArrayList<>();
            Float minYData = null;
            Float maxYData = null;
            TreeMap<String, Float> dayMax = new TreeMap<>();
            for (SingleBusi singleBusi : singleBusiList) {
                String curDay = singleBusi.getDay().substring(0, 10);
                xData.add(singleBusi.getDay());
                Float singleYHighData = singleBusi.getHigh();
                yHighData.add(singleYHighData);
                Float singleYLowData = singleBusi.getLow();
                yLowData.add(singleYLowData);
                Float singleYOpenData = singleBusi.getOpen();
                yOpenData.add(singleYOpenData);
                Float singleYCloseData = singleBusi.getClose();
                yCloseData.add(singleYCloseData);

                if (!dayMax.containsKey(curDay)) {
                    dayMax.put(curDay, singleYHighData);
                }

                if (dayMax.containsKey(curDay)) {
                    if (singleYHighData.compareTo(dayMax.get(curDay)) > 0) dayMax.put(curDay, singleYHighData);
                }
            }
//            if (minYData > minValue) continue;

            BusiInfo busiInfo = new BusiInfo();
            busiInfo.setxData(xData);
            busiInfo.setyHighData(yHighData);
            busiInfo.setyLowData(yLowData);
            busiInfo.setyOpenData(yOpenData);
            busiInfo.setyCloseData(yCloseData);
            busiInfo.setyMaxData(maxYData);
            busiInfo.setyMinData(minYData);
            busiInfo.setCode(code);
            res.add(busiInfo);

            for (Map.Entry<String, Float> singleDaya : dayMax.entrySet()) {
                BusiValue busiValue = new BusiValue();
                busiValue.setC(code);
                busiValue.setDay(singleDaya.getKey());
                busiValue.setV(singleDaya.getValue());
                busiValueService.save(busiValue);
            }
        }

        logger.info("res:{}", res);

        return res;
    }


    public String getStringFromStream(InputStream is) {
        String str = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int len = 0;
        byte[] data = new byte[1024];
        if (is != null) {
            try {
                while ((len = is.read(data)) != -1) {
                    bos.write(data, 0, len);
                }
                str = new String(bos.toByteArray(), "GBK");
                logger.info("lineData:{}", str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str;
    }


    public InputStream useGetMethod(String path, Map<String, String> map,
                                    String encode) {
        InputStream is = null;
        StringBuffer sb = new StringBuffer(path);
/*            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue())
                        .append("&");
            }
            sb.deleteCharAt(sb.length() - 1);*/
        System.out.println(sb.toString());
        URL url = null;

        OutputStream os = null;
        try {
            url = new URL(sb.toString());
            if (url != null) {
                HttpURLConnection con = (HttpURLConnection) url
                    .openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(3000);
                con.setDoInput(true);
                con.setDoOutput(true);
                os = con.getOutputStream();
                os.write(sb.toString().getBytes(encode));
                os.close();
                is = con.getInputStream();
            }
        } catch (Exception e) {
        }
        return is;
    }


    public String sendGet(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sbParams.append(entry.getKey());
                sbParams.append("=");
                sbParams.append(entry.getValue());
                sbParams.append("&");
            }
        }
        HttpURLConnection con = null;
        BufferedReader br = null;
        try {
            URL url = null;
            if (sbParams != null && sbParams.length() > 0) {
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));
            } else {
                url = new URL(urlParam);
            }
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.connect();
            resultBuffer = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                } finally {
                    if (con != null) {
                        con.disconnect();
                        con = null;
                    }
                }
            }
        }
        return resultBuffer.toString();
    }


    private static final String SRC_URL = "http://app.finance.ifeng.com/list/stock.php?t=hs";
    private static final String ENCODING = "utf-8";

    // Used to save stock code names

    public List<Stock> getBusiCodes(int max, int limit) {
        List<Stock> stockList = new ArrayList<Stock>();
        String url = SRC_URL;

        int idx = 0;
        while (true) {
            System.out.println(url);

            String html = getUrlHtml(url, ENCODING);
            Document doc = Jsoup.parse(html, ENCODING);

            // Find core node
            Element divtab01 = doc.getElementsByClass("tab01").last();
            if (null == divtab01) continue;

            // Find stocks
            Elements trs = divtab01.getElementsByTag("tr");
            for (Element tr : trs) {
                Elements tds = tr.getElementsByTag("td");
                if (tds.size() > 2) {
                    Element codeElm = tds.get(0).getElementsByTag("a").last();
                    Element nameElm = tds.get(1).getElementsByTag("a").last();

                    Stock s = new Stock(idx++, codeElm.text(), nameElm.text());
                    if (idx <= limit) {
                        continue;
                    }
                    stockList.add(s);
                    if (idx >= max) break;
                }
            }

            // Find next page url
            Element lastLink = divtab01.getElementsByTag("a").last();
            if (lastLink.text().equals("下一页")) {
                url = "http://app.finance.ifeng.com/list/stock.php" + lastLink.attr("href");
            } else {
                break;
            }
            if (idx >= max) break;
        }
        return stockList;

    }

    private String getUrlHtml(String url, String encoding) {
        StringBuffer sb = new StringBuffer();
        URL urlObj = null;
        URLConnection openConnection = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            urlObj = new URL(url);
            openConnection = urlObj.openConnection();
            isr = new InputStreamReader(openConnection.getInputStream(), encoding);
            br = new BufferedReader(isr);
            String temp = null;
            while ((temp = br.readLine()) != null) {
                sb.append(temp + "\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
