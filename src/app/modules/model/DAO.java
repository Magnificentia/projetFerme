/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modules.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author _Nprime496_
 */
public class DAO<T> {

    private final static Session session=HibernateUtil.getSessionFactory().openSession();
    private final Class parameterClass;
    public DAO (Class c)
    {
        this.parameterClass=c;
        this.openCurrentSessionwithTransaction();
    }
    
    public static boolean addEmployee(int idEm, String nom, String user, String login, String typeEm)
    {
        int empId=0;
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            Employes emp=new Employes(idEm,nom,user,login,typeEm);
            empId=(Integer)session.save(emp);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("exception");
            System.err.println(e.getMessage());
            if(tx!=null)
            {
                tx.rollback();
            }
        }
        finally
        {
            //session.close();
        }
        return empId>0;
    }
    public static boolean create(Object object)
    {
        int empId=0;
        Transaction tx=null;
        try
        {
            tx=session.beginTransaction();
            empId=(Integer)session.save(object);
            tx.commit();
        }
        catch(Exception e)
        {
            System.out.println("exception");
            System.err.println(e.getMessage());
            if(tx!=null)
            {
                tx.rollback();
            }
        }
        finally
        {
            //session.close();
        }
        return empId>0;
    }
    private Session currentSession;
     
    private Transaction currentTransaction;
 

 
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
     
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
 
    public void persist(Object entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Object entity) {
        getCurrentSession().update(entity);
    }

 
    public Object findById(String id) {
        Object object;
        object = this.parameterClass.cast(getCurrentSession().get(this.parameterClass,id));
        return object; 
    }
 
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<T> findAll(String table) {
        List<T> books = (List<T>) getCurrentSession().createQuery("from "+table).list();
        return books;
    }
 
    public void deleteAll(String table) {
        List<T> entityList = findAll(table);
        for (T entity : entityList) {
            delete(entity);
        }
    }   
}
