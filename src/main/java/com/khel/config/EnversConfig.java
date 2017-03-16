package com.khel.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by RKasturi on 12/19/2016.
 * Spring Data Envers Configuration
 * reference: https://github.com/spring-projects/spring-data-envers
 */
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class, basePackages = "com.khel")
public class EnversConfig  extends HibernateJpaAutoConfiguration
{
  public EnversConfig(DataSource dataSource, JpaProperties jpaProperties, ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider)
  {
    super(dataSource, jpaProperties, jtaTransactionManagerProvider);
  }

  @Override
  protected void customizeVendorProperties(Map<String, Object> vendorProperties) {
    vendorProperties.put("org.hibernate.envers.revision_field_name", "version_number");
    vendorProperties.put("org.hibernate.envers.revision_type_field_name", "transaction_type");
    vendorProperties.put("hibernate.show_sql", "true");
    vendorProperties.put("hibernate.format_sql", "true");
  }

}
