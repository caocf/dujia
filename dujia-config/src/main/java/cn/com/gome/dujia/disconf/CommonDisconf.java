package cn.com.gome.dujia.disconf;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

/**
 * 公共 配置信息
 * 
 * @author xiongyan
 * @date 2016年4月14日 上午10:02:35
 */
@DisconfFile(filename = "common.properties")
public final class CommonDisconf {

	/**
	 * zookeeper地址
	 */
	public static String zookeeperAddress;
	
	/**
	 * 业务名称
	 */
	public static String businessName;
	
	/**
	 * 周边游 索引名称
	 */
	public static String zbyIndex;
	
	/**
	 * 周边游 索引别名
	 */
	public static String zbyAlias;
	
	/**
	 * 周边游 类型名称
	 */
	public static String zbyType;
	
	/**
	 * 搜索服务器 地址
	 */
	public static String elasticSearchAddress;
	
	/**
	 * 搜索服务器 集群名称
	 */
	public static String elasticSearchClusterName;


	@DisconfFileItem(name = "zookeeper.address")
	public static String getZookeeperAddress() {
		return zookeeperAddress;
	}

	public static void setZookeeperAddress(String zookeeperAddress) {
		CommonDisconf.zookeeperAddress = zookeeperAddress;
	}

	@DisconfFileItem(name = "business_name")
	public static String getBusinessName() {
		return businessName;
	}

	public static void setBusinessName(String businessName) {
		CommonDisconf.businessName = businessName;
	}

	@DisconfFileItem(name = "zby_index")
	public static String getZbyIndex() {
		return zbyIndex;
	}

	public static void setZbyIndex(String zbyIndex) {
		CommonDisconf.zbyIndex = zbyIndex;
	}

	@DisconfFileItem(name = "zby_alias")
	public static String getZbyAlias() {
		return zbyAlias;
	}

	public static void setZbyAlias(String zbyAlias) {
		CommonDisconf.zbyAlias = zbyAlias;
	}

	@DisconfFileItem(name = "zby_type")
	public static String getZbyType() {
		return zbyType;
	}

	public static void setZbyType(String zbyType) {
		CommonDisconf.zbyType = zbyType;
	}

	@DisconfFileItem(name = "elasticSearch_address")
	public static String getElasticSearchAddress() {
		return elasticSearchAddress;
	}

	public static void setElasticSearchAddress(String elasticSearchAddress) {
		CommonDisconf.elasticSearchAddress = elasticSearchAddress;
	}

	@DisconfFileItem(name = "elasticSearch_cluster_name")
	public static String getElasticSearchClusterName() {
		return elasticSearchClusterName;
	}

	public static void setElasticSearchClusterName(String elasticSearchClusterName) {
		CommonDisconf.elasticSearchClusterName = elasticSearchClusterName;
	}

}
