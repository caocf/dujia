package cn.com.gome.dujia.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.io.Serializable;
/**
 * 
 * Description  : 基础model
 * Copyright    : Copyright (c) 2008- 2016 All rights reserved;
 * Created Time : 2016/4/18 17:18;
 *
 * @author WenJie Mai
 * @version 1.0
 */
public class BaseDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -6561778611940141467L;

    @Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@SuppressWarnings("unchecked")
	public <T> T clone(Class<T> cls) throws CloneNotSupportedException {
		return (T) clone();
	}
}
