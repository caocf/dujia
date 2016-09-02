package cn.com.gome.dujia.service.impl;

import cn.com.gome.dujia.service.ImageService;
import cn.com.gome.gis.client.GisClient;
import cn.com.gome.gis.dto.GisResultDto;
import cn.com.gome.gis.enums.ImageParEnum;
import cn.com.gome.gis.enums.ImageTypeEnum;
import com.gome.plan.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 图片服务
 */
@Service
public class ImageServiceImpl implements ImageService {

    private Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    //返回成功标识
    private static final String SUCC = "Y";

    //图片后缀名
    private static final String[] fileTypes = new String[]{".gif", ".jpg", ".png", ".jpeg"};

    @Autowired
    private GisClient gisClient;

    /**
     * 根据文件名获取指定大小的图片。
     *
     * @param imageName
     * @param parEnum
     * @return
     */
    public String getImageUrl(String imageName, ImageParEnum parEnum) {
        if (StringUtils.isEmpty(imageName) || null == parEnum) {
            logger.error("根据文件名获取指定大小的图片 参数不能为空");
            return null;
        }
        return gisClient.zoomImg(imageName, parEnum, ImageTypeEnum.JPG);
    }

    /**
     * 根据文件名获取指定格式和大小的图片地址
     *
     * @param imageName
     * @param parEnum
     * @param typeEnum
     * @return
     */
    public String getImageUrl(String imageName, ImageParEnum parEnum, ImageTypeEnum typeEnum) {
        if (StringUtils.isEmpty(imageName) || null == parEnum || null == typeEnum) {
            logger.error("根据文件名获取指定格式和大小的图片地址 参数不能为空");
            return null;
        }
        return gisClient.zoomImg(imageName, parEnum, typeEnum);
    }

    /**
     * 上传图片，并返回文件名称
     *
     * @param imageUrl
     * @return
     */
    public String uploadRemoteFile2GFS(String imageUrl) {
        if (StringUtils.isEmpty(imageUrl)) {
            logger.error("上传图片地址 不能为空");
            return null;
        }
        // 检查文件扩展类型
        if (!checkImgType(imageUrl)) {
            logger.error("不符合的文件类型{},必须是[gif、jpg、png、jpeg]", imageUrl);
            return null;
        }
        logger.info("要上传的图片地址:" + imageUrl);

        // 获取文件名
        String fileName = this.getImgName(imageUrl);
        // 获取文件byte[]
        byte[] imgData = this.getImage(imageUrl);
        if (null == imgData) {
            return null;
        }

        // 生成外网访问地址
        GisResultDto dto = uploadFile2GFS(fileName, imgData, true);
        if (null != dto) {
            return dto.getUrl();
        } else {
            return null;
        }
    }

    /**
     * 上传图片，获取文件名
     *
     * @param imgData
     * @return
     */
    public String uploadRemoteFile2GFS(String imageName, byte[] imgData) {
        // 生成外网访问地址
        GisResultDto dto = uploadFile2GFS(imageName, imgData, true);
        if (null != dto) {
            return dto.getUrl();
        } else {
            return null;
        }
    }

    /**
     * 上传图片，获取文件名
     *
     * @param imgData
     * @return
     */
    public String uploadRemoteFile2GFS(byte[] imgData) {
        // 生成外网访问地址
        GisResultDto dto = uploadFile2GFS(null, imgData, true);
        if (null != dto) {
            return dto.getUrl();
        } else {
            return null;
        }
    }

    /**
     * 上传byte[]格式文件到GFS,返回图片访问地址
     *
     * @param fileName    文件名
     * @param byteFile    byte[]格式文件
     * @param isOutDomain 是否生成外网域名
     * @return
     */
    private GisResultDto uploadFile2GFS(String fileName, byte[] byteFile, boolean isOutDomain) {
        if (null == byteFile) {
            logger.error("上传图片文件到GFS失败, 文件为NULL");
            return null;
        }
        //没有图片名，指定默认的图片名
        if (StringUtils.isEmpty(fileName)) {
            fileName = "default.jpg";// 默认图片名
            logger.info("图片名不存在使用默许文件名{}", fileName);
        }
        try {
            // GFS上传图片
            GisResultDto dto = gisClient.fileup(fileName, byteFile);
            if (null != dto) {
                if (SUCC.equals(dto.getResult())) {
                    logger.info("文件名{}GFS上传图片成功{}", fileName, dto.getUrl());
                    return dto;
                } else {
                    logger.error("文件名{}GFS上传图片失败{}-{}", fileName, dto.getErrorCode(), dto.getMsg());
                }
            } else {
                logger.error("文件名{}GFS上传图片失败", fileName);
            }
        } catch (Exception e) {
            logger.error("GFS上传图片成功失败", e);
        }
        return null;
    }

    /**
     * 截取图片名称
     *
     * @param remoteImgUrl
     * @return
     */
    private String getImgName(String remoteImgUrl) {
        if (remoteImgUrl.lastIndexOf("/") == -1) {
            return null;
        } else {
            return remoteImgUrl.substring(remoteImgUrl.lastIndexOf("/") + 1);
        }
    }

    /**
     * 检查文件类型
     *
     * @param img
     * @return
     */
    private boolean checkImgType(String img) {
        String suffix = getSuffix(img);
        for (String s : fileTypes) {
            if (s.equalsIgnoreCase(suffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 截取文件的类型
     *
     * @param imgFilePathName
     * @return
     */
    private String getSuffix(String imgFilePathName) {
        return imgFilePathName.substring(imgFilePathName.lastIndexOf(".") == -1 ? 0 : imgFilePathName.lastIndexOf(".")).toLowerCase();
    }


    /**
     * 根据url获取远程图片
     *
     * @param remoteImgUrl
     * @return
     */
    private byte[] getImage(String remoteImgUrl) {
        ByteArrayOutputStream outStream = null;
        InputStream inStream = null;
        try {
            URL url = new URL(remoteImgUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3 * 1000);// 连接时间3s
            conn.setReadTimeout(3 * 1000); // 读取时间3s

            // 判断是否gzip压缩
            boolean gzip = false;
            if (conn.getHeaderField("Content-Encoding") != null && conn.getHeaderField("Content-Encoding").equalsIgnoreCase("gzip")) {
                gzip = true;
            }
            logger.info("获取图片:url={},gzip={}", remoteImgUrl, gzip);
            // 取前两个字节
            byte[] header = new byte[2];
            if (gzip) {
                try {
                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                    bis.mark(2);
                    int result = bis.read(header);
                    // reset输入流到开始位置
                    bis.reset();
                    // 判断是否是GZIP格式
                    int ss = (header[0] & 0xff) | ((header[1] & 0xff) << 8);
                    if (result != -1 && ss == GZIPInputStream.GZIP_MAGIC) {
                        // 为gzip数据压缩格式...
                        inStream = new GZIPInputStream(bis);
                    } else {
                        inStream = bis;
                    }
                } catch (Exception e) {
                    logger.error("获取图片{}失败", remoteImgUrl, e);
                    return null;
                }
            } else {
                // 不是Gzip直接读取
                inStream = conn.getInputStream();
            }

            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 10]; // 10K缓冲
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            // 返回byte[]
            return outStream.toByteArray();
        } catch (Exception e) {
            logger.error("获取图片{}失败", remoteImgUrl, e);
            return null;
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (Exception e) {
                logger.error("关闭获取图片流失败", e);
            }
        }
    }


}
