package com.example.ReactiveCRUD.controller;


import com.example.ReactiveCRUD.entity.Contract;
import com.example.ReactiveCRUD.exception.CompleteDeleteMessage;
import com.example.ReactiveCRUD.exception.CompleteUpdateMessage;
import com.example.ReactiveCRUD.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ReactiveCRUD/v1/loc_contract")
public class MainController {
    @Resource
    private final ContractService contractService;


    @PostMapping()
    public Mono<Contract> add(@RequestBody Contract contract) {
        return contractService.addOne(contract);
    }

    /*@GetMapping("/")
    public Flux<Contract> getAll() {
        return contractService
                .list()
                .delayElements(Duration.ofMillis(200));
    }*/

    @GetMapping("/{id}")
    public Flux<Contract> getById(@PathVariable("id") Long id) {

        return contractService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {

        return contractService.deleteById(id).switchIfEmpty(Mono.error(new CompleteDeleteMessage()));
    }

    @PutMapping("/{id}")
    Mono<Contract> updateContract(@RequestBody Contract contract, @PathVariable("id") Long id) {
        contract.setId(id);
        return contractService.saveContract(contract).switchIfEmpty(Mono.error(new CompleteUpdateMessage()));
    }

    @GetMapping()
    public Flux<Contract> getContract(@RequestParam(required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateBegin,
                                      @RequestParam(required = false) Long count) {
        return contractService.getContract(dateBegin, count).switchIfEmpty(Mono.error(new NoSuchElementException ()));
    }

    @GetMapping("/sumFilter/{sum}")
    public Flux<Contract> sumFilter(@PathVariable("sum") double sum) {
        return contractService.filterBySum(sum);
    }
    /*@GetMapping("/export")
    public Mono<Void> exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Flux<Contract> getAll = getAll();

        ContractExcelExporter excelExporter = new ContractExcelExporter(getAll);

        excelExporter.export(response);
    }*/
}
