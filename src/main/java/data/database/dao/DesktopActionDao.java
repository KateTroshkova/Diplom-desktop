package data.database.dao;

import data.database.HibernateSessionFactoryUtil;
import data.database.entity.DesktopAction;
import javafx.scene.input.KeyCode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DesktopActionDao {

    private static final KeyCode[] actions = KeyCode.values();

    public DesktopActionDao() {
        preFill();
    }

    public List<DesktopAction> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<DesktopAction> keywords = session.createQuery("from DesktopAction", DesktopAction.class).list();
        transaction.commit();
        session.close();
        return keywords;
    }

    public DesktopAction readById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        DesktopAction action = session.get(DesktopAction.class, id);
        transaction.commit();
        session.close();
        return action;
    }

    public DesktopAction readByName(String keycode) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from DesktopAction where desktop_name=:name");
        query.setParameter("name", keycode);
        DesktopAction action = null;
        try {
            action = (DesktopAction) query.uniqueResult();
        } catch (Throwable ignore) {
        }
        transaction.commit();
        session.close();
        return action;
    }

    private void preFill() {
        List<DesktopAction> saved = readAll();
        if (saved.size() > 0) return;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (KeyCode keycode : actions) {
            DesktopAction action = new DesktopAction(keycode.name());
            session.save(action);
        }
        transaction.commit();
        session.close();
    }
}
