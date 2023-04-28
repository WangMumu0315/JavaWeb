package com.xll.model;

import com.xll.util.FormatUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author 谢琳琳
 * @version 1.0
 */
public class Order {

    private int id;
    private String orderId;
    private User customer;
    private List<BookItem> bookItemList;
    private BigDecimal money;
    private OrderStatus orderStatus;
    private long createTime;
    private long updateTime;
    private String receiverName;
    private String receiverTel;
    private String receiverAddress;
    private String expressNumber;

    private String customerId; //用于模糊查询
    private String status; //用于模糊查询

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<BookItem> getBookItemList() {
        return bookItemList;
    }

    public String getBooks() {
        if (bookItemList == null) {
            return null;
        }
        String books = "";
        for (BookItem bookItem : bookItemList) {
            books += bookItem.toString() + ",";
        }
        return books;
    }

    public void setBookItemList(List<BookItem> bookItemList) {
        this.bookItemList = bookItemList;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public String getMoneyString() {
        return FormatUtil.formatMoney(money);
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderStatus(String status) {
        if (status == null || status == "") {
            setOrderStatus(OrderStatus.UNKNOWN);
        }
        switch (status) {
            case "未付款":
                setOrderStatus(OrderStatus.UNPAID);
                break;
            case "已付款":
                setOrderStatus(OrderStatus.PAID);
                break;
            case "已发货":
                setOrderStatus(OrderStatus.SHIPPED);
                break;
            case "已完成":
                setOrderStatus(OrderStatus.FINISHED);
                break;
            case "已取消":
                setOrderStatus(OrderStatus.CANCEL);
                break;
            default:
                setOrderStatus(OrderStatus.UNKNOWN);
        }
    }

    public long getCreateTime() {
        return createTime;
    }

    public String getCreateTimeString() {
        return createTime != 0 ? FormatUtil.formatDateTime(createTime) : "";
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public String getUpdateTimeString() {
        return updateTime != 0 ? FormatUtil.formatDateTime(updateTime) : "";
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverTel() {
        return receiverTel;
    }

    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = String.valueOf(customerId);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", customer=" + customer +
                ", books=" + Arrays.toString(bookItemList.toArray()) +
                ", money=" + getMoneyString() +
                ", orderStatus=" + orderStatus.getName() +
                ", createTime=" + getCreateTimeString() +
                ", updateTime=" + getUpdateTimeString() +
                ", receiverName=" + receiverName +
                ", receiverTel=" + receiverTel +
                ", receiverAddress=" + receiverAddress +
                '}';
    }
}

