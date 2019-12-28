/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author _Nprime496_
 */
public class DbManager {
    
    public static boolean addEmployee(int idEm, String nom, String user, String login, String typeEm)
    {
        int empId=0;
        Session session=HibernateUtil.getSessionFactory().openSession();
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
            session.close();
        }
        return empId>0;
    }
}
