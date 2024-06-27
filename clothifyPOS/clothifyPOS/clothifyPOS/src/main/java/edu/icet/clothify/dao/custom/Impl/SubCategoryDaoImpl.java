package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.SubCategoryDao;
import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.entity.SubCategoryEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SubCategoryDaoImpl implements SubCategoryDao {
    @Override
    public boolean save(SubCategoryEntity entity) {
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
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)AS row_count FROM subcategorydetail");
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
    public String lastId() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        String lastId;
        List<SubCategoryEntity> list;
        try{
            ts = session.beginTransaction();
            list  = session.createQuery("SELECT a FROM SubCategoryEntity a", SubCategoryEntity.class).getResultList();
            ts.commit();
            SubCategoryEntity temp = list.get(list.size()-1);
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
    public List<SubCategoryEntity> getAllSubCategoryDetail() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<SubCategoryEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM SubCategoryEntity a",SubCategoryEntity.class).getResultList();
            ts.commit();
        }catch(Exception e){
            if(ts!=null)ts.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public SubCategoryEntity getById(String text) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SubCategoryEntity subCategory = (SubCategoryEntity) session.get(SubCategoryEntity.class,text.toUpperCase());
        session.getTransaction().commit();
        session.close();
        return subCategory;
    }

    @Override
    public Boolean update(SubCategoryEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


}
