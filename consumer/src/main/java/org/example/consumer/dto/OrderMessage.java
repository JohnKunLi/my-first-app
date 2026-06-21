package org.example.consumer.dto;

import java.io.Serializable;

public class OrderMessage implements Serializable {

    private String orderId;
    private String userId;
    private String productName;
    private Integer quantity;
    private Double price;
    private Long timestamp;

    public OrderMessage() {}

    public OrderMessage(String orderId, String userId, String productName, Integer quantity, Double price) {
        this.orderId = orderId;
        this.userId = userId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = System.currentTimeMillis();
    }

    // Getter 和 Setter 方法
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Long getTimestamp() { return timestamp; }
    public void setTimestamp(Long timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", timestamp=" + timestamp +
                '}';
    }
}