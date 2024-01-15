package com.projectteamspring.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@MapperScan(basePackages = {"com.projectteamspring.www.repository"})
@ComponentScan(basePackages = {"com.projectteamspring.www.service","com.projectteamspring.www.handler"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class RootConfig {
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariconfig = new HikariConfig();
		hikariconfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariconfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springdb");
		hikariconfig.setUsername("springUser");
		hikariconfig.setPassword("mysql");
		
		hikariconfig.setMaximumPoolSize(5);
		hikariconfig.setMinimumIdle(5);
		
		hikariconfig.setConnectionTestQuery("SELECT now()");
		hikariconfig.setPoolName("springHikariCP");
		
		hikariconfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		hikariconfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		hikariconfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "true");
		hikariconfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariconfig);
		return hikariDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return (SqlSessionFactory)sqlFactoryBean.getObject();
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
