package com.example.sericefeign;

import org.springframework.stereotype.Service;

/**
 * Created by yuhuijuan on 2018/2/24
 */
@Service
public class SchedualServiceHiHystric implements SchedualServiceHi {
	@Override
	public String sayHiFromClientOne(String name) {
		return "sorry " + name;
	}
}
