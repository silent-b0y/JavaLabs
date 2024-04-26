package lab2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
        ClassWithMethods objectWithMethods = new ClassWithMethods();
        try {
            Constructor constructor = objectWithMethods.getClass().getConstructor();
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
        Class classObject = objectWithMethods.getClass();
        Method[] methods = classObject.getDeclaredMethods();
        for (Method method : methods) {
            Annotation annotation = method.getAnnotation(MyAnnotation.class);
            int methodModifier = method.getModifiers();
            if (annotation != null && ((MyAnnotation) annotation).value() > 0 &&
                    (Modifier.isProtected(methodModifier) || Modifier.isPrivate(methodModifier))) {
                MyAnnotation myAnnotation = (MyAnnotation) annotation;
                Class[] parameterTypes = method.getParameterTypes();
                int length = parameterTypes.length;
                for (int i = 0; i < myAnnotation.value(); i++) {
                    method.setAccessible(true);
                    if (length == 0) {
                        try {
                            method.invoke(objectWithMethods);
                        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        Object[] parameters = new Object[length];
                        for (int j = 0; j < length; j++) {
                            switch (parameterTypes[j].getSimpleName()) {
                                case ("byte") -> parameters[j] = (byte) 0;
                                case ("short") -> parameters[j] = (short) 1;
                                case ("int") -> parameters[j] = 2;
                                case ("long") -> parameters[j] = 3;
                                case ("float") -> parameters[j] = 4.0f;
                                case ("double") -> parameters[j] = 5.0;
                                case ("boolean") -> parameters[j] = false;
                                case ("char") -> parameters[j] = 'c';
                                default -> parameters[j] = null;
                            }
                        }
                        try {
                            method.invoke(objectWithMethods, parameters);
                        } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
