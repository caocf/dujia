package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.dto.ZbyCityDto;
import cn.com.gome.dujia.dto.ZbyCityRespDto;
import cn.com.gome.dujia.mapper.business.ZbyCityMapper;
import cn.com.gome.dujia.mapper.business.ZbyRecomInfoMapper;
import cn.com.gome.dujia.model.ZbyCity;
import cn.com.gome.dujia.model.ZbyRecomInfo;
import cn.com.gome.dujia.service.ZbyCityService;
import com.gome.plan.mybatis.mapper.entity.Example;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ZbyCityServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>04/15/2016</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class ZbyCityServiceImplTest extends TestCase {

    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private ZbyCityService cityService;

    @Autowired
    private ZbyCityMapper cityMapper;

    @Autowired
    private ZbyRecomInfoMapper recomInfoMapper;


    @Test
    public void ttt() {
        Example example = new Example(ZbyRecomInfo.class);
        example.selectProperties("title");
        ZbyRecomInfo info = new ZbyRecomInfo();
        info.setProductId("tc_14349");
        example.createCriteria().andEqualTo(info);

        List<ZbyRecomInfo> list = recomInfoMapper.selectByExample(example);
        for (ZbyRecomInfo info1 : list) {
            logger.info(">>>>>>>>>>>>>>>>>>>>>>" + info1.getTitle());
        }
    }

    public void selectCityByIdTest() {
        ZbyCity city = cityService.selectCityById("53");

        logger.info(">>>>>>>>>>>>>>>>>>>>>>" + city.getCityPinyin());
    }

    /**
     * Method: queryCity()
     */
    @Test
    public void testqueryCity() throws Exception {
        ZbyCityRespDto respDto = cityService.queryCity();

        for (ZbyCityDto dto : respDto.getCityMap().get("hot")) {
            System.out.println(">>>>>>>>>>>>>>>>" + dto.getId() + "---" + dto.getName() + "---" + dto.getPinyin());
        }
    }

    /**
     * Method: getAllZbyCity()
     */
    @Test
    public void testGetAllZbyCity() throws Exception {
        List<ZbyCityDto> citys = cityMapper.queryHotCity();

        for (ZbyCityDto dto : citys) {
            System.out.println(">>>>>>>>>>>>>>>>" + dto.getId() + "---" + dto.getName() + "---" + dto.getPinyin());
        }
    }


}
