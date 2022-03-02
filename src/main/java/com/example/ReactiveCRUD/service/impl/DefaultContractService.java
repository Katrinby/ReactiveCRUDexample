package com.example.ReactiveCRUD.service.impl;


import com.example.ReactiveCRUD.entity.Contract;
import com.example.ReactiveCRUD.repository.ContractRepository;
import com.example.ReactiveCRUD.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service(value = "contactService")
public class DefaultContractService implements ContractService {
    private final ContractRepository contractRepo;

    @Autowired
    public DefaultContractService(final ContractRepository contractRepo) {
        this.contractRepo = contractRepo;
    }

    @Override
    public Flux<Contract> list() {
        return contractRepo.findAll();
    }

    @Override
    public Flux<Contract> getById(final long id) {
        return contractRepo.findContractById(id);
    }

    @Override
    public Mono<Contract> addOne(final Contract contract) {
        return contractRepo.save(contract);
    }

    @Override
    public Mono<Void> deleteById(final Long id) {
        return contractRepo.deleteContractById(id);
    }

    @Override
    public Mono<Contract> saveContract(final Contract contract) {
        return contractRepo.save(contract);
    }

    @Override
    public Flux<Contract> filterBySum(final double sum) {
        return contractRepo.findContractsBySumGreaterThanEqual(sum);
    }

    @Override
    public Flux<Contract> filterByBeginDate(final LocalDate beginDate) {
        return contractRepo.findByDateBeginAfter(beginDate);
    }

    @Override
    public Flux<Contract> getContract(final LocalDate dateBegin, final Long count) {
        if (dateBegin == null) {
            return contractRepo.findByIdLessThanEqual(count);
        }
        if (count == null) {
            return contractRepo.findByDateBeginAfter(dateBegin);
        }

        return contractRepo.findFirstNContractAndAfterRequiredDate(dateBegin, count);
    }
}
