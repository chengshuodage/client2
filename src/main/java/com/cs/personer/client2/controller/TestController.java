package com.cs.personer.client2.controller;

import com.cs.personer.client2.feign.Client1FeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping(value = "/test", produces = "application/json;charset=UTF-8")
public class TestController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private Client1FeignClient client1FeignClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String testLogin() {
        return "test1:" + port;
    }

    @GetMapping("/getFeign")
    public String getFeign(@RequestParam String name) {
        String feign = "";
        try {
            feign = client1FeignClient.getFeign(name);
        } catch (Exception e) {
            log.error("调用feign出错:" + e.getMessage());
        }
        return "feign获取:" + feign;
    }

    @GetMapping("/getHystrix")
    public String getHystrix(@RequestParam String name) {
        String feign = "";
        try {
            feign = client1FeignClient.getHystrix(name);
        } catch (Exception e) {
            log.error("调用feign出错:" + e.getMessage());
        }
        return "feign获取:" + feign;
    }

    @GetMapping("/getRestTemplate")
    public String getRestTemplate(@RequestParam String name) {
        String url1 = "http://localhost:8082/test/getFeign?name=" + name;
        String url = "http://client1/test/getFeign?name=" + name;
        String feign = restTemplate.getForObject(url, String.class);
        System.out.println("端口为:" + feign);
        return "feign获取:" + feign;
    }

}
