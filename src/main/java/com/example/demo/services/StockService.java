package com.example.demo.services;


import com.example.demo.controllers.StockController;
import com.example.demo.exception.OverlapException;
import com.example.demo.dao.StockDAO;
import com.example.demo.dto.StockStateDTO;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);
    private final StockDAO dao;

    @Autowired
    public StockService(StockDAO dao) {
        this.dao = dao;
    }


    public List<StockStateDTO> getAll() {
        return dao.getList();

    }


    public void save(StockStateDTO stockStateDTO) {
        String currentStockStateName = stockStateDTO.getName();
        StockStateDTO stock = dao.getByState(stockStateDTO.getName(), stockStateDTO.getDate(), stockStateDTO.getPrice());
        if (stock != null) {
            logger.error("Повторные значения");
            throw new OverlapException("Повторные значения");
        } else {
            dao.save(stockStateDTO);
        }
    }


    public void delete(int id) {
        dao.delete(id);
}


    public void update(StockStateDTO stockStateDTO, int id) {
        dao.update(stockStateDTO, id);
    }

}
