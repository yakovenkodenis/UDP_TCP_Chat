package persistence.management;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import persistence.models.ChatMessage;

import java.util.List;

public class ChatMessageManager implements IdQueriable {

    private final SessionFactory factory;

    public ChatMessageManager() {
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

    public void addChatMessage(ChatMessage message) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            ChatMessage chatMessage = (ChatMessage)session.merge(message);
            session.saveOrUpdate(chatMessage);
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
    public Object getById(Integer id) {
        Session session = factory.openSession();
        ChatMessage message = null;

        try {
            message = (ChatMessage)session.get(ChatMessage.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return message;
    }

    public void listChatMessages() {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            List chatMessages = session.createQuery("FROM ChatMessage").list();

            for (Object message : chatMessages) {
                ChatMessage chatMessage = (ChatMessage) message;
                System.out.println(chatMessage.toString());
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

    public void updateChatMessage(ChatMessage message) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ChatMessage chatMessage = (ChatMessage)session.get(ChatMessage.class, message.getId());
            ChatMessage.updateAttributes(chatMessage, message);

            session.update(chatMessage);
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

    public void deleteChatMessage(ChatMessage message) {
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ChatMessage chatMessage = (ChatMessage)session.get(ChatMessage.class, message.getId());
            session.delete(chatMessage);
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
