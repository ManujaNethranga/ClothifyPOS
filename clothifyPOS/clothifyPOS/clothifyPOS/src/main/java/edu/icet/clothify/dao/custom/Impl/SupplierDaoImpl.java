package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.SupplierDao;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity entity) {
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
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)AS row_count FROM supplierdetails");
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
        List<SupplierEntity> list;
        try{
            ts = session.beginTransaction();
            list  = session.createQuery("SELECT a FROM SupplierEntity a", SupplierEntity.class).getResultList();
            ts.commit();
            SupplierEntity temp = list.get(list.size()-1);
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
    public SupplierEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        SupplierEntity supplierEntity = (SupplierEntity) session.get(SupplierEntity.class,id.toUpperCase());
        session.getTransaction().commit();
        session.close();
        return supplierEntity;
    }

    @Override
    public Boolean update(SupplierEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<SupplierEntity> getAllSuppliers() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<SupplierEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM SupplierEntity a",SupplierEntity.class).getResultList();
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
