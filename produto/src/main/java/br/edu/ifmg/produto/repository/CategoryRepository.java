package br.edu.ifmg.produto.repository;

import br.edu.ifmg.produto.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //anotacao opcional o spring já reconhece como repositorio pelo uso do JPA
public interface CategoryRepository extends JpaRepository<Category, Long> {
/*
* JpaRepository é uma interface forneccida pelo spring data que já vem com varios metodos prontos
* para manipulacao no banco de dados*/

}