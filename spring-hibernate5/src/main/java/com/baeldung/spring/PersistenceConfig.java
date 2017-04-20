package com.nklkarthi.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.nklkarthi.persistence.dao.IBarAuditableDao;
import com.nklkarthi.persistence.dao.IBarDao;
import com.nklkarthi.persistence.dao.IFooAuditableDao;
import com.nklkarthi.persistence.dao.IFooDao;
import com.nklkarthi.persistence.dao.impl.BarAuditableDao;
import com.nklkarthi.persistence.dao.impl.BarDao;
import com.nklkarthi.persistence.dao.impl.BarJpaDao;
import com.nklkarthi.persistence.dao.impl.FooAuditableDao;
import com.nklkarthi.persistence.dao.impl.FooDao;
import com.nklkarthi.persistence.service.IBarAuditableService;
import com.nklkarthi.persistence.service.IBarService;
import com.nklkarthi.persistence.service.IFooAuditableService;
import com.nklkarthi.persistence.service.IFooService;
import com.nklkarthi.persistence.service.impl.BarAuditableService;
import com.nklkarthi.persistence.service.impl.BarJpaService;
import com.nklkarthi.persistence.service.impl.BarSpringDataJpaService;
import com.nklkarthi.persistence.service.impl.FooAuditableService;
import com.nklkarthi.persistence.service.impl.FooService;
import com.google.common.base.Preconditions;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.nklkarthi.persistence" }, transactionManagerRef = "jpaTransactionManager")
@EnableJpaAuditing
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "com.nklkarthi.persistence" })
public class PersistenceConfig {

    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.nklkarthi.persistence.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(restDataSource());
        emf.setPackagesToScan(new String[] { "com.nklkarthi.persistence.model" });

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);
        emf.setJpaProperties(hibernateProperties());

        return emf;
    }

    @Bean
    public DataSource restDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PlatformTransactionManager jpaTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public IBarService barJpaService() {
        return new BarJpaService();
    }

    @Bean
    public IBarService barSpringDataJpaService() {
        return new BarSpringDataJpaService();
    }

    @Bean
    public IFooService fooHibernateService() {
        return new FooService();
    }

    @Bean
    public IBarAuditableService barHibernateAuditableService() {
        return new BarAuditableService();
    }

    @Bean
    public IFooAuditableService fooHibernateAuditableService() {
        return new FooAuditableService();
    }

    @Bean
    public IBarDao barJpaDao() {
        return new BarJpaDao();
    }

    @Bean
    public IBarDao barHibernateDao() {
        return new BarDao();
    }

    @Bean
    public IBarAuditableDao barHibernateAuditableDao() {
        return new BarAuditableDao();
    }

    @Bean
    public IFooDao fooHibernateDao() {
        return new FooDao();
    }

    @Bean
    public IFooAuditableDao fooHibernateAuditableDao() {
        return new FooAuditableDao();
    }

    private final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        hibernateProperties.setProperty("hibernate.show_sql", "true");

        // Envers properties
        hibernateProperties.setProperty("org.hibernate.envers.audit_table_suffix", env.getProperty("envers.audit_table_suffix"));

        return hibernateProperties;
    }

}