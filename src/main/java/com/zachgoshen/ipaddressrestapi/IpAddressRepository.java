package com.zachgoshen.ipaddressrestapi;

import org.springframework.data.repository.CrudRepository;

public interface IpAddressRepository extends CrudRepository<IpAddress, Long> {

    IpAddress findByAddress(String address);

}
