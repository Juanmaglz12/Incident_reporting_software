package org.mcnz.jpa.methods;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.mcnz.jpa.models.Incident;
import org.mcnz.jpa.models.users.SpecialistUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ModuloEspecialista {

    public static SpecialistUser moduloEspecialista(SpecialistUser user) {
        System.out.println("Elija una opción");
        System.out.println("1. Ver mis incidentes");
        System.out.println("2. Modificar incidente");
        System.out.println("3. Volver");
        System.out.println("4. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1" -> verMisIncidentes(user);
            case "2" -> ModuloEspecialista.modificarIncidente(user);
            case "3" -> MenuMethods.menu();
            case "4" -> {
            }
            default -> {
                System.out.println("Opción no válida");
                moduloEspecialista(user);
            }
        }
        return user;
    }

    public static void modificarIncidente(SpecialistUser user) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        String idIncident = null;

        try {
            entityManager.getTransaction().begin();

            System.out.println("Ingresar el ID del incidente que desea modificar");

            idIncident = sc.nextLine();
            String jpql = "SELECT DISTINCT i FROM Incident i JOIN i.specialistAssigned s WHERE s.userName = :user AND i.idIncident = :idIncidente";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            query.setParameter("user", user.getUserName());
            query.setParameter("idIncidente", Long.valueOf(idIncident));

            Incident incidente = query.getSingleResult();

            System.out.println("Id: " + incidente.getIdIncident());
            System.out.println("Cliente: " + incidente.getClient().getBusinessName());
            System.out.println("Especialidad: " + incidente.getOfferedService().getOfferedServiceName());
            System.out.println("Fecha de incidente: " + incidente.getIncidentDate());
            System.out.println("Fecha potencial de resolución: "+ incidente.getResolutionDate());
            System.out.println("Tipo de problema: " + incidente.getTypeProblem());
            System.out.println("Descripcion del problema: " + incidente.getDescriptionProblem());
            System.out.println("Consideraciones: " + incidente.getSpecialistConsiderations());


            System.out.println("Ingrese el dato que desea modificar");
            System.out.println("1. Fecha potencial de resolución: "+ incidente.getResolutionDate());
            System.out.println("2. Modificar consideraciones "+ incidente.getSpecialistConsiderations());
            System.out.println("3. Volver");
            String opcion = sc.nextLine();

            switch (opcion){
                case "1" -> {
                    System.out.println("Ingrese el día (dd)");
                    int dia = sc.nextInt();
                    System.out.println("Ingrese el mes (mm)");
                    int mes = sc.nextInt();
                    System.out.println("Ingrese el año (aaaa)");
                    int ano = sc.nextInt();
                    LocalDate fecha = LocalDate.of(ano,mes, dia);
                    incidente.setResolutionDate(fecha);
                }
                case "2" -> {
                    System.out.println("Ingrese la especificación (se sobreescribirá la anterior)");
                    String texto = sc.nextLine();
                    incidente.setSpecialistConsiderations(texto);
                }
                case "3" -> moduloEspecialista(user);
                default -> {
                    System.out.println("Opción no válida");
                    modificarIncidente(user);
                }
            }

            // Cerrar transacción después del bucle
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Manejar la excepción
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }
        moduloEspecialista(user);
    }

    public static void verMisIncidentes(SpecialistUser user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            System.out.println("**************");
            System.out.println("Mis incidentes");
            System.out.println("**************");
//            String jpql = "SELECT DISTINCT s FROM SpecialistUser s JOIN s.offeredServices o WHERE o.offeredServiceName = :nombreEspecialidad";
            String jpql = "SELECT DISTINCT i FROM Incident i JOIN i.specialistAssigned s WHERE s.userName = :user";
            TypedQuery<Incident> query = entityManager.createQuery(jpql, Incident.class);
            query.setParameter("user", user.getUserName());
            List<Incident> incidentes = query.getResultList();

            System.out.println("-----------------------------------------------");
            System.out.println("-------- MIS INCIDENTES PENDIENTES ------------");
            System.out.println("-----------------------------------------------");

            for (Incident incidente : incidentes) {
                if(incidente.getIncidentState().equals("RECIBIDO")){
                    System.out.println("Id: " + incidente.getIdIncident());
                    System.out.println("Cliente: " + incidente.getClient().getBusinessName());
                    System.out.println("Especialidad: " + incidente.getOfferedService().getOfferedServiceName());
                    System.out.println("Fecha de incidente: " + incidente.getIncidentDate());
                    System.out.println("Fecha potencial de resolución: "+ incidente.getResolutionDate());
                    System.out.println("Tipo de problema: " + incidente.getTypeProblem());
                    System.out.println("Descripcion del problema: " + incidente.getDescriptionProblem());
                    System.out.println("Consideraciones: " + incidente.getSpecialistConsiderations());
                    System.out.println("------------------------");
                }else if(incidentes.isEmpty()) {
                    System.out.println("No tenes incidentes sin resolver");
                }

            }
            System.out.println("-----------------------------------------------");
            System.out.println("-------- MIS INCIDENTES RESUELTOS ------------");
            System.out.println("-----------------------------------------------");

            for (Incident incidente : incidentes) {
                if(incidente.getIncidentState().equals("RESUELTO")){

                    System.out.println("Id: " + incidente.getIdIncident());
                    System.out.println("Cliente: " + incidente.getClient().getBusinessName());
                    System.out.println("Especialidad: " + incidente.getOfferedService().getOfferedServiceName());
                    System.out.println("Fecha de incidente: " + incidente.getIncidentDate());
                    System.out.println("Fecha potencial de resolución: "+ incidente.getResolutionDate());
                    System.out.println("Tipo de problema: " + incidente.getTypeProblem());
                    System.out.println("Descripcion del problema: " + incidente.getDescriptionProblem());
                    System.out.println("------------------------");
                }
            }
            // Cerrar transacción después del bucle
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            // Manejar la excepción
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y el EntityManagerFactory
            entityManager.close();
            emf.close();
        }
        moduloEspecialista(user);
    }
}
