package com.zachgoshen.ipaddressrestapi;

import org.apache.commons.net.util.SubnetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IpAddressService {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired IpAddressRepository repository;

    /**
     * Return all the IP Addresses stored in the repository
     *
     * @return the IP Addresses
     */
    public List<IpAddress> listAll() {
        return (List<IpAddress>) repository.findAll();
    }

    /**
     * Add all the IP Addresses within a CIDR block to the repository
     *
     * @param cidrBlock the CIDR block
     */
    public void addAllInBlock(String cidrBlock) {
        try {
            SubnetUtils subnetUtils = new SubnetUtils(cidrBlock);
            subnetUtils.setInclusiveHostCount(true);
            String[] ipAddresses = subnetUtils.getInfo().getAllAddresses();
            for (String ipAddress : ipAddresses) {
                if (repository.findByAddress(ipAddress) == null) {
                    repository.save(new IpAddress(ipAddress));
                }
            }
        } catch (IllegalArgumentException e ) {
            log.error("Invalid CIDR block");
        }
    }

    /**
     * Update the status of an IP Address to either available or acquired
     *
     * @param id the id of the IP Address
     * @param status the new status of the IP Address
     */
    public void updateStatus(String id, String status) {
        try {
            Optional<IpAddress> ipAddressFound = repository.findById(Long.parseLong(id));
            boolean statusIsValid = (status.equals("available")) || (status.equals("acquired"));
            if (ipAddressFound.isPresent() && statusIsValid) {
                IpAddress ipAddress = ipAddressFound.get();
                ipAddress.setStatus(status);
                repository.save(ipAddress);
            }
        } catch (NumberFormatException e) {
            log.error("Invalid ID");
        }
    }

}
