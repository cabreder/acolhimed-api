package br.cefet.acolhimed.service;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.cefet.acolhimed.dto.UsuarioRequestDTO;
import br.cefet.acolhimed.dto.UsuarioResponseDTO;
import br.cefet.acolhimed.entity.Paciente;
import br.cefet.acolhimed.enums.TipoUsuario;
import br.cefet.acolhimed.exception.BusinessException;
import br.cefet.acolhimed.exception.ResourceNotFoundException;
import br.cefet.acolhimed.repository.PacienteRepository;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO inserir(UsuarioRequestDTO dto) {

        if (pacienteRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("Já existe um usuário com esse email.");
        }

        if (!"paciente".equalsIgnoreCase(dto.getTipoUsuario())) {
            throw new BusinessException("Apenas pacientes podem ser cadastrados neste endpoint.");
        }

        Paciente paciente = new Paciente();
        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setSenha(passwordEncoder.encode(dto.getSenha()));
        paciente.setTipoUsuario(TipoUsuario.paciente);

        return new UsuarioResponseDTO(pacienteRepository.save(paciente));
    }

    @Transactional
    public UsuarioResponseDTO atualizar(UsuarioRequestDTO dto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails usuario = (UserDetails) authentication.getPrincipal();

        String pacienteId = usuario.getUsername();

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente nao encontrado. Id: " + pacienteId));

        // Busca se ALGUÉM usa o novo e-mail
        Optional<Paciente> usuarioComMesmoEmail = pacienteRepository.findByEmail(dto.getEmail());

        // Se o e-mail já existe E não pertence ao próprio usuário que está logado, aí
        // barra
        if (usuarioComMesmoEmail.isPresent() && !usuarioComMesmoEmail.get().getId().equals(pacienteId)) {
            throw new BusinessException("Já existe um usuário com esse e-mail.");
        }

        // Busca se alguem usa o novo cpf
        Optional<Paciente> usuarioComMesmoCpf = pacienteRepository.findByCpf(dto.getCpf());

        // Se o cpf já existe E não pertence ao próprio usuário que está logado, aí
        // barra
        if (usuarioComMesmoCpf.isPresent() && !usuarioComMesmoCpf.get().getId().equals(pacienteId)) {
            throw new BusinessException("Já existe um usuário com esse CPF.");
        }

        LocalDate nascimento = dto.getDataNascimento().toLocalDate();

        if (dto.getDataNascimento() == null
                || nascimento.isBefore(LocalDate.of(1900, 1, 1))
                || nascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida.");
        }

        paciente.setNome(dto.getNome());
        paciente.setEmail(dto.getEmail());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setCpf(dto.getCpf());

        // senha - atualizar apenas se providenciado
        String senha = dto.getSenha();
        if (senha != null && !senha.trim().isEmpty()) {
            paciente.setSenha(passwordEncoder.encode(senha));
        }

        return new UsuarioResponseDTO(pacienteRepository.save(paciente));
    }
}
