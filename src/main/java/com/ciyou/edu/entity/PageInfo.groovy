package com.ciyou.edu.entity

import com.github.pagehelper.Page

/**
 * @Author C.
 * @Date 2018-02-08 15:35
 * 帮助分页的实体类
 */
class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L
    //当前页
    private int pageNum
    //每页的数量
    private int pageSize
    //总记录数
    private long total
    //总页数
    private int pages
    //结果集
    private List<T> list
    //是否为第一页
    private boolean isFirstPage = false
    //是否为最后一页
    private boolean isLastPage = false

    //导航页码数
    private int navigatePages
    //所有导航页号
    private List<Integer> navigatepageNums

    private String url //url的条件


    public PageInfo() {
    }

    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        if (list instanceof Page) {
            Page page = (Page) list
            this.pageNum = page.getPageNum()
            this.pageSize = page.getPageSize()

            this.pages = page.getPages()
            this.list = page
            this.total = page.getTotal()
        } else if (list instanceof Collection) {
            this.pageNum = 1
            this.pageSize = list.size()

            this.pages = 1
            this.list = list
            this.total = list.size()
        }
        if (list instanceof Collection) {
            //判断页面边界
            judgePageBoudary()
            //计算导航条
            getNavigatepage()
        }
    }

    /**
     * 判定页面边界
     */
    void judgePageBoudary() {
        isFirstPage = pageNum == 1
        isLastPage = pageNum == pages
    }

    int getPageNum() {
        return pageNum
    }

    void setPageNum(int pageNum) {
        this.pageNum = pageNum
    }

    int getPageSize() {
        return pageSize
    }

    void setPageSize(int pageSize) {
        this.pageSize = pageSize
    }

    long getTotal() {
        return total
    }

    void setTotal(long total) {
        this.total = total
    }

    int getPages() {
        return pages
    }

    void setPages(int pages) {
        this.pages = pages
    }

    public List<T> getList() {
        return list
    }

    void setList(List<T> list) {
        this.list = list
    }

    boolean isIsFirstPage() {
        return isFirstPage
    }

    void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage
    }

    boolean isIsLastPage() {
        return isLastPage
    }

    void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage
    }

    boolean getIsFirstPage() {
        return isFirstPage
    }

    boolean getIsLastPage() {
        return isLastPage
    }

    int getNavigatePages() {
        return navigatePages
    }

    void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages
    }

    List<Integer> getNavigatepageNums() {
        return navigatepageNums
    }

    void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums
    }

    String getUrl() {
        return url
    }

    void setUrl(String url) {
        this.url = url
    }
/**
     * 根据显示的导航页码数 计算得到导航条
     * @param navigatePages 显示的导航页码数
     * 默认为5个
     */
    void getNavigatepage(int navigatePages = 5){
        this.navigatePages = navigatePages
        this.navigatepageNums = new ArrayList<Integer>()
        //1.如果总页数<=navigatePages，那么页码列表为1 ~ totaPage 从第一页到总页数
        if(this.pages <= navigatePages){
            for(i in 1 .. this.pages){
                this.navigatepageNums?.add(i)
            }
        }else{
            //2.总页数>navigatePages的情况
            //按公式计算，让列表的头为当前页-((navigatePages-1)/2)；列表的尾为当前页+((navigatePages-1)/2)
            int begin = this.pageNum - ((navigatePages - 1) / 2)
            int end = this.pageNum + ((navigatePages - 1) / 2)
            //处理begin出界
            if(begin < 1){
                //如果开头的导航码数小于1了，那么导航的码数就应该从1到navigatePages的情况
                for(i in 1 .. navigatePages){
                    this.navigatepageNums?.add(i)
                }
            }else if(end > this.pages){
                //处理end结尾的问题
                //如果导航结尾超过总页码数，那么结尾应该为总页码数，开头应该为总页码数 - navigatePages
                for(i in pages - navigatePages + 1 .. pages){
                    this.navigatepageNums?.add(i)
                }
            }else{
                for(i in begin .. end){
                    this.navigatepageNums?.add(i)
                }
            }
        }
    }


    @Override
    public String toString() {
        return "PageInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", pages=" + pages +
                ", list=" + list +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", navigatePages=" + navigatePages +
                ", navigatepageNums=" + navigatepageNums +
                ", url='" + url + '\'' +
                '}';
    }
}