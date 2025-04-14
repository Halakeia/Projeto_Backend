package ifmg.edu.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ifmg.edu.br.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    
} 
