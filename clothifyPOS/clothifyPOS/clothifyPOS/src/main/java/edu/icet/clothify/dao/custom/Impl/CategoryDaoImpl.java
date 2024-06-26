package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.CategoryDao;
import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public boolean save(CategoryEntity entity) {
        return false;
    }

    @Override
    public int empCount() {
        Session session = HibernateUtil.getSession();
        AtomicInteger count = new AtomicInteger();
        session.doWork(connection -> {
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)AS row_count FROM categorydetails");
                resultSet.next();
                count.set(resultSet.getInt("row_count"));
            }catch(SQLSyntaxErrorException e) {
                count.set(0);
                throw e;
            }
        });
        return count.get();
    }

    @Override
    public String getLastId() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        String lastId;
        List<CategoryEntity> list;
        try{
            ts = session.beginTransaction();
            list  = session.createQuery("SELECT a FROM CategoryEntity a", CategoryEntity.class).getResultList();
            ts.commit();
            CategoryEntity temp = list.get(list.size()-1);
            lastId = temp.getId();
        }catch(Exception e){
            if(ts!=null)ts.rollback();
            throw e;
        }finally {
            session.close();
        }
        return lastId;
    }
}
