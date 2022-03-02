package com.example.ReactiveCRUD.service;

import com.example.ReactiveCRUD.entity.Contract;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ContractService {

    Flux<Contract> list();

    Flux<Contract> getById(long id);

    Mono<Contract> addOne(Contract contract);

    Mono<Void> deleteById(Long id);

    Mono<Contract> saveContract(Contract contract);

    Flux<Contract> filterBySum(double sum);

    Flux<Contract> filterByBeginDate(LocalDate beginDate);

    Flux<Contract> getContract(LocalDate dateBegin, Long count);
}
