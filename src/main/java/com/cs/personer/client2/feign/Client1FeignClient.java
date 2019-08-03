package com.cs.personer.client2.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
//@FeignClient(name = "client1")
@FeignClient(name = "client1", url = "http://localhost:8082")//单独使用feign
public interface Client1FeignClient {
    @RequestMapping(value = "/test/getFeign", method = RequestMethod.GET)
    String getFeign(@RequestParam String name);

    @RequestMapping(value = "/test/getHystrix", method = RequestMethod.GET)
    String getHystrix(@RequestParam String name);

}