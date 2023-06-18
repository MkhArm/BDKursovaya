package org.example.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateManager {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("airport_db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();

        System.out.println("Список всех утренних смен: ");
        entityManager
                .createQuery("SELECT v FROM ShiftEntity v WHERE v.period_day = 'morning' ", ShiftEntity.class).getResultList()
                .forEach(System.out::println);
        System.out.println();
        // endregion

        entityManagerFactory.close();
    }
}
