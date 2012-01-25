package org.nvc.ui.common.dao;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;


public class BaseDao
{
    private DataSource dataSource;
    protected JdbcTemplate template;
    
    
    public BaseDao()
    {
        ;
    }

    @Inject
    @Named("dataSource")
    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    
    public DataSource getDataSource()
    {
        return this.dataSource;
    }
    
    @Inject
    @Named("jdbcTemplateMaster")
    public void setJdbcTemplate(JdbcTemplate template)
    {
        this.template = template;
    }
    
    public JdbcTemplate getJdbcTemplate()
    {
        return template;
    }
    
    protected int getLastInsertedId(String tableName)  
    {    
        return template.queryForInt("SELECT last_insert_id() from `"+ tableName +"` LIMIT 1", null, null);   
    }
}

