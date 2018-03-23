package com.example.configclient;

/**
 * Created by yuhuijuan on 2018/2/26
 */
public class PersonDaoImpl implements PersonDao {
	@Override
	public String say() {
		System.out.println("time to eat");
		return "say";
	}
}
