package com.qf.common;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.qf.pojo.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AlipayUtils {

    public String pay (HttpServletRequest httpRequest, HttpServletResponse httpResponse, Order order) throws ServletException, IOException {
        /**
         * 1.要去支付包请求的地址
         */


        AlipayClient alipayClient =  new DefaultAlipayClient( "https://openapi.alipaydev.com/gateway.do" , "2021000116671588", "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCFZySNFMy6ivW2x+rmy5tlfPC7D2h6x/ENrRsZMMycuhRWpJuq8nzKZUvKcVCTRV45PEfufXa3gyLTHvvxsUWOiwOFZu3nzzR4dA4Rl6kj432Ry2BKmpvhJtVU0VsGhgEDG275gX/sVEuzH1SHpIqdRpKmS5+wmRsoEcWufa/rUQnZ+g5sKy6lmihEYqkJro3aJbDttz2TPgd7ggtgkKgWnMK1/i9o/duEMSJhxgUWVjD1kMHMJD4Ah3yPUeF2YD+tTZ12W6IkUdF7J9k85Jxgfbsx2GDQYQ9XhADNmUPqZOgGrM6igefKZLV/6Ez9K0/WwIRTPD8Fa+6s/7WJdRxbAgMBAAECggEAe9miSYjB+X4BWUyJfPQStUolhMVtTnBKD1RjECQKEod/UmFIVJED4jPhBXXmRJPmQmUAsx2wFfedYTwm0+CsnwXYowVIYiowoJO+a/7NfRA7CBw0rY6UoZeX/0LOq2PFlMjnYqUjypkIHqg7piLQvetDQzh6bU33tF+rk7AEnwYp6zkQvl3+bUtjs5FzvVJS+xzqJmHns2ZvWmMfil8dInQGf7flan/WdvJu/UDKvoz9gZWna774iDJUQLmvCdkA1pX0IlI4MzPZuMTtnG+yjtToY/OFJr13obBlpsjCZWoZSWZISI6svCCkLrZgG130R5UIkzAEPvRjxTu1RrQJYQKBgQDC5jsOR5KKprFBVgpwOjGw2Rh05exludFSQqJr9Bt6CGlgu246ESROKEtmurMsy6hqw4/Bn7MXRTfTYS2Pthw4nWs3A/wgz7NaROD69Nm0OBBsLJpYE8aJ55huGxxy6SiUhG0RPeV6FzZ+JZMkDxYdCZUNaZ8rS2exGtAr8EFo5wKBgQCvOXoMahNT0isOax3ovSa5+Ziy4uajLtgnx66OLvvSRnmgeCdsRSnPXv2FuxomKqLAF5FQJL2bxT7hKRoA6zt7x4ECwxAKXuKSjtvAq/1yePGPvqhx8qrJKhNt19cAxp1sa0/FiyftJCMfvkILykKSyvnzQEkDkwCLFTV3yEC+bQKBgFQAErfm1Rv5GDCGutz/1+bWypmndymo2rTPj11jymvgMDrGzY0tUi51Y3oVQaAu7pmFS/IxuGhxJsxt98sWvDcSceE2UHZ7zls7kJPY7/OirkFRP/xyGYHaVXIvMSW9pAXOxYtG2B603oGkBDToY6mENb+gJD/usfQ9EDe/xxFFAoGAQsswIQdWcr9MtmRStGNWHdJtSNOBg3bZ4uAVRBHY4GEJAAiNTpJWy7uz/IREBYR/IaZaOCipNnXL8FyQkwUYWajFHt5ogOuGhsN6NUnahQZhXqAY6cbNH32whlCenqcXXzrQ38aZ46XA1drapk/lbLt+6CD7NCeXL21YQl7Nf0kCgYASrljgobKGvoRDKRno0rOtmelnk1PUcDpwCqcnoyRmKWn2CYjwYHYeMxNjCzzPiuBPF1isrDLXxqzaO2QeiCZ7G3uAUR2nQ4bbYoK3adiAqEg+HTsL+SRpDp64tSdQibhc+qXq0yE2BOUyNn0xCrxBRCBWf2kccSC55stW9E1DhA==", "json", "utf-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkWkdD8kwpguxrZkbuEgjXmEhbaTK2CDYKX9ITjgfUh+vLbFgjHmLJFvgUBbWFjjd7QRYT4YRNDpDvDja5sEXtFk3b6H14bPLAE+wOMisVYYW5/jd3NbW7EGqCPQiI6BTB8JgJUZSCHUyzsdEcx3rUNFE16wuUfvS3mqtuqccfpPJobosMOjlxC9ul3U7j0hxsCzaJ/w4E/Lbv0qq5EVeW1gqcIdsBcYEYfi2NlvIItQGOTq2zIe6yMo2zQf01UxZ8HBcbqbyMrlTJ3wAuY3aH58orYvHtqVA6qEsUH2iwzNsSih7leH0HfVyBfigTT7Yr6vihRRrjFcRp5SHrGfDEwIDAQAB", "RSA2");  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
        alipayRequest.setNotifyUrl( "http://331t2j2729.zicp.vip/returnUrl" ); //在公共参数中设置回跳和通知地址



        //生成订单信息存储到数据库，默认的订单信息时未支付
        alipayRequest.setBizContent( "{"  +
                "    \"out_trade_no\":\""+order.getTransferId()+"\","  +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
                "    \"total_amount\":\""+order.getPrice()+"\","  +
                "    \"subject\":\""+order.getName()+"\","  +
                "    \"body\":\"爱奇艺会员\","  +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
                "    \"extend_params\":{"  +
                "    \"sys_service_provider_id\":\"2088511833207846\""  +
                "    }" +
                "  }" ); //填充业务参数
        String form= "" ;
        try  {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        }  catch  (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType( "text/html;charset=UTF-8");
        /*httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();*/
        return form;
    }
}
