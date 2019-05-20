package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestDemo {
	public static void main(String[] args) throws Exception {
		//获取类上注解
		Class<Demo> clazz = Demo.class;
		MyAnnotation annotationOnClass = clazz.getAnnotation(MyAnnotation.class);
		System.out.println(annotationOnClass.getValue());

		//获取成员变量上的注解
		Field name = clazz.getField("name");
		MyAnnotation annotationOnField = name.getAnnotation(MyAnnotation.class);
		System.out.println(annotationOnField.getValue());

		//获取hello方法上的注解
		Method hello = clazz.getMethod("hello", null);
		MyAnnotation annotationOnMethod = hello.getAnnotation(MyAnnotation.class);
		System.out.println(annotationOnMethod.getValue());

		//获取defaultMethod方法上的注解
		Method defaultMethod = clazz.getMethod("defaultMethod", null);
		MyAnnotation annotationOnDefaultMethod = defaultMethod.getAnnotation(MyAnnotation.class);
		System.out.println(annotationOnDefaultMethod.getValue());
	}
}




