package com.example.configclient;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yuhuijuan on 2018/2/26
 */
public class PersonTest {

	@Test
	public void test(){
		PersonDao pDao = new PersonDaoImpl();
		PersonDao proxy = (PersonDao) Proxy.newProxyInstance(pDao.getClass().getClassLoader(),
				pDao.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println("before");

						System.out.println("after");
						return "yes yoyo";

					}
				});
		System.out.print( proxy.say());
	}
}
