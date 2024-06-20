package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.EmployeeDao;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.util.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity entity) {
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
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)AS row_count FROM employeemanagement");
                resultSet.next();
                count.set(resultSet.getInt("row_count"));
            }
        });
        return count.get();

    }

    @Override
    public String getLastEmployeeId() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        String lastId;
        List<EmployeeEntity> list;
        try{
            ts = session.beginTransaction();
            list  = session.createQuery("SELECT a FROM EmployeeEntity a", EmployeeEntity.class).getResultList();
            ts.commit();
            EmployeeEntity temp = list.get(list.size()-1);
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
    public EmployeeEntity getById(String id) {

        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        EmployeeEntity employeeEntity = (EmployeeEntity) session.get(EmployeeEntity.class,id.toUpperCase());
        session.getTransaction().commit();
        session.close();
        return employeeEntity;
    }

    @Override
    public Boolean updateEmployee(EmployeeEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<EmployeeEntity> getAllEmployeeDetails() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<EmployeeEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM EmployeeEntity a",EmployeeEntity.class).getResultList();
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
