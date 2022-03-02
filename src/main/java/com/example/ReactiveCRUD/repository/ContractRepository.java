package com.example.ReactiveCRUD.repository;

import com.example.ReactiveCRUD.entity.Contract;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ContractRepository extends ReactiveCrudRepository<Contract, Long> {
    Flux<Contract> findAll();

    @Query("SELECT * from loc_contract LIMIT $1 ")
    Flux<Contract> findByIdLessThanEqual (Long count);

    Flux<Contract> findContractById(long id);

    Flux<Contract> findContractsBySumGreaterThanEqual(double sum);
    Flux<Contract> findByDateBeginAfter(LocalDate beginDate);
    Mono<Void> deleteContractById(long id);
    Mono<Contract> save(Contract contract);

    @Query("SELECT * FROM loc_contract  WHERE date_begin >=$1 LIMIT $2 ")
    Flux<Contract> findFirstNContractAndAfterRequiredDate(LocalDate dateBegin, Long count);

}
