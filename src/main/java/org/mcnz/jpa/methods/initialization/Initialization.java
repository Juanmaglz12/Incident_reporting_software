package org.mcnz.jpa.methods.initialization;


public class Initialization {

    public static void initialization(){

        //Metodos para crear los registros prestablecidos que se indicaron en el proyecto

      /*
        Method.createRol("RRHH");
        Method.createRol("Comercial");
        Method.createRol("HelpDesk");
        Method.createRol("Specialist");




        Method.createIncidentState("Recibido");
        Method.createIncidentState("En revisión");
        Method.createIncidentState("Solucionado");
        Method.createIncidentState("Anulado");



        Method.createOfferedService("SAP");
        Method.createOfferedService("Tango");
        Method.createOfferedService("Linux Ubuntu");
        Method.createOfferedService("Windows");
        Method.createOfferedService("MacOs");

*/
        Method.createClient("Cliente 1", "11111111","cliente1@gmail.com");
           /*  Method.createClient("Rulemanes SA", "22.222.222","rule@rule");


        Method.createComercialUser("jperez","Juan","Perez","jperez@cons");
        Method.createComercialUser("llopez","Lucia","Lopez","llopez@cons");

        Method.createHelpDeskUser("rromero","Ramon","Romero","rromero@cons");
        Method.createHelpDeskUser("mmarin","Mariana","Marin","mmarin@cons");

        Method.createRrhhUser("fsalvio","Facundo","Salvio","fsalvio@cons");
        Method.createRrhhUser("ddominguez","Damian","Dominguez","ddominguez@cons");




       // Method.createIncident(1L,"Error fatal","Al hacer clic, sale error",5,1L,1L,"Recibido");
       // Method.createIncident(2L,"Error simple","Al hacer clic, error simple",2,2L,2L,"Resuelto");

        /*
        List<Long> offSerId = new ArrayList<>();
        offSerId.add(1L);
        Method.createSpecialistUser("rroca","Raul","Roca","rroca@cons",offSerId);
        Method.createSpecialistUser("hhoyos","Hernan","Hoyos","hhoyos@cons",offSerId);
        */


        /*
        Method.createSpecialistState("Asignado");
        Method.createSpecialistState("Liberado");


        Method.createRrhhUser(new RrhhUser("mbarragan","1234","Marcos","Barragan","mbarragan@cons.com", Method.getUserRolById(1L)));
        Method.createComercialUser(new ComercialUser("bdiaz","1234","Belen","Diaz","bdiaz@cons.com", Method.getUserRolById(2L)));
        Method.createHelpDesklUser(new HelpDeskUser("mlopez","1234","Matias","Lopez","mlopez@cons.com", Method.getUserRolById(3L)));
        Method.createSpecialistUser(new SpecialistUser("aperez","1234","Aldana","Perez","aperez@cons.com", Method.getUserRolById(4L),Method.getStateSpecialistById(1L),Method.getOfferedServiceById(1L)));


        Method.createClient(new Client("Oro Rojo","65447677","ororojo@gmail.com",Method.getOfferedServiceById(1L)));



 */










    }


}
