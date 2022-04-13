package com.urase.webapp;

import com.urase.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("person");
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        // TODO : invoke r.toString via reflection
        System.out.println(r);
        Class clazz = Class.forName(Resume.class.getName());
        Method toString = clazz.getDeclaredMethod("toString");
        System.out.println(toString.invoke(r));
    }
}
