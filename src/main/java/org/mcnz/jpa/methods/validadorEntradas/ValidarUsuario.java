package org.mcnz.jpa.methods.validadorEntradas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.mcnz.jpa.methods.*;
import org.mcnz.jpa.models.users.ComercialUser;
import org.mcnz.jpa.models.users.HelpDeskUser;
import org.mcnz.jpa.models.users.RrhhUser;
import org.mcnz.jpa.models.users.SpecialistUser;

import java.util.Scanner;

public class ValidarUsuario {

    public static void validarUsuarioRrhh(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        RrhhUser user = null;


        System.out.println("Ingrese su usuario");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM RrhhUser c WHERE c.userName = :userName AND alta = true";
            TypedQuery<RrhhUser> query = entityManager.createQuery(jpql, RrhhUser.class);
            query.setParameter("userName", userName.toUpperCase());

            user = query.getSingleResult();

            ModuloRRHH.moduloRRHH();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Usuario incorrecto");
            MenuMethods.menu();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
    }

    public static SpecialistUser validarUsuarioEspecialista(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        SpecialistUser user = null;


        System.out.println("Ingrese su usuario");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM SpecialistUser c WHERE c.userName = :userName AND alta = true";
            TypedQuery<SpecialistUser> query = entityManager.createQuery(jpql, SpecialistUser.class);
            query.setParameter("userName", userName.toUpperCase());

            user = query.getSingleResult();

            ModuloEspecialista.moduloEspecialista(user);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Usuario incorrecto");
            MenuMethods.menu();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
        return user;
    }

    public static void validarUsuarioComercial(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        ComercialUser user = null;


        System.out.println("Ingrese su usuario");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM ComercialUser c WHERE c.userName = :userName AND alta = true";
            TypedQuery<ComercialUser> query = entityManager.createQuery(jpql, ComercialUser.class);
            query.setParameter("userName", userName.toUpperCase());

            user = query.getSingleResult();

            ModuloComercial.moduloComercial();

        } catch (Exception e) {
            System.out.println(e);
            MenuMethods.menu();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
    }

    public static void validarUsuarioMesaDeAyuda(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        HelpDeskUser user = null;


        System.out.println("Ingrese su usuario");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();

        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT c FROM HelpDeskUser c WHERE c.userName = :userName AND alta = true";
            TypedQuery<HelpDeskUser> query = entityManager.createQuery(jpql, HelpDeskUser.class);
            query.setParameter("userName", userName.toUpperCase());

            user = query.getSingleResult();

            ModuloMesaDeAyuda.moduloMesaDeAyuda();

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Usuario incorrecto");
            MenuMethods.menu();
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
            emf.close();
        }
    }


}
