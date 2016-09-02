/**
 * 公用站点配置
 *
 */
var SiteData = {
    /**站点域名, 如：hotel.gome.com.cn** "http://10.126.56.29:8081" **/
    HOTEL_SITE: "http://hotel.gome.com.cn",
	FLIGHT_SITE: "http://jipiao.gome.com.cn",
	TRAVEL_SITE: "http://lvxing.gome.com.cn",
	TICKET_SITE: "http://piao.gome.com.cn",
	DUJIA_SITE:  "http://dujia.gome.com.cn",
    /**项目名称，如:/main,此配置可为空**/
    DYN_URL: "",
    /**是否登陆验证地址**/
    ATG_SIT: ".gome.com.cn",
    /**页面一级目录**/
    PAGE_URL : "/page",

    /**
     线上配置成https,UAT 配置http
     **/
    HTTP_HEAD:"https",
    /**
     * 接口版本号
     */
    VERSION:"20150521",
    MAIN_LINK:"http://10.126.56.29:8081/page/index.html",
    HOTEL_LINK:"http://10.126.56.29:8081/page/index.html",
    FLIGHT_LINK:"http://jipiao.gome.com.cn/",
    ADDRESS_LINK:"http://ss.gome.com.cn"
};

//http://10.126.56.29:8081/
var dynSite='http://g'+SiteData.ATG_SIT,staSite=SiteData.HOTEL_SITE,contextPath='/ec/homeus',imgServer='http://img.gomein.net.cn/images',secureURL='https://g.gome.com.cn:443',pictureserver='http://img.gomein.net.cn/image',cookieDomain=SiteData.ATG_SIT,stageJsServer='http://js.gomein.net.cn',stageImageServer='http://img.gomein.net.cn',stageCssServer='http://css.gomein.net.cn',cssserver='http://css.gomein.net.cn/css',jsserver='http://js.gomein.net.cn/js',versionData='20150007002600110014';

