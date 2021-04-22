package com.dmitrenko.database.config;

import com.p6spy.engine.spy.P6DataSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;

public class P6SpyDataSourceWrapperPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
    return bean instanceof DataSource ? new P6DataSource((DataSource) bean) : bean;
  }

  @Override
  public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
    return bean;
  }

}
