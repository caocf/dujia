/**
 * 公用站点配置
 * 
 */
var SiteData = {
    /**
	 * 站点域名, 如：hotel.gome.com.cn** "http://10.126.56.29:8081"
	 * http://hotel.atguat.com.cn:3002*
	 */
    DUJIA_SITE: "",
    /** 项目名称，如:/main,此配置可为空* */
    DYN_URL: "",
    /** 是否登陆验证地址* */
    ATG_SIT: ".gome.com.cn",
    /** 页面一级目录* */
    PAGE_URL : "" , 

    /**
	 * 线上配置成https,UAT 配置http
	 */
    HTTP_HEAD:"https",
    /**
	 * 接口版本号
	 */
    VERSION:"20150521",

    MAIN_LINK:"http://lvxing.gome.com.cn/index.html",
    HOTEL_LINK:"http://hotel.gome.com.cn/index.html",/**酒店首页**/
    FLIGHT_LINK:"http://jipiao.gome.com.cn/",
    TRAVEL_LINK:"http://lvxing.gome.com.cn/index.html",  /**旅行首页**/
	TRAIN_LINK:"http://train.gome.com.cn/index.html"  /** 火車票* */

};

// http://10.126.56.29:8081/
var dynSite='http://g'+SiteData.ATG_SIT,staSite=SiteData.HOTEL_SITE,contextPath='/ec/homeus',imgServer='http://img.gomein.net.cn/images',secureURL='https://g.gome.com.cn:443',pictureserver='http://img.gomein.net.cn/image',cookieDomain=SiteData.ATG_SIT,stageJsServer='http://js.gomein.net.cn',stageImageServer='http://img.gomein.net.cn',stageCssServer='http://css.gomein.net.cn',cssserver='http://css.gomein.net.cn/css',jsserver='http://js.gomein.net.cn/js',versionData='20150007002600110014';
