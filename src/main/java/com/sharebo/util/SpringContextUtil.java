package com.sharebo.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * ��ȡSpring��������
 * @author niewei
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		SpringContextUtil.applicationContext=ac;
	}
	/***
	 * ��ȡApplicationContext
	 * @return
	 */
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	/**
	 * �����ֻ�ȡSpringbeanʵ��
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
	/**
	 * �������ͻ�ȡSpring bean
	 * @param beanType
	 * @return
	 */
	public static  <T>T getBean(Class<T> beanType){
		return applicationContext.getBean(beanType);
	}
}
