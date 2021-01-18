package com.spring.cloud.pagamento.message;

import com.spring.cloud.pagamento.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

}
