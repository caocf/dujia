package cn.com.gome.dujia.vo.search;

import java.io.Serializable;

/**
 * 资源信息
 * 
 * @author xiongyan
 * @date 2016年5月3日 上午9:28:06
 */
public class Res implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资源id
	 */
	private String resId;
	
	/**
	 * 资源名称
	 */
	private String resName;
	
	/**
	 * 资源城市
	 */
	private String resCity;
	
	public Res() {
		
	}
	
	public Res(String resId, String resName, String resCity) {
		this.resId = resId;
		this.resName = resName;
		this.resCity = resCity;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResCity() {
		return resCity;
	}

	public void setResCity(String resCity) {
		this.resCity = resCity;
	}

	public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Res other = (Res) that;
        return (this.getResId() == null ? other.getResId() == null : this.getResId().equals(other.getResId()))
            && (this.getResName() == null ? other.getResName() == null : this.getResName().equals(other.getResName()))
            && (this.getResCity() == null ? other.getResCity() == null : this.getResCity().equals(other.getResCity()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getResId() == null) ? 0 : getResId().hashCode());
        result = prime * result + ((getResName() == null) ? 0 : getResName().hashCode());
        result = prime * result + ((getResCity() == null) ? 0 : getResCity().hashCode());
        return result;
    }
}
