package com.spring.cloud.pagamento.entity;

import com.spring.cloud.pagamento.data.vo.ProdutoVendaVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "produto_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoVenda implements Serializable {

    private static final long serialVersionUID = -9208312787020910659L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idProduto;

    @Column(nullable = false, length = 10)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public static ProdutoVenda create(ProdutoVendaVO produtoVendaVO){
        return new ModelMapper().map(produtoVendaVO, ProdutoVenda.class);
    }
}
