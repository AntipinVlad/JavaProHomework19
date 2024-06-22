package org.example.repository.dao;

import org.example.interfaces.Repository;
import org.example.repository.HibernateUtil;
import org.example.entity.Game;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class GameRepository implements Repository {
    @Override
    public void saveGame(Game game) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(game);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public void deleteGame(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Game game = session.get(Game.class, id);
            if (game != null) {
                session.delete(game);
                transaction.commit();
            }
        }
    }

    @Override
    public Game getGameByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Game WHERE name = :name", Game.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }

    @Override
    public List<Game> filterGamesByPrice(double minPrice, double maxPrice) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Game WHERE cost BETWEEN :minPrice AND :maxPrice", Game.class)
                    .setParameter("minPrice", minPrice)
                    .setParameter("maxPrice", maxPrice)
                    .list();
        }
    }

    @Override
    public List<Game> getAllGamesSortedByCreationDate() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Game ORDER BY creationDate DESC", Game.class).list();
        }
    }

    @Override
    public List<Game> getAllGames() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Game", Game.class).list();
        }
    }
}
