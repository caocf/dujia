package cn.com.gome.dujia.enums;

/**
 * 列表页排序类型定义
 * Created by zhaoxiang-ds on 2016/5/10.
 */
public enum WebSortType {

    DEFAULT_SORT(0, "", ""),//默认

    SALE_COUNT(1, "saleCount", "desc"),//销量

    PRICE_H2L(2, "productMinPrice", "desc"),//价格高到低

    PRICE_L2H(3, "productMinPrice", "asc");//价格低到高

    private Integer key;
    private String sortField;
    private String sortOrder;

    WebSortType(Integer key, String SortName, String sortBy) {
        this.key = key;
        this.sortField = SortName;
        this.sortOrder = sortBy;
    }

    public static WebSortType getSortNameByKey(Integer key) {
        switch (key) {
            case 1:
                return SALE_COUNT;
            case 2:
                return PRICE_H2L;
            case 3:
                return PRICE_L2H;
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public String getSortField() {
        return sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }
}
