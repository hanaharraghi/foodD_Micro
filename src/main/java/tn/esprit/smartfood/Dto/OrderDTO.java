package tn.esprit.smartfood.Dto;

public class OrderDTO {

    private Integer id;
    private Long userId;
    private Double totalAmount;

    public OrderDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
}