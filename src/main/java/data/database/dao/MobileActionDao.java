package data.database.dao;

import data.database.HibernateSessionFactoryUtil;
import data.database.entity.MobileAction;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class MobileActionDao {

    private static final String[] actions = {
            "KEYCODE_BACK",
            "KEYCODE_HOME",
            "KEYCODE_POWER",
            "KEYCODE_VOLUME_UP",
            "KEYCODE_VOLUME_DOWN",
            "ROTATE_90",
            "ROTATE_180",
            "ROTATE_270",
            "SWIPE_UP",
            "SWIPE_DOWN",
            "SWIPE_RIGHT",
            "SWIPE_LEFT"
    };

    public MobileActionDao() {
        preFill();
    }

    public List<MobileAction> readAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<MobileAction> keywords = session.createQuery("from MobileAction", MobileAction.class).list();
        transaction.commit();
        session.close();
        return keywords;
    }

    public MobileAction readById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        MobileAction action = session.get(MobileAction.class, id);
        transaction.commit();
        session.close();
        return action;
    }

    public MobileAction readByName(String keycode) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from MobileAction where mobile_name=:name");
        query.setParameter("name", keycode);
        MobileAction action = null;
        try {
            action = (MobileAction) query.uniqueResult();
        } catch (Throwable ignore) { }
        transaction.commit();
        session.close();
        return action;
    }

    private void preFill() {
        List<MobileAction> saved = readAll();
        if (saved.size() > 0) return;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (String keycode : actions) {
            MobileAction action = new MobileAction(keycode);
            session.save(action);
        }
        transaction.commit();
        session.close();
    }
}
