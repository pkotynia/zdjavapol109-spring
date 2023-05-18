package pl.sda.demo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class ReflectionExampleMain {

    public static void main(String[] args) throws IllegalAccessException {

        ReflectionExample example = new ReflectionExample();
        System.out.println(ReflectionExample.class.getDeclaredFields().length);

        for (Field declaredField : ReflectionExample.class.getDeclaredFields()) {
            declaredField.setAccessible(true);
            System.out.println(declaredField.getName());
            System.out.println(declaredField.get(example));
            declaredField.set(example, "changed Value");
        }

        for (Annotation declaredAnnotation : ReflectionExample.class.getDeclaredAnnotations()) {
            System.out.println("declaredAnnotation = " + declaredAnnotation);
        }

        System.out.println("example.getPrivateField() = " + example.getPrivateField());
    }
}
