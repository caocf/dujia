package cn.com.gome.dujia.service;

import cn.com.gome.gis.enums.ImageParEnum;
import cn.com.gome.gis.enums.ImageTypeEnum;

/**
 * 图片服务
 */
public interface ImageService {
	
    /**
     * 根据文件名获取指定大小的图片。
     * @param imageName
     * @param imageSize
     * @return
     */
    String getImageUrl(String imageName, ImageParEnum imageSize);

    /**
     * 根据文件名获取指定格式和大小的图片地址
     * @param imageName
     * @param parEnum
     * @param typeEnum
     * @return
     */
    String getImageUrl(String imageName, ImageParEnum parEnum, ImageTypeEnum typeEnum);

    /**
     * 上传图片，并返回文件名称
     * @param imageUrl
     * @return
     */
    String uploadRemoteFile2GFS(String imageUrl);

    /**
     * 上传图片，获取文件名
     * @param imgData
     * @return
     */
    String uploadRemoteFile2GFS(String imageName, byte[] imgData);

    /**
     * 上传图片，获取文件名
     * @param imgData
     * @return
     */
    String uploadRemoteFile2GFS(byte[] imgData);

}
