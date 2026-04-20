package com.example.deliveryservice.dto;

public class OrderDto {
    private Long id;
    private String orderNumber;
    private String customerName;
    private String menuName;
    private Double totalPrice;
    private String status;

    public OrderDto() {
    }

    public OrderDto(Long id, String orderNumber, String customerName, String menuName, Double totalPrice, String status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.menuName = menuName;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMenuName() {
        return menuName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}