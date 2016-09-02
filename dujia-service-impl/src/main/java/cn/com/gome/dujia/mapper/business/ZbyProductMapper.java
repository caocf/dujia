package cn.com.gome.dujia.mapper.business;

import cn.com.gome.dujia.dto.QuickLinkDto;
import cn.com.gome.dujia.dto.ZbyProductDetailDto;
import cn.com.gome.dujia.model.ZbyProduct;
import com.gome.plan.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ZbyProductMapper extends Mapper<ZbyProduct> {

    /**
     * 根据线路ID获取线路详情
     *
     * @param productId
     * @return
     */
    ZbyProductDetailDto getProductDetailDtoById(String productId);

    /**
     * 获取城市所有线路含有的游玩天数列表
     *
     * @param cityId
     * @return
     */
    List<QuickLinkDto> queryTravelDays(String cityId);

    void batchInsert(List<ZbyProduct> list);

    void updateIsDelete(ZbyProduct product);

    void batchUpdate(List<ZbyProduct> list);

    void batchUpdateSBCount(List<ZbyProduct> list);


    ZbyProduct selectByProductId(String productId);

    /**
     * 查询线路销量，浏览量
     *
     * @param product
     * @return
     */
    List<ZbyProduct> querySaleBrowseCount(ZbyProduct product);


    void updateSelective(ZbyProduct product);


    /**
     * 查询线路信息
     *
     * @param productIds
     * @return
     */
    List<ZbyProduct> queryProductByPIds(List<String> productIds);

    /**
     * 获取线路的游玩天数
     *
     * @param productId
     * @return
     */
    Integer getTravlDaysByProductId(String productId);

    /**
     * 获取所有线路含有的游玩天数列表
     *
     * @return
     */
    List<Integer> queryAllTravelDays();

    /**
     * 根据城市id，获取线路最多的前N个城市ID
     *
     * @param param map<cityIds,><limit,>
     * @return
     */
    List<ZbyProduct> queryMostLineByCity(Map param);
}