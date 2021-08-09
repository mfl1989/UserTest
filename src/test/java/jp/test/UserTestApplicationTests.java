package jp.test;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//@WebAppConfiguration
class UserTestApplicationTests {

	
	 
	    @Before(value = "")
	    public void init() {
	        System.out.println("teststrat-----------------");
	    }
	 
	    @After(value = "")
	    public void after() {
	        System.out.println("testfinish-----------------");
	    }
	}
	 
	

