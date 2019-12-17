package sems.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import net.sf.log4jdbc.tools.Log4JdbcCustomFormatter;
import net.sf.log4jdbc.tools.LoggingType;

@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@PropertySource("classpath:db.properties")
public class Db1DataConfig {

  @Value("${jdbc.url.cust_u}")
  private String url;

  @Value("${jdbc.driver}")
  private String driver;

  @Value("${jdbc.username}")
  private String username;

  @Value("${jdbc.password}")
  private String password;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(driver);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);

    return new LazyConnectionDataSourceProxy(dataSource);
  }

  @Bean
  public DataSource dataSourceSpied() {
    Log4jdbcProxyDataSource dataSourceSpied = new Log4jdbcProxyDataSource(dataSource());
    Log4JdbcCustomFormatter formatter = new Log4JdbcCustomFormatter();
    formatter.setLoggingType(LoggingType.MULTI_LINE);
    formatter.setSqlPrefix("");

    dataSourceSpied.setLogFormatter(formatter);

    return dataSourceSpied;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

    sessionFactory.setDataSource(dataSourceSpied());
    sessionFactory
        .setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/**/*Mapper.xml"));
    sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));

    return sessionFactory.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sessionFactory) {
    SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sessionFactory);
    return sessionTemplate;
  }

  @Bean
  public PlatformTransactionManager custTxManager() {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSourceSpied());

    return dataSourceTransactionManager;
  }
}
