package domain;


import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

  private String pid;
  private String pname;
  private Double marketPrice;
  private Double shopPrice;

  private String pimage;

  private Date pdate;
  private Long isHot;
  private String pdesc;
  private Long pflag;
  private String cid;

  @Override
  public String toString() {
    return "Product{" +
            "pid='" + pid + '\'' +
            ", pname='" + pname + '\'' +
            ", marketPrice=" + marketPrice +
            ", shopPrice=" + shopPrice +
            ", pimage='" + pimage + '\'' +
            ", pdate=" + pdate +
            ", isHot=" + isHot +
            ", pdesc='" + pdesc + '\'' +
            ", pflag=" + pflag +
            ", cid='" + cid + '\'' +
            '}';
  }

  public String getPid() {
    return pid;
  }

  public void setPid(String pid) {
    this.pid = pid;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public Double getMarketPrice() {
    return marketPrice;
  }

  public void setMarketPrice(Double marketPrice) {
    this.marketPrice = marketPrice;
  }

  public Double getShopPrice() {
    return shopPrice;
  }

  public void setShopPrice(Double shopPrice) {
    this.shopPrice = shopPrice;
  }

  public String getPimage() {
    return pimage;
  }

  public void setPimage(String pimage) {
    this.pimage = pimage;
  }

  public Date getPdate() {
    return pdate;
  }

  public void setPdate(Date pdate) {
    this.pdate = pdate;
  }

  public Long getIsHot() {
    return isHot;
  }

  public void setIsHot(Long isHot) {
    this.isHot = isHot;
  }

  public String getPdesc() {
    return pdesc;
  }

  public void setPdesc(String pdesc) {
    this.pdesc = pdesc;
  }

  public Long getPflag() {
    return pflag;
  }

  public void setPflag(Long pflag) {
    this.pflag = pflag;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }
}
