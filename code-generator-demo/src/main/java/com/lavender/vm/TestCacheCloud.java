package com.lavender.vm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class TestCacheCloud {
    private static Properties props = null;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        /* first, get and initialize an engine */

        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();
        /* next, get the Template */
        Template t = ve.getTemplate("Action2.vm", "gb2312");
        //          Template t = ve.getTemplate( "src/Action.vm" );
        //如果改为上面则出现乱码．
        StringBuilder byteMethod = new StringBuilder();
        /* create a context and add data */
        VelocityContext context = new VelocityContext();
        Class clazz = JedisClusterBinaryScriptingCommands.class;
        Method ms[] = clazz.getMethods();
        for (int i = 0; i < ms.length; i++) {
            //方法名
            String mName = ms[i].getName();
            //返回类型
            Class returnType = ms[i].getReturnType();
            //参数列表
            Parameter argsNames[] = ms[i].getParameters();

            String returnTypeName = "";
            if (returnType.getName().equals("[B")) {
                returnTypeName = "byte[]";
            } else {
                returnTypeName = returnType.getName();
            }
            byteMethod.append("@Override public " +returnTypeName +" " + mName + " (");
            String argName = "";
            String argNameWithoutType = "";
            for (int j = 0; j < argsNames.length; j++) {
                // 参数名与类型
                argName += argsNames[j].toString();
//                byteMethod.append(argName);
                argNameWithoutType += argsNames[j].getName();
                if (j < argsNames.length - 1) {
//                    byteMethod.append(",");
                    argName += ",";
                    argNameWithoutType += ",";
                }
            }
            byteMethod.append(argName + "){");
            byteMethod.append("return jedisCluster." + mName + "(" + argNameWithoutType + ");}");
        }

        context.put("bytesMethod", byteMethod);

        /* now render the template into a StringWriter */
        StringWriter writer = new StringWriter();
        t.merge(context, writer);

        writeJavaFile("/Users/yuhuijuan/Documents/2018/my_project/study_demo-java-/code-generator-demo/src/main/java/com/lavender/" + "RedisCluster_.java", writer.toString());

        /* show the World */
        // System.out.println( writer.toString() );

    }


    private static void writeJavaFile(String name, String str) {
        try {
            FileWriter fw = new FileWriter(new File(name), true);
            BufferedWriter bw = new BufferedWriter(fw);

            // 将读入的字符串写入到文件中
            bw.write(str, 0, str.length());
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error-- :" + e.toString());
        }
    }

    private static void loadProperties() {
        props = new Properties();
        try {
            props.load(Test.class.getResourceAsStream("/action.properties"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static Object[] prop2List() {
        loadProperties();
        System.out.println(props.size());
        if (props.isEmpty()) return null;
        Object[] aa = props.values().toArray();
        return aa;
    }


}
