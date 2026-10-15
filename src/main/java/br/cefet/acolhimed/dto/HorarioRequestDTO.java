package br.cefet.acolhimed.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HorarioRequestDTO {
    
    @NotEmpty
    private List<HorarioResponseDTO> horarios;
}
