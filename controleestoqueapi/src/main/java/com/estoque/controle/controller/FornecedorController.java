package com.estoque.controle.controller;

import com.estoque.controle.model.fornecedor.Fornecedor;
import com.estoque.controle.model.fornecedor.FornecedorDTO;
import com.estoque.controle.repository.FornecedorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private FornecedorRepository fornecedorRepository;

    public FornecedorController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @PostMapping
    public Fornecedor cadastrar(@RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorDTO dtoFormatado = FornecedorDTO.of(fornecedorDTO.nomeFornecedor(),fornecedorDTO.cpfOuCnpj(), fornecedorDTO.tipoFornecedor());

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNomeCliente(dtoFormatado.nomeFornecedor());
        fornecedor.setCpfOuCnpj(dtoFormatado.cpfOuCnpj());
        fornecedor.setTipoFornecedor(dtoFormatado.tipoFornecedor());

        fornecedorRepository.save(fornecedor);

        return fornecedor;
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") int id) {
       Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));

        fornecedorRepository.delete(fornecedor);
    }

    @PutMapping("{id}")
    public Fornecedor atualizar(@PathVariable("id") int id,@RequestBody FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado!"));

        FornecedorDTO dtoFormatado = FornecedorDTO.of(fornecedorDTO.nomeFornecedor(),fornecedorDTO.cpfOuCnpj(), fornecedorDTO.tipoFornecedor());

        fornecedor.setId(id);
        fornecedor.setNomeCliente(dtoFormatado.nomeFornecedor());
        fornecedor.setCpfOuCnpj(dtoFormatado.cpfOuCnpj());
        fornecedor.setTipoFornecedor(dtoFormatado.tipoFornecedor());

        return fornecedorRepository.save(fornecedor);
    }

    @GetMapping
    public List<Fornecedor> buscarTodos() {
        return fornecedorRepository.findAll();
    }
}
