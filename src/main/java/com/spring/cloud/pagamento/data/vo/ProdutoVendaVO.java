package com.spring.cloud.pagamento.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spring.cloud.pagamento.entity.ProdutoVenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "idProduto", "quantidade"})
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable {

    private static final long serialVersionUID = -6414975976714737981L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduto")
    private Long idProduto;

    @JsonProperty("quantidade")
    private Integer quantidade;

    public static ProdutoVendaVO create(ProdutoVenda produtoVenda){
        return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
    }
}
