package com.iori.psxc;

public class Custom {
    private Long id;

    private String name;

    private String phone;

    private String address;

    private String remark;

    private String province;

    private String city;

    private String district;

    private int count;

    private double price;

    private double totalPrice;

    private String ip="1.1.1.1";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("姓名:"+getName()+"<br/>");
        sb.append("电话:"+getPhone()+"<br/>");
        sb.append("数量:"+getCount()+"<br/>");
        sb.append("单价:"+getPrice()+"<br/>");
        sb.append("总价:"+getTotalPrice()+"<br/>");
        sb.append("地区:"+getProvince()+" "+getCity()+" "+getDistrict()+"<br/>");
        sb.append("地址:"+getAddress()+"<br/>");
        sb.append("备注:"+getRemark());
        return sb.toString();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
