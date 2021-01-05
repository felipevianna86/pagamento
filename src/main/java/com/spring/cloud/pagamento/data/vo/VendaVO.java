package com.spring.cloud.pagamento.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.spring.cloud.pagamento.entity.ProdutoVenda;
import com.spring.cloud.pagamento.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id", "dataVenda", "produtos", "valorTotal"})
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {

    private static final long serialVersionUID = 8532772745426979813L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("dataVenda")
    private Data dataVenda;

    @JsonProperty("produtos")
    private List<ProdutoVenda> produtos;

    @JsonProperty("valorTotal")
    private BigDecimal valorTotal;

    public static VendaVO create(Venda venda){
        return new ModelMapper().map(venda, VendaVO.class);
    }
}
