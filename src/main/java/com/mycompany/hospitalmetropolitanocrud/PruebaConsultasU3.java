package com.mycompany.hospitalmetropolitanocrud;

import com.mycompany.hospitalmetropolitanocrud.dao.CitaMedicaDAO;
import com.mycompany.hospitalmetropolitanocrud.modelo.CitaMedica;
import com.mycompany.hospitalmetropolitanocrud.modelo.Diagnostico;
import java.util.List;

public class PruebaConsultasU3 {

    public static void main(String[] args) {
        CitaMedicaDAO dao = new CitaMedicaDAO();

        System.out.println("=========================================================");
        System.out.println("1. CONSULTA JPQL: Citas por Estado 'Confirmada' (Paginación: Pág 1, Tam 3)");
        System.out.println("=========================================================");
        List<CitaMedica> citasPorEstado = dao.consultarCitasPorEstadoPaginado("Confirmada", 1, 3);
        for (CitaMedica c : citasPorEstado) {
            System.out.println("ID: " + c.getId() + " | Fecha: " + c.getFecha() + " " + c.getHora()
                    + " | Paciente: " + c.getPaciente().getNombre() + " " + c.getPaciente().getApellido()
                    + " | Estado: " + c.getEstado());
        }

        System.out.println("\n=========================================================");
        System.out.println("2. CONSULTA JPQL: Citas por Departamento 'Medicina General' (Paginación: Pág 1, Tam 3)");
        System.out.println("=========================================================");
        List<CitaMedica> citasPorDepto = dao.consultarCitasPorDepartamentoPaginado("Medicina General", 1, 3);
        for (CitaMedica c : citasPorDepto) {
            System.out.println("ID: " + c.getId() + " | Paciente: " + c.getPaciente().getApellido() + ", " + c.getPaciente().getNombre()
                    + " | Médico: " + c.getMedico().getNombre() + " | Fecha: " + c.getFecha());
        }

        System.out.println("\n=========================================================");
        System.out.println("3. CONSULTA JPQL: Diagnósticos por Cédula (Paginación: Pág 1, Tam 3)");
        System.out.println("=========================================================");
        String cedulaPrueba = "1712345678"; 
        List<Diagnostico> diagnosticos = dao.consultarDiagnosticosPorPacientePaginado(cedulaPrueba, 1, 3);
        for (Diagnostico d : diagnosticos) {
            System.out.println("ID Diag: " + d.getId() + " | Diagnóstico: " + d.getDescripcion()
                    + " | Receta: " + d.getReceta() + " | Cita ID: " + d.getCitaMedica().getId());
        }
    }
}