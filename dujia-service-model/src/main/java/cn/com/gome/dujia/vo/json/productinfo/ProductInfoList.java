package cn.com.gome.dujia.vo.json.productinfo;

import cn.com.gome.dujia.model.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunming on 2016/5/5.
 */
public class ProductInfoList  implements Serializable {

    private static final long serialVersionUID = 1L;

    List<ZbyProduct> zbyProductList = new ArrayList<ZbyProduct>();
    List<ZbyProductPackage> zbyProductPackageList = new ArrayList<ZbyProductPackage>();
    List<ZbyRecomInfo> zbyRecomInfoList = new ArrayList<ZbyRecomInfo>();
    List<ZbyProductPackageDetail> zbyProductPackageDetailList = new ArrayList<ZbyProductPackageDetail>();
    List<ZbyProductAddition> zbyProductAdditionList = new ArrayList<ZbyProductAddition>();

    public List<ZbyProduct> getZbyProductList() {
        return zbyProductList;
    }

    public void setZbyProductList(List<ZbyProduct> zbyProductList) {
        this.zbyProductList = zbyProductList;
    }

    public List<ZbyProductPackage> getZbyProductPackageList() {
        return zbyProductPackageList;
    }

    public void setZbyProductPackageList(List<ZbyProductPackage> zbyProductPackageList) {
        this.zbyProductPackageList = zbyProductPackageList;
    }

    public List<ZbyRecomInfo> getZbyRecomInfoList() {
        return zbyRecomInfoList;
    }

    public void setZbyRecomInfoList(List<ZbyRecomInfo> zbyRecomInfoList) {
        this.zbyRecomInfoList = zbyRecomInfoList;
    }

    public List<ZbyProductPackageDetail> getZbyProductPackageDetailList() {
        return zbyProductPackageDetailList;
    }

    public void setZbyProductPackageDetailList(List<ZbyProductPackageDetail> zbyProductPackageDetailList) {
        this.zbyProductPackageDetailList = zbyProductPackageDetailList;
    }

    public List<ZbyProductAddition> getZbyProductAdditionList() {
        return zbyProductAdditionList;
    }

    public void setZbyProductAdditionList(List<ZbyProductAddition> zbyProductAdditionList) {
        this.zbyProductAdditionList = zbyProductAdditionList;
    }
}
