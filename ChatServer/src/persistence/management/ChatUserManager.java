package persistence.management;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import persistence.models.ChatUser;

import java.util.List;

public class ChatUserManager implements IdQueriable {

    private final SessionFactory factory;

    public ChatUserManager() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void addChatUser(ChatUser user) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            ChatUser chatUser = (ChatUser)session.merge(user);
            session.saveOrUpdate(chatUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public ChatUser getById(Integer id) {
        Session session = factory.openSession();
        ChatUser user = null;

        try {
            user = (ChatUser)session.get(ChatUser.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public void listChatUsers() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            List chatUsers = session.createQuery("FROM ChatUser").list();

            for (Object user : chatUsers) {
                ChatUser chatUser = (ChatUser) user;
                System.out.println(chatUser.toString());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateChatUser(ChatUser user) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ChatUser chatUser = (ChatUser)session.get(ChatUser.class, user.getId());
            ChatUser.updateAttributes(chatUser, user);

            session.update(chatUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteChatUser(ChatUser user) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ChatUser chatUser = (ChatUser)session.get(ChatUser.class, user.getId());
            session.delete(chatUser);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
