package com.example.ReactiveCRUD.controller;


import com.example.ReactiveCRUD.entity.Contract;
import com.example.ReactiveCRUD.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contract")
public class MainController {
    private final ContractService contractService;



    @PostMapping("/add")
    public Mono<Contract> add(@RequestBody Contract contract) {
        return contractService.addOne(contract);
    }

    @GetMapping("/all")
    public Flux<Contract> getAll() {
        return contractService
                .list()
                .delayElements(Duration.ofMillis(200));
    }

    @GetMapping("/{id}")
    public Flux<Contract> getById( @PathVariable("id") Long id){

        return contractService.getById(id);}

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id){

        return contractService.deleteById(id);
    }

    @PutMapping("/{id}")
    Mono<Contract> updateContract(@RequestBody Contract contract, @PathVariable("id") Long id){
        contract.setId(id);
        return contractService.saveContract(contract);
    }

    @GetMapping("/first2")
    public Flux<Contract> getFirst2(){
        return contractService.getFirst2();
    }
}
