package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	String getValue() default "no description";
}


@interface MyAnnotation2 {
}

enum CityEnum {
	WENZHOU,
	HANGZHOU,
	NINGBO;
}
