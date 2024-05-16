package br.com.tech.challenge.msproduto.infrastructure.persistence.repository.jpa;

import br.com.tech.challenge.msproduto.infrastructure.persistence.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoModel, Long> {
}
