package pl.kurs.equationsolverapp.confiq;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class JpaConfig {

    @Autowired
    private Environment env;


    @Bean
    public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter adapter, DataSource ds) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pl.kurs.equationsolverapp.model");
        emf.setDataSource(ds);
        return emf;
    }

    @Profile({"prod", "!prod & !dev"})
    @Bean
    public JpaVendorAdapter createVendorAdapterProd() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.valueOf(env.getProperty("jdbc.dbtype")));
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Profile({"prod", "!prod & !dev"})
    @Bean
    public DataSource getDataSourceProd() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(env.getProperty("jdbc.url"));
        ds.setPassword(env.getProperty("jdbc.pass"));
        ds.setUsername(env.getProperty("jdbc.user"));
        ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        return ds;
    }

    @Profile("dev")
    @Bean
    public JpaVendorAdapter createVendorAdapterDev() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Profile("dev")
    @Bean
    public DataSource getDataSourceDev() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:h2:mem:myDb");
        ds.setPassword("");
        ds.setUsername("sa");
        ds.setDriverClassName("org.h2.Driver");
        return ds;
    }
}
