package data.database.dao;

import data.database.HibernateSessionFactoryUtil;
import data.database.entity.HotkeyEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotkeyDao {

    private DesktopActionDao desktopDao;
    private MobileActionDao mobileDao;

    public HotkeyDao(){
        desktopDao = new DesktopActionDao();
        mobileDao = new MobileActionDao();
    }

    public List<HotkeyEntity> readAll(){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<HotkeyEntity> keywords = session.createQuery("from HotkeyEntity", HotkeyEntity.class).list();
        transaction.commit();
        session.close();
        return keywords;
    }

    public void deleteByActions(HotkeyEntity hotkey){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(hotkey);
        transaction.commit();
        session.close();
    }

    public void save(HotkeyEntity hotkey){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(hotkey);
        transaction.commit();
        session.close();
    }
}
