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
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
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

    @Override
    public CategoryEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        CategoryEntity categoryEntity = (CategoryEntity) session.get(CategoryEntity.class,id.toUpperCase());
        session.getTransaction().commit();
        session.close();
        return categoryEntity;
    }

    @Override
    public Boolean update(CategoryEntity entity) {
        Session session= HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<CategoryEntity> getAllCategories() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<CategoryEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM CategoryEntity a",CategoryEntity.class).getResultList();
            ts.commit();
        }catch(Exception e){
            if(ts!=null)ts.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }
}
