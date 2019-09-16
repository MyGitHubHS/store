package domain;

import java.util.List;

public class OrderList {
    private String oid;

    //以订单为单位进行查询
    private List<Orderitem> orderitemList;


    public List<Orderitem> getOrderitemList() {
        return orderitemList;
    }

    public void setOrderitemList(List<Orderitem> orderitemList) {
        this.orderitemList = orderitemList;
    }


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

}
