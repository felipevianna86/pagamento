package com.spring.cloud.pagamento.message;

import com.spring.cloud.pagamento.data.vo.ProdutoVO;
import com.spring.cloud.pagamento.entity.Produto;
import com.spring.cloud.pagamento.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public  void receive(@Payload ProdutoVO produtoVO){
        produtoRepository.save(Produto.create(produtoVO));
    }

}
