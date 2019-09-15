package domain;

//存储一条记录
public class Cart {
    private Product product;
    private Integer num;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "product=" + product +
                ", num=" + num +
                '}';
    }
}
