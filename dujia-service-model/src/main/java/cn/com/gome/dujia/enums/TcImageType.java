package cn.com.gome.dujia.enums;

/**
 * 同程图片尺寸枚举
 *
 * @author zhaoxiang
 */
public enum TcImageType {

    WH100x75("_100x75_00"),
    WH120x120("_120x120_00"),
    WH150x150("_150x150_00"),
    WH160x120("_160x120_00"),
    WH200x150("_200x150_00"),
    WH200x120("_200x120_00"),
    WH500x270("_500x270_00"),
    WH360x250("_360x250_00"),
    WH480x360("_480x360_00");

    private String value;

    TcImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
