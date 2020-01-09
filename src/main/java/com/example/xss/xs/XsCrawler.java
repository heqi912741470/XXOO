package com.example.xss.xs;

import com.example.xss.entity.Catalog;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XsCrawler {

    public List<Catalog> doGet() {
        List<Catalog> catalogList = new ArrayList<>();
        //打开浏览器
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //申明get请求
        HttpGet httpGet = new HttpGet("http://www.zongheng.com/rank.html");
        //设置代理
        HttpHost proxy = new HttpHost("58.218.92.76", 3509);
        //设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设置从connect Manager获取Connection超时时间、
        RequestConfig requestConfig = RequestConfig.custom().setProxy(proxy).setConnectTimeout(10000).setSocketTimeout(10000).setConnectionRequestTimeout(3000).build();
        //设置请求头
        httpGet.setHeader("Host", "www.zongheng.com");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpGet.setHeader("Referer", "http://www.zongheng.com/");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        httpGet.setHeader("Cookie", "ZHID=3619A1ABB26C80C2BA7BFA2204F3CB56; v_user=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DTOBoIHWT_k68h5z8k_PmqqYoJT9h-ijtkqAjkbsGJq1tapQqHokiUFyvhDNndauW%26wd%3D%26eqid%3Dadf7e43000009c14000000045db00923%7Chttp%3A%2F%2Fwww.zongheng.com%2F%7C55415804; ver=2018; zh_visitTime=1578033476973; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%2216df7a3e55e7d6-056b5180c32756-b363e65-2073600-16df7a3e55f8a5%22%2C%22%24device_id%22%3A%2216df7a3e55e7d6-056b5180c32756-b363e65-2073600-16df7a3e55f8a5%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_referrer%22%3A%22%22%2C%22%24latest_referrer_host%22%3A%22%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%7D%7D; PassportCaptchaId=08eee32dc656491e8ec61c23990d22b0; platform=H5; zhffr=0; Hm_lvt_c202865d524849216eea846069349eb9=1578033477,1578305502,1578384308,1578540127; zh_rba=true; AST=1578556506140a000c5b00b; Hm_up_c202865d524849216eea846069349eb9=%7B%22uid_%22%3A%7B%22value%22%3A%2251885807%22%2C%22scope%22%3A1%7D%7D; logon=NTE4ODU4MDc%3D%7CMA%3D%3D%7C%7CeHhvb2JpYmk%3D%7CdHJ1ZQ%3D%3D%7CMTAyNTU1MzE5Ng%3D%3D%7C65AD3F4A7AF9326B7F708CB826E7DC65; __logon__=NTE4ODU4MDc%3D%7CMA%3D%3D%7C%7CeHhvb2JpYmk%3D%7CdHJ1ZQ%3D%3D%7CMTAyNTU1MzE5Ng%3D%3D%7C65AD3F4A7AF9326B7F708CB826E7DC65; Hm_lpvt_c202865d524849216eea846069349eb9=1578551769; visit_list=51885807");
        //发送请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断状态码
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            //使用工具类 EntityUtils 从响应中取出尸体表示的内容并转换成字符串
            String str = null;
            try {
                str = EntityUtils.toString(entity, "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(str);
            //使用jsoup解析，将字符内容解析为一个Document类
            Document doc = Jsoup.parse(str);
            //根据标签拿到的标签，得到数据
            Elements elements = doc.select("div[class=rank_main]").select("div[class=rank_i_bname fl]");


            for (Element element : elements) {
                String NewStr = element.text();
                String url = element.select("a").attr("href").replace("book", "showchapter");
                Catalog catalog = new Catalog();
                catalog.setXsName(NewStr);
                catalog.setXsUrl(url);
                catalogList.add(catalog);
            }


        }
        //5.关闭资源
        try {
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return catalogList;

    }
}
