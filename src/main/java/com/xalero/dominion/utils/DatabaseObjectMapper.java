package com.xalero.dominion.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;

public class DatabaseObjectMapper {

	public static void fillObjectFromResultSet(Object toFill, ResultSet rs, Class<?> classType) {
		Field[] fields = classType.getDeclaredFields();
		for (Field field : fields) {
			Object fieldValue = null;
			try {
				fieldValue = rs.getObject(field.getName());	
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), classType);
				Method method = propertyDescriptor.getWriteMethod();
				method.invoke(toFill, fieldValue);
			} catch (Exception e) {
				continue;
			}
		}
	}
	
	public String getMethodFromFieldName(String fieldName) {
		
		String methodName = "";
		return methodName;
	}
}
