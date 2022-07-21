package com.revature.razang.data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.sql.Date;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.revature.razang.models.User;
import com.revature.razangorm.annotations.ORMIgnore;
import com.revature.razangorm.annotations.Subclass;

@Disabled("Disabled until I need to mess with reflections again")
public class ReflectionTest {
    
    @Test
    public void constructor() {
        Object obj = new User();
        User user = new User(9, "Mubasher", new Date(2021-01-01),
				"mmm@gmail.com", "19021100110", "3322222");
                
        Class<? extends Object> objClass = obj.getClass();
		Constructor<? extends Object>[] constructors = objClass.getDeclaredConstructors();
		for (Constructor<? extends Object> construct : constructors) {
            System.out.println("Constructor: " + construct);
			Parameter[] parameters = construct.getParameters();
            Stream.of(parameters).forEach(p -> {
                System.out.println("Parameter: " + p);
            });
		}

        try {
            Object myObj = obj.getClass().getDeclaredConstructor().newInstance();
            Field[] fields = getFields (myObj.getClass());
            Field[] ufields = getFields (user.getClass());
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                ufields[i].setAccessible(true);
                // System.out.println("Field Name: " + fields[i].getName() + " " + fields[i].get(myObj) + " User Field Value: " + ufields[i].get(user));
                fields[i].set(myObj, ufields[i].get(user));
                // System.out.println("\t AFTER SET Field Name: " + fields[i].getName() + " " + fields[i].get(myObj));
            }
            System.out.println(myObj.toString());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    public static Field[] getFields (Class<?> objClass) {
        Field[] fields = objClass.getDeclaredFields();
        
        // If class is a subclass, get the superclass fields too
        while (objClass.isAnnotationPresent(Subclass.class)) {
            objClass = objClass.getSuperclass();
            Field[] superFields = objClass.getDeclaredFields();
            fields = Stream.concat(Arrays.stream(superFields), Arrays.stream(fields))
                    .toArray(Field[]::new);
        }
        
        // Filter out any fields with the ORMIgnore annotation
        fields = Stream.of(fields)
        .filter(field -> !field.isAnnotationPresent(ORMIgnore.class))
        .collect(Collectors.toList()).stream().toArray(Field[]::new);
        return fields;
    }
}
