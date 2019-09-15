package domain;
import java.util.List;

public class OrderListPage {
    private List<OrderList> list;
    //当前页
    private Integer pageNo;
    //每页显示数量
    private Integer pageSize=4;
    //总页数
    private Integer totalPage;
    //总记录数
    private Integer totalCount;
    //起始行数
    private Integer beginRows;


    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo==null){
            this.pageNo =1;
        }else{
            this.pageNo = pageNo;

        }
    }

    public Integer getTotalPage() {
        //2/1->2   3/2->1 错误的，应该是2
        //如果可以整除,那么totalCount/pageSize，不能整除totalCount/pageSize+1
        return getTotalCount()%getPageSize()==0?getTotalCount()/getPageSize():(getTotalCount()/getPageSize())+1;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getBeginRows() {
        beginRows = (pageNo-1)*pageSize;
        return beginRows;
    }

    public void setBeginRows(Integer beginRows) {
        this.beginRows = beginRows;
    }
}
