package com.example.demo.controllers;

import com.example.demo.dto.StockStateDTO;
import com.example.demo.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/stock")
public class StockController {

    private final StockService service;

    @Autowired
    public StockController(StockService service) {
        this.service = service;
    }

    @GetMapping
    public List<StockStateDTO> getAll() {
        return service.getAll();
    }


    @PostMapping()
    public void save(@RequestBody StockStateDTO stockStateDTO) {

                service.save(stockStateDTO);

    }

    @PostMapping("/{id}")
    public void update(@RequestBody StockStateDTO stockStateDTO, @PathVariable int id) {
        service.update(stockStateDTO, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }


}
