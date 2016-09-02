package cn.com.gome.dujia.vo.order;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
/**
 * @author wangweiran
 *
 * 订单增量返回信息
 */
public class QueryOrderIncrementResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 增量信息集合
	 * Y
	 */
	@JacksonXmlProperty(localName = "OrderIncrementList")
	private List<OrderIncrementInfo> orderIncrementList;

}
