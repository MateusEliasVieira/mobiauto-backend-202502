package com.mobiauto.domain.service.revenda.impl;

import com.mobiauto.api.dto.revenda.RevendaAddUsuarioInputDTO;
import com.mobiauto.api.dto.revenda.RevendaIDInputDTO;
import com.mobiauto.api.dto.usuario.UsuarioIDInputDTO;
import com.mobiauto.domain.exception.RegrasDeNegocioException;
import com.mobiauto.domain.model.Revenda;
import com.mobiauto.domain.model.Usuario;
import com.mobiauto.domain.repository.RevendaRepository;
import com.mobiauto.domain.repository.UsuarioRepository;
import com.mobiauto.domain.service.revenda.RevendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevendaServiceImpl implements RevendaService {

    @Autowired
    private RevendaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void salvar(Revenda revenda, String token) {
        if (repository.findByCnpj(revenda.getCnpj()).isEmpty()) {
            repository.save(revenda);
        } else {
            throw new RegrasDeNegocioException("Já existe uma revenda cadastrada com o CNPJ " + revenda.getCnpj());
        }
    }

    @Override
    public void adicionarUsuario(RevendaAddUsuarioInputDTO revendaAddUsuarioInputDTO) {
        Revenda revenda = listarPorId(revendaAddUsuarioInputDTO.getIdRevenda());

        if (revendaAddUsuarioInputDTO.getIdUsuario() == null)
            throw new RegrasDeNegocioException("Informe o código de identificado único do usuário a ser adicionado a revenda!");

        Usuario usuario = usuarioRepository.findById(revendaAddUsuarioInputDTO.getIdUsuario()).orElseThrow(() -> new RegrasDeNegocioException("Usuário não encontrado!"));

        revenda.getUsuarios().add(usuario);
        usuario.setRevenda(revenda);

        repository.save(revenda);
        usuarioRepository.save(usuario);

    }

    @Override
    public List<Revenda> listar() {
        return repository.findAll();
    }

    @Override
    public Revenda listarPorId(Long id) {
        if (id != null) {
            return repository.findById(id)
                    .orElseThrow(() -> new RegrasDeNegocioException("Revenda não encontrada!"));
        } else {
            throw new RegrasDeNegocioException("Informe o código identificador único da revenda!");
        }
    }

    @Override
    public Revenda listarPorCnpj(String cnpj) {
        return repository.findByCnpj(formatarCNPJ(cnpj))
                .orElseThrow(() -> new RegrasDeNegocioException("Revenda não encontrada!"));
    }

    private String formatarCNPJ(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !cnpj.matches("\\d+")) {
            throw new RegrasDeNegocioException("CNPJ inválido! Deve conter exatamente 14 dígitos numéricos.");
        }

        return new StringBuilder(cnpj)
                .insert(2, ".")
                .insert(6, ".")
                .insert(10, "/")
                .insert(15, "-")
                .toString();
    }

    @Override
    public Revenda atualizar(Revenda revenda) {
        // Apenas se atualiza o nomeSocial e cnpj
        Revenda revendaExistente = listarPorId(revenda.getIdRevenda());
        revenda.setUsuarios(revendaExistente.getUsuarios());
        revenda.setOportunidades(revendaExistente.getOportunidades());

        return repository.save(revenda);
    }


}
