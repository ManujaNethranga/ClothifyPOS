package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.StockDao;
import edu.icet.clothify.entity.StockUpdateDetailEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StockDaoImpl implements StockDao {

    @Override
    public boolean save(StockUpdateDetailEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<StockUpdateDetailEntity> getAllStockDetails() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<StockUpdateDetailEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM StockUpdateDetailEntity a",StockUpdateDetailEntity.class).getResultList();
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
