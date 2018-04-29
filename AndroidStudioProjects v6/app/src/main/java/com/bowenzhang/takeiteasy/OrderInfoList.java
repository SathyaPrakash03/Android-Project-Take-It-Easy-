package com.bowenzhang.takeiteasy;

import java.util.ArrayList;

public class OrderInfoList {
    private ArrayList<OrderInfo> orderInfos;

    public OrderInfoList() {
        this.orderInfos = new ArrayList<>();
    }

    public ArrayList<OrderInfo> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(ArrayList<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }


    public OrderInfo addOrderInfo() {
        OrderInfo info = new OrderInfo();
        orderInfos.add(info);
        return info;
    }

    public  void deleteOrderInfo(OrderInfo info) {
        orderInfos.remove(info);
    }
}
