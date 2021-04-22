package com.dmitrenko.database.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;

import javax.sql.DataSource;

public class RetryableDataSourceWrapperBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
    return bean instanceof DataSource ? new RetryableDataSource((DataSource) bean) : bean;
  }

  @Override
  public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
    return bean;
  }

}
