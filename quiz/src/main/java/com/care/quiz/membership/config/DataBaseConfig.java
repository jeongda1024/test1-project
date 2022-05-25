package com.care.quiz.membership.config;

import java.io.IOException;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages ={"com.care.quiz.membership.dao","com.care.quiz.login.dao"}) //root-context-mybatis-spring 이름 가져오기
public class DataBaseConfig {
	
	@Bean
	public HikariDataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		hikariConfig.setUsername("oracle");	
		hikariConfig.setPassword("oracle");
		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
		
	}
	
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() throws IOException{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		sqlSessionFactory.setMapperLocations(resolver.getResources("/mappers/**/*Mapper.xml"));
		return sqlSessionFactory;
	}
}
