package org.examples;

import java.util.Properties;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.usertype.DynamicParameterizedType;

public class JsonStringType extends AbstractSingleColumnStandardBasicType<Object> implements DynamicParameterizedType{

	private static final long serialVersionUID = 1L;

	public JsonStringType() {
		super(JsonStringSqlTypeDescriptor.INSTANCE, new JsonTypeDescriptor());
	}

	@Override
	public void setParameterValues(Properties parameters) {
		((JsonTypeDescriptor)getJavaTypeDescriptor()).setParameterValues(parameters);
	}

	@Override
	public String getName() {
		return "json";
	}

}
