package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.EmployeeDao;
import edu.icet.clothify.dao.custom.UserDao;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.entity.UserEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public int getUserCount() {
        Session session = HibernateUtil.getSession();
        AtomicInteger count = new AtomicInteger();
        session.doWork(connection -> {
            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(*)AS row_count FROM UserDetail");
                resultSet.next();
                count.set(resultSet.getInt("row_count"));
            }catch(SQLSyntaxErrorException e){
                count.set(0);
                throw e;
            }
        });
        return count.get();
    }

    @Override
    public String lastUserId() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        String lastId;
        List<UserEntity> list;
        try{
            ts = session.beginTransaction();
            list  = session.createQuery("SELECT a FROM UserEntity a", UserEntity.class).getResultList();
            ts.commit();
            UserEntity temp = list.get(list.size()-1);
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
    public List<UserEntity> getAllUserDetails() {
        Session session = HibernateUtil.getSession();
        Transaction ts = null;
        List<UserEntity> list;
        try{
            ts = session.beginTransaction();
            list = session.createQuery("SELECT a FROM UserEntity a",UserEntity.class).getResultList();
            ts.commit();
        }catch(Exception e){
            if(ts!=null)ts.rollback();
            return null;
        }finally {
            session.close();
        }
        return list;
    }

    @Override
    public Boolean update(UserEntity entity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }


}
