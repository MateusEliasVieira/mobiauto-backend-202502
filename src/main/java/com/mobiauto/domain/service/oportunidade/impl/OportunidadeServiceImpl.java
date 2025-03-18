package com.mobiauto.domain.service.oportunidade.impl;

import com.mobiauto.api.dto.oportunidade.OportunidadeTransferenciaInputDTO;
import com.mobiauto.domain.enums.RolePerfilUsuario;
import com.mobiauto.domain.enums.StatusOportunidade;
import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.*;
import com.mobiauto.domain.repository.*;
import com.mobiauto.domain.service.oportunidade.OportunidadeService;
import com.mobiauto.utils.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OportunidadeServiceImpl implements OportunidadeService {

    @Autowired
    private OportunidadeRepository repository;

    @Autowired
    private RevendaRepository revendaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private AtendimentoRepository repositoryAtendimento;

    @Autowired
    private UsuarioLogado usuarioLogado;

    @Transactional(readOnly = false)
    @Override
    public void criar(Oportunidade oportunidade) {

        if (oportunidade.getRevenda() == null || oportunidade.getRevenda().getIdRevenda() == null) {
            throw new RegrasDeNegocioException("Informe o código identificado único da revenda!");
        }
        if (oportunidade.getCliente() == null || oportunidade.getCliente().getIdCliente() == null) {
            throw new RegrasDeNegocioException("Informe o código identificado único do cliente!");
        }
        if (oportunidade.getUsuario() == null || oportunidade.getUsuario().getIdUsuario() == null) {
            throw new RegrasDeNegocioException("Informe o código identificado único do usuário!");
        }
        if (oportunidade.getVeiculo() == null || oportunidade.getVeiculo().getIdVeiculo() == null) {
            throw new RegrasDeNegocioException("Informe o código identificado único do veículo!");
        }

        Revenda revenda = revendaRepository.findById(oportunidade.getRevenda().getIdRevenda()).orElseThrow(() -> new RegrasDeNegocioException("Revenda vinculada não encontrada!"));
        Cliente cliente = clienteRepository.findById(oportunidade.getCliente().getIdCliente()).orElseThrow(() -> new RegrasDeNegocioException("Cliente vinculado não encontrado!"));
        Usuario usuario = usuarioRepository.findById(oportunidade.getUsuario().getIdUsuario()).orElseThrow(() -> new RegrasDeNegocioException("Usuário vinculado não encontrado!"));
        Veiculo veiculo = veiculoRepository.findById(oportunidade.getVeiculo().getIdVeiculo()).orElseThrow(() -> new RegrasDeNegocioException("Veículo vinculado não encontrado!"));

        oportunidade.setRevenda(revenda);
        revenda.getOportunidades().add(oportunidade);

        oportunidade.setCliente(cliente);
        cliente.getOportunidades().add(oportunidade);

        oportunidade.setUsuario(usuario);
        usuario.getOportunidades().add(oportunidade);

        oportunidade.setVeiculo(veiculo);
        veiculo.getOportunidades().add(oportunidade);

        oportunidade.setDataDeAtribuicao(LocalDateTime.now());
        oportunidade.setStatus(StatusOportunidade.NOVO);

        // Verifica se foi atribuido o usuário responsável por essa oportunidade
        if (oportunidade.getUsuario().getIdUsuario() == null) {
            // Não foi atribuido, deverá ser atribuido ao usuario do tipo assistente com menor quantidade de oportunidades "em andamento" e maior tempo sem receber uma oportunidade
            Usuario u = distribuirOportunidade();
            oportunidade.getUsuario().setIdUsuario(u.getIdUsuario());
        }

        repository.save(oportunidade);

    }

    private Usuario distribuirOportunidade() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();
        List<Usuario> listaUsuarioAssistente = new ArrayList<>();

        // Adicionamos na lista apenas os usuarios que sao assistentes
        listaUsuario.forEach((u) -> {
            if (u.getPerfil().equals(RolePerfilUsuario.ROLE_ASSISTENTE)) {
                listaUsuarioAssistente.add(u);
            }
        });

        if (listaUsuarioAssistente.isEmpty()) {
            throw new RegrasDeNegocioException("No momento não há nenhum assistente disponível para receber a oportunidade!");
        }

        // Encontrando usuário com menor quantidade de oportunidade "em andamento" e caso ocorra um empate, verificar qual tem maior tempo sem receber uma oportunidade
        Usuario usuarioComMenorQuantidadeDeOportunidadesEmAndamento = listaUsuarioAssistente.get(0);
        for (int i = 0; i < listaUsuarioAssistente.size() - 1; i++) {

            if ((somarQuantidadeDeOportunidadesEmAndamento(listaUsuarioAssistente.get(i).getOportunidades())
                    < somarQuantidadeDeOportunidadesEmAndamento(usuarioComMenorQuantidadeDeOportunidadesEmAndamento.getOportunidades()))) {

                usuarioComMenorQuantidadeDeOportunidadesEmAndamento = listaUsuarioAssistente.get(i);

            }
            if (somarQuantidadeDeOportunidadesEmAndamento(listaUsuarioAssistente.get(i).getOportunidades())
                    == somarQuantidadeDeOportunidadesEmAndamento(usuarioComMenorQuantidadeDeOportunidadesEmAndamento.getOportunidades())) {

                if (getDataUltimaOportunidadeRecebida(listaUsuarioAssistente.get(i).getOportunidades())
                        .isBefore(getDataUltimaOportunidadeRecebida(usuarioComMenorQuantidadeDeOportunidadesEmAndamento.getOportunidades()))) {

                    usuarioComMenorQuantidadeDeOportunidadesEmAndamento = listaUsuarioAssistente.get(i);

                }
            }

        }

        return usuarioComMenorQuantidadeDeOportunidadesEmAndamento;

    }

    private int somarQuantidadeDeOportunidadesEmAndamento(List<Oportunidade> oportunidades) {
        return (int) oportunidades.stream()
                .filter(o -> o.getStatus().equals(StatusOportunidade.EM_ATENDIMENTO))
                .count();
    }

    private LocalDateTime getDataUltimaOportunidadeRecebida(List<Oportunidade> oportunidades) {
        // Se não houver oportunidades para esse usuario, retornamos uma data antiga para priorizar ele no processamento
        if (oportunidades.isEmpty()) {
            return LocalDateTime.MIN;
        }
        LocalDateTime ultimaOportunidade = oportunidades.get(0).getDataDeAtribuicao();
        for (int i = 0; i < oportunidades.size() - 1; i++) {
            if (oportunidades.get(i).getDataDeAtribuicao().isAfter(ultimaOportunidade)) {
                ultimaOportunidade = oportunidades.get(i).getDataDeAtribuicao();
            }
        }
        return ultimaOportunidade;
    }

    @Transactional(readOnly = false)
    @Override
    public void transferirOportunidade(OportunidadeTransferenciaInputDTO oportunidadeTransferenciaInputDTO, String token) {
        // Qual usuário Assistente será?
        // Qual oportunidade?
        // Quem acionou esse método, tem permissão de Proprietário ou Gerente?

        // Buscando usuário
        Usuario usuario = usuarioRepository.findById(oportunidadeTransferenciaInputDTO
                .getIdUsuarioRecebedorDaOportunidade()).orElseThrow(
                () -> new RegrasDeNegocioException("Não foi encontrado o usuário que irá receber a transferência da oportunidade!"));

        // Usuário é assistente?
        if (!usuario.getPerfil().equals(RolePerfilUsuario.ROLE_ASSISTENTE)) {
            throw new RegrasDeNegocioException("O usuário não é assistente!");
        }

        // Buscando a oportunidade
        Oportunidade oportunidade = repository.findById(oportunidadeTransferenciaInputDTO
                .getIdOportunidade()).orElseThrow(
                () -> new RegrasDeNegocioException("Não foi encontrado a oportunidade que será transferida!"));

        // Verificamos se quem acionou o método tem permissão para isso
        if (usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_PROPRIETARIO.toString())
                || usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_GERENTE.toString())) {

            // Verificar se o usuário logado é proprietário ou gerente da revenda da oportunidade
            System.out.println("Cnpj da revenda: " + oportunidade.getRevenda().getCnpj());
            System.out.println("CNpj do usuario logado: " + usuarioLogado.getCnpjTokenUsuarioLogado(token));
            if (!oportunidade.getRevenda().getCnpj().equals(usuarioLogado.getCnpjTokenUsuarioLogado(token))) {
                throw new RegrasDeNegocioException("Você não tem permissão para transferir oportunidades dessa revenda!");
            }

            // Verifica se a oportunidade já foi concluida
            if (oportunidade.getStatus().equals(StatusOportunidade.CONCLUIDO)) {
                throw new RegrasDeNegocioException("Não é possível transferir uma oportunidade concluída!");
            }

            // Remover a oportunidade do usuário original
            Usuario usuarioAnterior = oportunidade.getUsuario();
            if (usuarioAnterior != null) {
                usuarioAnterior.getOportunidades().remove(oportunidade);
                usuarioRepository.save(usuarioAnterior);
            }

            // Realizando transferência
            oportunidade.setUsuario(usuario);
            usuario.getOportunidades().add(oportunidade);

            repository.save(oportunidade);
            usuarioRepository.save(usuario);

        } else {
            throw new RegrasDeNegocioException("Você não tem permissão para transferir oportunidades!");
        }

    }

    @Transactional(readOnly = false)
    @Override
    public void concluir(Oportunidade oportunidade) {
        Oportunidade oportunidadeEncontrada = repository.findById(oportunidade.getIdOportunidade()).orElseThrow(() -> new RegrasDeNegocioException("Oportunidade não encontrada!"));
        if (!oportunidade.getMotivoDaConclusao().isEmpty()) {
            oportunidadeEncontrada.setStatus(StatusOportunidade.CONCLUIDO);
            oportunidadeEncontrada.setDataDeConclusao(LocalDateTime.now());
            oportunidadeEncontrada.setMotivoDaConclusao(oportunidade.getMotivoDaConclusao());
            repository.save(oportunidadeEncontrada);
        } else {
            throw new RegrasDeNegocioException("Informe o motivo da conclusão!");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<Oportunidade> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Oportunidade listarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RegrasDeNegocioException("Oportunidade não encontrada!"));
    }

    @Transactional(readOnly = false)
    @Override
    public void atualizar(Oportunidade oportunidade, String token) {
        // O usuário que está acionando este método deve ser proprietário ou gerente ou dono da oportunidade

        // Verificando se o código identificador único da oportunidade foi informado e se ela existe no banco de dados
        if (oportunidade.getIdOportunidade() != null) {
            Oportunidade oportunidadeEncontrada = listarPorId(oportunidade.getIdOportunidade());

            Revenda revenda = revendaRepository.findById(oportunidade.getRevenda().getIdRevenda())
                    .orElseThrow(() -> new RegrasDeNegocioException("Revenda vinculada não encontrada!"));
            Cliente cliente = clienteRepository.findById(oportunidade.getCliente().getIdCliente())
                    .orElseThrow(() -> new RegrasDeNegocioException("Cliente vinculado não encontrado!"));
            Usuario usuario = usuarioRepository.findById(oportunidade.getUsuario().getIdUsuario())
                    .orElseThrow(() -> new RegrasDeNegocioException("Usuário vinculado não encontrado!"));
            Veiculo veiculo = veiculoRepository.findById(oportunidade.getVeiculo().getIdVeiculo())
                    .orElseThrow(() -> new RegrasDeNegocioException("Veículo vinculado não encontrado!"));

            // Atualizando os dados da oportunidade
            oportunidadeEncontrada.setRevenda(revenda);
            oportunidadeEncontrada.setCliente(cliente);
            oportunidadeEncontrada.setUsuario(usuario);
            oportunidadeEncontrada.setVeiculo(veiculo);

            // Verificando as permissões
            if (temPermissaoParaAtualizar(oportunidadeEncontrada, token)) {
                repository.save(oportunidadeEncontrada);
            } else {
                throw new RegrasDeNegocioException("Você não tem permissão para atualizar esta oportunidade!");
            }
        } else {
            throw new RegrasDeNegocioException("Informe o código identificador da oportunidade!");
        }
    }

    private boolean temPermissaoParaAtualizar(Oportunidade oportunidade, String token) {
        Usuario usuario = oportunidade.getUsuario();

        // Verificando se o usuário logado é proprietário ou gerente
        if (usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_PROPRIETARIO.toString())
                || usuarioLogado.getPerfilTokenUsuarioLogado(token).equals(RolePerfilUsuario.ROLE_GERENTE.toString())) {
            return true;
        }

        // Verificando se o usuário logado é o dono da oportunidade
        Long idUsuarioLogado = Long.parseLong(usuarioLogado.getIDTokenUsuarioLogado(token));
        return idUsuarioLogado.equals(usuario.getIdUsuario());
    }


}
