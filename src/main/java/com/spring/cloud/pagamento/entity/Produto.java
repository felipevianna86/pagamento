package com.spring.cloud.pagamento.entity;

import com.spring.cloud.pagamento.data.vo.ProdutoVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {

    private static final long serialVersionUID = 1192638510105802285L;

    @Id
    private Long id;

    @Column(nullable = false ,length = 10)
    private Integer estoque;

    public static Produto create(ProdutoVO produtoVO){
        return new ModelMapper().map(produtoVO, Produto.class);
    }
}
