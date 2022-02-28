package com.example.ReactiveCRUD.service;


import com.example.ReactiveCRUD.entity.Contract;
import com.example.ReactiveCRUD.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class ContractService {
    private final ContractRepository contractRepo;

    @Autowired
    public ContractService(ContractRepository contractRepo){
        this.contractRepo = contractRepo;
    }

    public Flux<Contract> list(){
        return  contractRepo.findAll();
    }

    public Flux<Contract> getById(long id){
        return  contractRepo.findContractById(id);
    }

    public Mono<Contract> addOne(Contract contract){
        return contractRepo.save(contract);
    }

    public Mono<Void> deleteById(Long id){
        return  contractRepo.deleteContractById(id);
    }

    public Mono<Contract> saveContract(Contract contract){
        return contractRepo.save(contract);
    }

    public Flux<Contract> getFirst2(){
        return  contractRepo.findByIdLessThanEqual();
    }
    public Flux<Contract> filterBySum(double sum){
        return  contractRepo.findContractsBySumGreaterThanEqual(sum);
    }

    public Flux<Contract> filterByBeginDate(LocalDate beginDate){
        return  contractRepo.findContractsByDateBeginGreaterThanEqual(beginDate);
    }
}
