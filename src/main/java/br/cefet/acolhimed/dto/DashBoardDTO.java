package br.cefet.acolhimed.dto;

import lombok.Data;

@Data 
public class DashBoardDTO {

    private long totalMedicos;
    private long totalEspecialidades;
    private long totalconsultasHoje;
    private long totalPacientesAtendidos;
    private ConsultaResponseDTO consultaEmAndamento;

    public DashBoardDTO(DashBoardDTO dashBoard){
        this.totalMedicos = dashBoard.getTotalMedicos();
        this.totalEspecialidades = dashBoard.getTotalEspecialidades();
        this.totalconsultasHoje = dashBoard.getTotalconsultasHoje();
        this.totalPacientesAtendidos = dashBoard.getTotalPacientesAtendidos();
        this.consultaEmAndamento = dashBoard.getConsultaEmAndamento();
    }

    public DashBoardDTO(){
        
    }

}