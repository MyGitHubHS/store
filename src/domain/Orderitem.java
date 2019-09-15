package domain;


public class Orderitem {

  private String itemid;
  private Integer count;
  private Double subtotal;
  private String pid;
  private String oid;


  @Override
  public String toString() {
    return "Orderitem{" +
            "itemid='" + itemid + '\'' +
            ", count=" + count +
            ", subtotal=" + subtotal +
            ", pid='" + pid + '\'' +
            ", oid='" + oid + '\'' +
            '}';
  }

  public String getItemid() {
    return itemid;
  }

  public void setItemid(String itemid) {
    this.itemid = itemid;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }
}
