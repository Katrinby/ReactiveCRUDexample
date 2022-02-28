package com.example.ReactiveCRUD.repository;

import com.example.ReactiveCRUD.entity.Contract;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ContractRepository extends ReactiveCrudRepository<Contract, Long> {
    Flux<Contract> findAll();

    @Query("SELECT * from loc_contract LIMIT 2 ")
    Flux<Contract> findByIdLessThanEqual ();

    Flux<Contract> findContractById(long id);

    Flux<Contract> findContractsBySumGreaterThanEqual(double sum);
    Flux<Contract> findContractsByDateBeginGreaterThanEqual(LocalDate beginDate);
    Mono<Void> deleteContractById(long id);
    Mono<Contract> save(Contract contract);

}
