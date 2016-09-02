package cn.com.gome.dujia.vo.order;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
/**
 * @author wangweiran
 *
 * 查询订单增量请求参数
 */
public class QueryOrderIncrementRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 开始时间：2015-8-26 00:00:00
	 * Y
	 */
	@JacksonXmlProperty(localName = "StartTime")
	private Date startTime;
	/**
	 * 结束时间：2015-8-27 00:00:00
	 * Y
	 */
	@JacksonXmlProperty(localName = "EndTime")
	private Date endTime;

}
