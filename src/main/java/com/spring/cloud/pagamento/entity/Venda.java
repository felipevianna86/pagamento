package com.spring.cloud.pagamento.entity;

import com.spring.cloud.pagamento.data.vo.VendaVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "produto_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Venda implements Serializable {

    private static final long serialVersionUID = 481224858890538560L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(nullable = false)
    private Date dataVenda;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
    private List<ProdutoVenda> produtos;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    public static Venda create(VendaVO vendaVO){
        return new ModelMapper().map(vendaVO, Venda.class);
    }
}
