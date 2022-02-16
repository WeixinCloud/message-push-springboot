package com.tencent.wxcloudrun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 主页页面
     *
     * @return API response html
     */
    @GetMapping
    public String index() {
        return "index";
    }

    /**
     * 发送请求
     *
     * @param body   微信推送过来的消息体
     * @param openId wx open id
     * @return API response
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> sendWxMsg(
            @RequestHeader(name = "x-wx-source") String source,
            @RequestHeader(name = "x-wx-openid") String openId,
            @RequestBody Map<String, Object> body) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.TEXT_PLAIN);

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("touser", openId);
        reqBody.put("msgtype", "text");
        reqBody.put("text", new HashMap<String, String>() {{
            put("content", String.format("云托管接收消息推送成功，内容如下：\n %s", body));
        }});
        try {
            String wxApiURL = "http://api.weixin.qq.com/cgi-bin/message/custom/send";
            Map result = restTemplate.postForObject(wxApiURL, reqBody, Map.class);
            logger.info("发送回复结果: {} ", result);
            return ResponseEntity.ok().headers(responseHeaders).body("success");
        } catch (HttpClientErrorException e) {
            logger.error("sendMsg errorMsg={}, openId={}, cause by client error", e.getMessage(), openId);
            return ResponseEntity.ok().headers(responseHeaders).body("error");
        } catch (HttpServerErrorException e) {
            logger.error("sendMsg errorMsg={}, openId={}, cause by server error", e.getMessage(), openId);
            return ResponseEntity.ok().headers(responseHeaders).body("error");
        }
    }
}
