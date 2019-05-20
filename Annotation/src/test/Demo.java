package test;

import annotation.MyAnnotation;

@MyAnnotation(getValue = "annotation on class")
public class Demo {

    @MyAnnotation(getValue = "annotation on field")
    public String name;

    @MyAnnotation(getValue = "annotation on method")
    public void hello(){

    }

    @MyAnnotation()
    public void defaultMethod(){

    }

}
