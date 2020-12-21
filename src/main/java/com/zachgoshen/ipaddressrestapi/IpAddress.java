package com.zachgoshen.ipaddressrestapi;

import javax.persistence.*;

@Entity
public class IpAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String address;
    private String status;

    protected IpAddress() {}

    public IpAddress(String address) {
        this.address = address;
        this.status = "available";
    }

    public long getId() {
        return this.id;
    }

    public String getAddress() {
        return this.address;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
