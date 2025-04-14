package ifmg.edu.br.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ifmg.edu.br.dtos.ProdutoDTO;
import ifmg.edu.br.service.ProdutoService;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResources {

    @Autowired 
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok( produtoService.findAll(pageable));
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok( produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> insert (@RequestBody ProdutoDTO dto){
        dto = produtoService.insertProduto(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}