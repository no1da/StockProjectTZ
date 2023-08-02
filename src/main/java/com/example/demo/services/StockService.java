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
        /*dao.delete(id);*/

        List<StockStateDTO> dtoList = new ArrayList<>();
        StockStateDTO stockStateDTO1 = new StockStateDTO();
        stockStateDTO1.setId(1);
        stockStateDTO1.setName("IIFGGF");
        stockStateDTO1.setPrice(2000);
        StockStateDTO stockStateDTO2 = new StockStateDTO();
        stockStateDTO2.setId(2);
        stockStateDTO2.setName("HUH");
        stockStateDTO2.setPrice(2030);

        dtoList.add(stockStateDTO1);
        dtoList.add(stockStateDTO2);

        System.out.println(dtoList);


        List<String> localDates = dtoList.stream().map(StockStateDTO::getName).collect(Collectors.toList());
        System.out.println(localDates);

        String string = new String("ТАНЯ");
        String string1 = new String("ТАНЯ");
        String string2 = new String("ТАНЯ");
        String string3 = new String("ТАНЯ");
        String string4 = new String("ТАНЯ");

        String s = "kdfldkfcfgjdfgjfgg";
        String s1 = "kdfldkfg";




    }


    public void update(StockStateDTO stockStateDTO, int id) {
        dao.update(stockStateDTO, id);
    }

}
