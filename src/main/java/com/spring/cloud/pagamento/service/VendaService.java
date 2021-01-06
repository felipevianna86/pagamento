package com.spring.cloud.pagamento.service;

import com.spring.cloud.pagamento.data.vo.VendaVO;
import com.spring.cloud.pagamento.entity.ProdutoVenda;
import com.spring.cloud.pagamento.entity.Venda;
import com.spring.cloud.pagamento.exception.ResourceNotFoundException;
import com.spring.cloud.pagamento.repository.ProdutoVendaRepository;
import com.spring.cloud.pagamento.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository){
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaVO create(VendaVO vendaVO){
        Venda venda = vendaRepository.save(convertToVenda(vendaVO));
        List<ProdutoVenda> produtosSalvos = new ArrayList<>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda produtoVenda = ProdutoVenda.create(p);
            produtoVenda.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(produtoVenda));
        });

        venda.setProdutos(produtosSalvos);
        return convertToVendaVO(venda);
    }

    public Page<VendaVO> findAll(Pageable pageable){
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToVendaVO);
    }

    public VendaVO findById(Long id){
        var entity = vendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));
        return convertToVendaVO(entity);
    }

    public VendaVO update(VendaVO vendaVO){
        final Optional<Venda> optionalVenda = vendaRepository.findById(vendaVO.getId());

        if(!optionalVenda.isPresent()){
            new ResourceNotFoundException("No records found for this ID");
        }

        Venda venda = vendaRepository.save(convertToVenda(vendaVO));

        return convertToVendaVO(venda);
    }

    public void delete(Long id){
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        vendaRepository.delete(entity);
    }

    private VendaVO convertToVendaVO(Venda venda){
        return VendaVO.create(venda);
    }

    private Venda convertToVenda(VendaVO vendaVO){
        return Venda.create(vendaVO);
    }

}
