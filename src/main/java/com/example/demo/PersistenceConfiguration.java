package com.example.demo;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration {

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("com.example.demo.model");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		return dataSource;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager
				= new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private final Properties hibernateProperties() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");

//		hibernateProperties.setProperty("hibernate.default_schema", "RANDOM_SCHEMA");
//		hibernateProperties.setProperty("hibernate.default_catalog", "RANDOM");

//		hibernateProperties.setProperty("hibernate.max_fetch_depth", "0"); //ограничивает количество join table
//		hibernateProperties.setProperty("hibernate.default_batch_fetch_size", "1"); // устанавливает количество запросов в пачке

//		hibernateProperties.setProperty("hibernate.generate_statistics", "true"); // статистика

//		hibernateProperties.setProperty("hibernate.use_sql_comments", "true");

		hibernateProperties.setProperty("hibernate.use_identifier_rollback", "true");

//		hibernateProperties.setProperty("hibernate.transaction.auto_close_session", "true"); //автозакрытие сессии после комита


//		hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");
//		hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");
//		hibernateProperties.setProperty("hibernate.cache.provider_class", "net.sf.ehcache.hibernate.EhCacheProvider");
//		hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");

//		hibernateProperties.setProperty("hibernate.cache.use_structured_entries", "true");


		return hibernateProperties;
	}

}
