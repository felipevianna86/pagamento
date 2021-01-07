package com.spring.cloud.pagamento.controller;

import com.spring.cloud.pagamento.data.vo.VendaVO;
import com.spring.cloud.pagamento.service.VendaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/venda")
public class VendaController {

    private final VendaService vendaService;
    private final PagedResourcesAssembler<VendaVO> assembler;

    public VendaController(VendaService vendaService, PagedResourcesAssembler<VendaVO> assembler){
        this.vendaService = vendaService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public VendaVO findById(@PathVariable("id") Long id){
        VendaVO vendaVO = vendaService.findById(id);
        vendaVO.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return vendaVO;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     @RequestParam(value = "direction", defaultValue = "asc") String direction){

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "dataVenda"));

        Page<VendaVO> vendas = vendaService.findAll(pageable);

        vendas.stream().forEach(v -> v.add(linkTo(methodOn(VendaController.class).findById(v.getId())).withSelfRel()));

        PagedModel<EntityModel<VendaVO>> pagedModel = assembler.toModel(vendas);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);

    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public VendaVO create(@RequestBody VendaVO vendaVO){
        VendaVO vendaVOSaved = vendaService.create(vendaVO);
        vendaVOSaved.add(linkTo(methodOn(VendaController.class).findById(vendaVOSaved.getId())).withSelfRel());

        return vendaVOSaved;
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {"application/json", "application/xml", "application/x-yaml"})
    public VendaVO update(@RequestBody VendaVO vendaVO){
        VendaVO vendaVOSaved = vendaService.update(vendaVO);
        vendaVOSaved.add(linkTo( methodOn(VendaController.class).findById(vendaVOSaved.getId())).withSelfRel());

        return vendaVOSaved;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        vendaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
