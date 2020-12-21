package com.zachgoshen.ipaddressrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ip-addresses")
public class IpAddressController {

    @Autowired
    private IpAddressService ipAddressService;

    @GetMapping("")
    public List<IpAddress> list() {
        return ipAddressService.listAll();
    }

    @PostMapping("")
    public void addAllInBlock(@RequestParam(value = "cidr-block") String cidrBlock) {
        ipAddressService.addAllInBlock(cidrBlock);
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable String id, @RequestParam(value = "status") String status) {
        ipAddressService.updateStatus(id, status);
    }

}
