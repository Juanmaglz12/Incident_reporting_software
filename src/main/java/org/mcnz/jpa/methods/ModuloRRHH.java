package org.mcnz.jpa.methods;

import jakarta.persistence.*;
import org.mcnz.jpa.methods.validadorEntradas.ValidadorEntradas;
import org.mcnz.jpa.models.OfferedService;
import org.mcnz.jpa.models.users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ModuloRRHH {


    public static void moduloRRHH() {
        System.out.println("Elija una opcion");
        System.out.println("1. Ver empleados");
        System.out.println("2. Alta empleado");
        System.out.println("3. Modificar empleado");
        System.out.println("4. Baja empleados");
        System.out.println("5. Volver");
        System.out.println("6. Salir");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1":
                System.out.println("Ver empleados");
                ModuloRRHH.verTodosLosEmpleados();

                break;
            case "2":
                System.out.println("Alta empleado");
                ModuloRRHH.altaEmpleado();
                break;
            case "3":
                System.out.println("Modificar empleado");
                ModuloRRHH.modificarEmpleado();
                break;
            case "4":
                System.out.println("Baja empleado");
                ModuloRRHH.bajaEmpleado();
                break;
            case "5":
                MenuMethods.menu();
                break;
            case "6":
                break;
            default:
                System.out.println("Opción no válida");
                ModuloRRHH.moduloRRHH();
        }
    }

    public static void altaEmpleado() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el rol del nuevo usuario");
        System.out.println("1. RRHH");
        System.out.println("2. Comercial");
        System.out.println("3. Mesa de ayuda");
        System.out.println("4. Especialista");
        System.out.println("5. Volver");
        String rol = sc.nextLine();

        switch (rol) {
            case "1":
                ModuloRRHH.altaRrhhUser();
                break;
            case "2":
                ModuloRRHH.altaComercialUser();
                break;
            case "3":
                ModuloRRHH.altaMesaDeAyudaUser();
                break;
            case "4":
                ModuloRRHH.altaEspecialistaUser();
                break;
            case "5":
                ModuloRRHH.moduloRRHH();
                break;
            default:
                System.out.println("opcion no válida");
                ModuloRRHH.altaEmpleado();
                break;
        }

        System.out.println("Alta exitosa");
        ModuloRRHH.moduloRRHH();

    }

    private static void altaRrhhUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo usuario o presione 1 para cancelar");
        String nombre = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el apellido del nuevo usuario o presione 1 para cancelar");
        String apellido = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el userName del nuevo usuario o presione 1 para cancelar");
        String userName = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el email del nuevo usuario o presione 1 para cancelar");
        String email = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        RrhhUser user = new RrhhUser();
        user.setName(nombre);
        user.setLastName(apellido);
        user.setUserName(userName);
        user.setEmail(email);
        user.setAlta(true);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    private static void altaComercialUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del nuevo usuario o presione 1 para cancelar");
        String nombre = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el apellido del nuevo usuario o presione 1 para cancelar");
        String apellido = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el userName del nuevo usuario o presione 1 para cancelar");
        String userName = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el email del nuevo usuario o presione 1 para cancelar");
        String email = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        ComercialUser user = new ComercialUser();
        user.setName(nombre);
        user.setLastName(apellido);
        user.setUserName(userName);
        user.setEmail(email);
        user.setAlta(true);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    private static void altaMesaDeAyudaUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo usuario o presione 1 para cancelar");
        String nombre = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el apellido del nuevo usuario o presione 1 para cancelar");
        String apellido = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el userName del nuevo usuario o presione 1 para cancelar");
        String userName = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el email del nuevo usuario o presione 1 para cancelar");
        String email = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        HelpDeskUser user = new HelpDeskUser();
        user.setName(nombre);
        user.setLastName(apellido);
        user.setUserName(userName);
        user.setEmail(email);
        user.setAlta(true);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }
    private static void altaEspecialistaUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del nuevo usuario o presione 1 para cancelar");
        String nombre = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el apellido del nuevo usuario o presione 1 para cancelar");
        String apellido = ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el userName del nuevo usuario o presione 1 para cancelar");
        String userName = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);
        System.out.println("Ingrese el email del nuevo usuario o presione 1 para cancelar");
        String email = ValidadorEntradas.validarEntrada2( 40, ModuloRRHH::moduloRRHH);


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        String opcion = "";
        List<OfferedService> servicios = new ArrayList<>();

        while (!opcion.equals("6")) {
            System.out.println("Ingrese las especialidades y presione 6 para confirmar");
            System.out.println("1. SAP");
            System.out.println("2. Tango");
            System.out.println("3. Windows");
            System.out.println("4. MacOs");
            System.out.println("5. Linux Ubuntu");
            System.out.println("6. Confirmar");

            opcion = scanner.nextLine();

            switch (opcion){
                case "1": opcion = "SAP";
                    break;
                case "2": opcion = "TANGO";
                    break;
                case "3": opcion = "WINDOWS";
                    break;
                case "4": opcion = "MACOS";
                    break;
                case "5": opcion = "LINUX UBUNTU";
                    break;
                case "6": opcion = "6";
                    break;
            }

            if (!opcion.equals("6")) {
                String jpql = "SELECT u FROM OfferedService u WHERE offeredServiceName = :offServName";
                TypedQuery<OfferedService> query = entityManager.createQuery(jpql, OfferedService.class);
                query.setParameter("offServName", opcion);
                List<OfferedService> serviciosEncontrados = query.getResultList();

                if (!serviciosEncontrados.isEmpty()) {
                    servicios.addAll(serviciosEncontrados);
                } else {
                    System.out.println("Especialidad no encontrada. Vuelva a intentarlo.");
                }
            }
        }

        SpecialistUser user = new SpecialistUser();
        user.setName(nombre);
        user.setLastName(apellido);
        user.setUserName(userName);
        user.setEmail(email);
        user.setAlta(true);
        user.setOfferedServices(servicios);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public static void modificarEmpleado() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Seleccione el userName del empleado a modificar o presione 1 para volver.");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        if (!opcion.equals("1")) {
            try {
                entityManager.getTransaction().begin();

                String jpql = "SELECT u FROM RrhhUser u WHERE userName = :userName";
                TypedQuery<RrhhUser> query = entityManager.createQuery(jpql, RrhhUser.class);
                query.setParameter("userName", opcion);
                RrhhUser resultado = query.getSingleResult();

                System.out.println("--------------------");
                System.out.println("Id: " + resultado.getIdUser());
                System.out.println("Nombre: " + resultado.getName());
                System.out.println("Apellido: " + resultado.getUserName());
                System.out.println("Nombre de usuario: " + resultado.getUserName());
                System.out.println("email: " + resultado.getEmail());
                System.out.println("Estado: " + resultado.getAlta());
                System.out.println("--------------------");

                System.out.println("Ingrese el dato a modificar");
                System.out.println("1. Nombre");
                System.out.println("2. Apellido");
                System.out.println("3. UserName");
                System.out.println("4. email");
                System.out.println("5. Reactivar usuario");
                System.out.println("6. Volver");

                String opcionDatoAModificar = sc.nextLine();

                switch (opcionDatoAModificar) {
                    case "1" -> {
                        System.out.println("Ingrese el nuevo nombre");
                        resultado.setName(ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH));
                    }
                    case "2" -> {
                        System.out.println("Ingrese el nuevo apellido");
                        resultado.setLastName(ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH));
                    }
                    case "3" -> {
                        System.out.println("Ingrese el nuevo userName");
                        resultado.setUserName(ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH));
                    }
                    case "4" -> {
                        System.out.println("Ingrese el nuevo email");
                        resultado.setEmail(ValidadorEntradas.validarEntrada2(40, ModuloRRHH::moduloRRHH));
                    }
                    case "5" -> {
                        if(!resultado.getAlta()){
                            resultado.setAlta(true);
                            System.out.println("El empleado se reactivó correctamente");
                        }else System.out.println("El emplado está actualmente activo");
                    }
                    case "6" -> ModuloRRHH.moduloRRHH();
                    default -> {
                        System.out.println("opcion no válida");
                        ModuloRRHH.modificarEmpleado();
                    }
                }entityManager.getTransaction().commit();
            } catch (NoResultException e) {
                System.out.println("No se encontró el usuario");
                ModuloRRHH.modificarEmpleado();
            } finally {
                entityManager.close();
                emf.close();
            }
        } else {
            ModuloRRHH.moduloRRHH();
        }
        ModuloRRHH.moduloRRHH();
    }


    public static void bajaEmpleado() {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        System.out.println("Seleccione el userName del empleado a dar de baja o presione 1 para salir.");

        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        if (!opcion.equals("1")) {
            try {
                entityManager.getTransaction().begin();

                String jpql = "SELECT u FROM RrhhUser u WHERE userName = :userName";
                TypedQuery<RrhhUser> query = entityManager.createQuery(jpql, RrhhUser.class);
                query.setParameter("userName", opcion);
                RrhhUser resultado = query.getSingleResult();

                System.out.println("--------------------");
                System.out.println("Id: " + resultado.getIdUser());
                System.out.println("Nombre: " + resultado.getName());
                System.out.println("Apellido: " + resultado.getUserName());
                System.out.println("Nombre de usuario: " + resultado.getUserName());
                System.out.println("email: " + resultado.getEmail());
                System.out.println("Estado: " + resultado.getAlta());
                System.out.println("--------------------");

                if (resultado.getAlta()){
                    System.out.println("¿Está seguro que desea dar de baja este empleado?");
                    System.out.println("1. Si");
                    System.out.println("2. Volver");

                    String opcionDatoAModificar = sc.nextLine();

                    switch (opcionDatoAModificar) {
                        case "1" -> {
                            resultado.setAlta(false);
                            System.out.println("Empleado dado de baja exitosamente!");
                        }
                        case "2" -> {
                            ModuloRRHH.moduloRRHH();
                        }
                        default -> {
                            System.out.println("opcion no válida");
                            ModuloRRHH.modificarEmpleado();
                        }
                    }
               }
                entityManager.getTransaction().commit();
                System.out.println("El empleado ya está dado de baja");
            } catch (NoResultException e) {
                System.out.println("No se encontró el usuario");
                ModuloRRHH.bajaEmpleado();
            } finally {
                entityManager.close();
                emf.close();
            }
        } else {
            ModuloRRHH.moduloRRHH();
        }
        ModuloRRHH.moduloRRHH();
    }

    public static void validarUserRRHH() {
        ModuloRRHH.moduloRRHH();
    }

    public static void verTodosLosEmpleados() {
        ModuloRRHH.verEmpleadosRRHH();
        ModuloRRHH.verEmpleadosComerciales();
        ModuloRRHH.verEmpleadosMesaDeAyuda();
        ModuloRRHH.verEmpleadosEspecialistas();

        ModuloRRHH.moduloRRHH();
    }

    private static void verEmpleadosEspecialistas() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados Especialistas");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM SpecialistUser e";
            TypedQuery<SpecialistUser> query = entityManager.createQuery(jpql, SpecialistUser.class);
            List<SpecialistUser> user = query.getResultList();

            // Mostrar la información
            for (SpecialistUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("--------------------");
            }

            // Commit de la transacción
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
    }

    private static void verEmpleadosMesaDeAyuda() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados Mesa de ayuda");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM HelpDeskUser e";
            TypedQuery<HelpDeskUser> query = entityManager.createQuery(jpql, HelpDeskUser.class);
            List<HelpDeskUser> user = query.getResultList();

            // Mostrar la información
            for (HelpDeskUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("--------------------");
            }

            // Commit de la transacción
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


    }

    private static void verEmpleadosComerciales() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados Comerciales");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM ComercialUser e";
            TypedQuery<ComercialUser> query = entityManager.createQuery(jpql, ComercialUser.class);
            List<ComercialUser> user = query.getResultList();

            // Mostrar la información
            for (ComercialUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("--------------------");
            }

            // Commit de la transacción
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


    }


    private static void verEmpleadosRRHH() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        System.out.println("**************");
        System.out.println("Empleados RRHH");
        System.out.println("**************");

        try{
            String jpql = "SELECT e FROM RrhhUser e";
            TypedQuery<RrhhUser> query = entityManager.createQuery(jpql, RrhhUser.class);
            List<RrhhUser> user = query.getResultList();

            // Mostrar la información
            for (RrhhUser empleado : user) {
                System.out.println("--------------------");
                System.out.println("Id: " + empleado.getIdUser());
                System.out.println("Nombre: " + empleado.getName());
                System.out.println("Apellido: " + empleado.getUserName());
                System.out.println("Nombre de usuario: " + empleado.getUserName());
                System.out.println("email: " + empleado.getEmail());
                System.out.println("Estado: " + empleado.getAlta());
                System.out.println("--------------------");
            }

            // Commit de la transacción
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

    }
}


