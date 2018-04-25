package com.lavender.javassist;

import javassist.*;

public class Test {
    public static void main(String[] args) throws NotFoundException, CannotCompileException {
        replaceMethodBody("com.lavender.javassist.Student", "execute", "System.out.println(\"this method is changed dynamically!\");");
        Student student = new Student();
        student.execute();
    }

    public static void replaceMethodBody(String clazzName, String methodName, String newMethodBody) {
        try {
            CtClass clazz = ClassPool.getDefault().get(clazzName);
            CtMethod method = clazz.getDeclaredMethod(methodName);
            method.insertAt(0,newMethodBody);
            clazz.toClass();
        } catch (NotFoundException | CannotCompileException e) {
            throw new RuntimeException(e);
        }
    }
}
