package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.OrderDetailsDao;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetailsEntity entity) {
        Session session = HibernateUtil.getSingletonSession();
        session.persist(entity);
        return true;
    }

    @Override
    public List<OrderDetailsEntity> getAllDetails() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<OrderDetailsEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM OrderDetailsEntity a",OrderDetailsEntity.class).getResultList();
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
