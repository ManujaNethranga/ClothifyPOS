package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.OrderDao;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity entity) {
        Session session = HibernateUtil.getSingletonSession();
        HibernateUtil.singletonBegin();
        session.persist(entity);
        return true;
    }

    @Override
    public int empCount() {
        Session session = HibernateUtil.getSession();
        AtomicInteger count = new AtomicInteger();
        session.doWork(connection -> {
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)AS row_count FROM orderDetails");
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
    public String lastEmpId() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        String lastId;
        List<OrderEntity> list;
        try{
            ts = session.beginTransaction();
            list  = session.createQuery("SELECT a FROM OrderEntity a", OrderEntity.class).getResultList();
            ts.commit();
            OrderEntity temp = list.get(list.size()-1);
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
    public OrderEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        OrderEntity orderEntity = (OrderEntity) session.get(OrderEntity.class,id.toUpperCase());
        session.getTransaction().commit();
        session.close();
        return orderEntity;
    }

    @Override
    public List<OrderEntity> getAllOrders() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<OrderEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM OrderEntity a",OrderEntity.class).getResultList();
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
    public Boolean update(OrderEntity byId) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(byId);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
