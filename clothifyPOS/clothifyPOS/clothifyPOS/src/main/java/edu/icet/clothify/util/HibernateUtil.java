package edu.icet.clothify.util;


import edu.icet.clothify.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory session = createSession();
    private static Transaction singletonTransaction;
    private static Session singletonSession;

    private static SessionFactory createSession() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(UserEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(OrderDetailsEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(SubCategoryEntity.class)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(StockUpdateDetailEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metadata.getSessionFactoryBuilder().build();
    }
    public static Session getSession(){
        return session.openSession();
    }

    public static Session getSingletonSession(){

        if(singletonSession == null || !singletonSession.isOpen()){
            singletonSession = getSession();
        }
        return singletonSession;
    }

    public static void singletonSessionClose(){
        singletonSession.close();
    }

    public static void singletonBegin(){
        singletonTransaction = singletonSession.beginTransaction();
    }

    public static void singletonCommit(){
        singletonTransaction.commit();
    }

    public static void singletonRollback(){
        singletonTransaction.rollback();
    }
}
