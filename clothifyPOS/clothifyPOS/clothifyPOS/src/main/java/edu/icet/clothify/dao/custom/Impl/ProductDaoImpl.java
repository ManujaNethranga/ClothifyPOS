package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.ProductDao;
import edu.icet.clothify.dto.tableModels.CartTable;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.util.HibernateUtil;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean save(ProductEntity entity) {
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
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)AS row_count FROM productdetails");
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
        List<ProductEntity> list;
        try{
            ts = session.beginTransaction();
            list  = session.createQuery("SELECT a FROM ProductEntity a", ProductEntity.class).getResultList();
            ts.commit();
            ProductEntity temp = list.get(list.size()-1);
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
    public List<ProductEntity> getAllProducts() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<ProductEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM ProductEntity a",ProductEntity.class).getResultList();
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
    public ProductEntity getById(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        ProductEntity productEntity = (ProductEntity) session.get(ProductEntity.class,id.toUpperCase());
        session.getTransaction().commit();
        session.close();
        return productEntity;
    }

    @Override
    public Boolean update(ProductEntity productEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(productEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public List<ProductEntity> getLowToHigh() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<ProductEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM ProductEntity a ORDER BY stock ASC",ProductEntity.class).getResultList();
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
    public List<ProductEntity> getHighToLow() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<ProductEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM ProductEntity a ORDER BY stock DESC",ProductEntity.class).getResultList();
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
    public void updateStock(ProductEntity product) {
        Session session = HibernateUtil.getSingletonSession();
        session.update(product);

    }


}
