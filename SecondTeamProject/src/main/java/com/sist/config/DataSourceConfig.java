package com.sist.config;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@MapperScan(basePackages = {"com.sist.mapper"})
@EnableTransactionManagement
public class DataSourceConfig {
	


	@Bean("ds")
	public BasicDataSource dataSource() {
		BasicDataSource ds=new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@211.238.142.102:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		
		
		return ds;
	}
	    
		@Bean("ssf")
		public SqlSessionFactory sqlsessionFactory(HttpServletRequest request) throws Exception {
			SqlSessionFactoryBean ssf=new SqlSessionFactoryBean();
			
			ssf.setDataSource(dataSource());
			 Resource configLocation = new ClassPathResource("Config.xml");
			 try {
				  ssf.setConfigLocation(configLocation);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		      
		
			
		
		 
			return ssf.getObject();
		}
		
		@Bean("transactionManager")
	    public DataSourceTransactionManager transactionManager() {
	        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
	        transactionManager.setDataSource(dataSource());
	        return transactionManager;
	    }
}
