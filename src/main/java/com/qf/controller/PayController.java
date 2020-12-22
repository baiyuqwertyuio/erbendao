package com.qf.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.qf.dao.OrderMapper;
import com.qf.dao.UserRepository;
import com.qf.pojo.Order;
import com.qf.pojo.User;
import com.qf.service.OrderService;
import com.qf.utils.RedisUtils;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by 54110 on 2020/12/11.
 */
@RestController
public class PayController {

    @Autowired
    OrderService orderService;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserRepository userRepository;



    @RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request , HttpResponse httpResp) throws AlipayApiException {
            Map<String, String> stringStringMap = convertRequestParamsToMap(request);

        boolean signVerified = AlipaySignature.rsaCheckV1(stringStringMap, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkWkdD8kwpguxrZkbuEgjXmEhbaTK2CDYKX9ITjgfUh+vLbFgjHmLJFvgUBbWFjjd7QRYT4YRNDpDvDja5sEXtFk3b6H14bPLAE+wOMisVYYW5/jd3NbW7EGqCPQiI6BTB8JgJUZSCHUyzsdEcx3rUNFE16wuUfvS3mqtuqccfpPJobosMOjlxC9ul3U7j0hxsCzaJ/w4E/Lbv0qq5EVeW1gqcIdsBcYEYfi2NlvIItQGOTq2zIe6yMo2zQf01UxZ8HBcbqbyMrlTJ3wAuY3aH58orYvHtqVA6qEsUH2iwzNsSih7leH0HfVyBfigTT7Yr6vihRRrjFcRp5SHrGfDEwIDAQAB", "utf-8", "RSA2"); //调用SDK验证签名
        if(signVerified){
            // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure
            System.out.println(stringStringMap);
            if (stringStringMap.get("trade_status").equals("TRADE_SUCCESS")){
                String out_trade_no = stringStringMap.get("out_trade_no");
                Order order = orderService.findOrder(out_trade_no);
                Integer uid = order.getUid();
                User user = userRepository.findById(uid).get();
                user.setMembers(1);
                userRepository.saveAndFlush(user);
                String key = user.getToken();
                redisUtils.set(key,user);
            }

        }else{
            // TODO 验签失败则记录异常日志，并在response中返回failure.
        }
    }

    // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<String, String>();

        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;

            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(",").append(val);
                }
                retMap.put(name, sb.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }

        return retMap;
    }
}