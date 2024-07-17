package edu.icet.clothify.dao.custom.Impl;

import edu.icet.clothify.dao.custom.OrderDetailsDao;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

public class OrderDetailsDaoImpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetailsEntity entity) {
        Session session = HibernateUtil.getSingletonSession();
        session.persist(entity);
        return true;
    }
}
