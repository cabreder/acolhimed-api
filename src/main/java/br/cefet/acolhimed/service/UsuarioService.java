package br.cefet.acolhimed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.cefet.acolhimed.dto.AutenticacaoResponseDTO;
import br.cefet.acolhimed.dto.ConsultaResponseDTO;
import br.cefet.acolhimed.dto.DashBoardDTO;
import br.cefet.acolhimed.dto.LoginRequestDTO;
import br.cefet.acolhimed.dto.MedicoResponseDTO;
import br.cefet.acolhimed.dto.PacienteRequestDTO;
import br.cefet.acolhimed.dto.UsuarioResponseDTO;
import br.cefet.acolhimed.entity.Medico;
import br.cefet.acolhimed.entity.Paciente;
import br.cefet.acolhimed.entity.Usuario;
import br.cefet.acolhimed.enums.TipoUsuario;
import br.cefet.acolhimed.exception.ResourceNotFoundException;
import br.cefet.acolhimed.repository.AvaliacaoRepository;
import br.cefet.acolhimed.repository.MedicoRepository;
import br.cefet.acolhimed.repository.PacienteRepository;
import br.cefet.acolhimed.repository.UsuarioRepository;
import br.cefet.acolhimed.security.JwtService;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public DashBoardDTO getDashBoard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String idUsuario = user.getUsername();

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuário não encontrado. Id: " + idUsuario));

        DashBoardDTO dashBoard = new DashBoardDTO();

        if (usuario.getTipoUsuario() == TipoUsuario.medico) {
            Medico medico = medicoRepository.findById(idUsuario)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Médico não encontrado. Id: " + idUsuario));

            dashBoard.setTotalconsultasHoje(consultaService.contarConsultasDoDia(medico));

            dashBoard.setTotalPacientesAtendidos(
                    consultaService.contarPacientesAtendidos(medico));

            dashBoard.setConsultaEmAndamento(
                    consultaService.buscarConsultaEmAndamento(medico.getTipoUsuario()));

        } else {
            dashBoard.setTotalMedicos(medicoService.contarMedicos());

            dashBoard.setTotalEspecialidades(
                    especialidadeService.contarEspecialidades());

            dashBoard.setConsultaEmAndamento(
                    consultaService.buscarConsultaEmAndamento(usuario.getTipoUsuario()));
        }

        return dashBoard;
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(String id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado. Id: " + id));

        if (usuario instanceof Medico medico) {
            Double media = avaliacaoRepository.calcularMediaAvaliacao(medico.getId());
            return new MedicoResponseDTO(medico, media);
        }

        return new UsuarioResponseDTO(usuario);
    }

    @Transactional(readOnly = true)
    public Object autenticar(LoginRequestDTO credenciais) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credenciais.getEmail(), credenciais.getSenha()));
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("E-mail ou senha incorretos.");
        }

        Usuario usuario = usuarioRepository.findByEmail(credenciais.getEmail())
                .orElseThrow(() -> new RuntimeException("E-mail ou senha incorretos."));

        String token = jwtService.gerarToken(usuario);

        return new AutenticacaoResponseDTO(token);
    }

}
