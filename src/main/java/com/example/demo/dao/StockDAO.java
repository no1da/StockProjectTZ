package com.example.demo.dao;

import com.example.demo.dto.StockStateDTO;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Component
public class StockDAO {
    private final JdbcTemplate jdbcTemplate;

    public StockDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<StockStateDTO> getList() {
        return jdbcTemplate.query("select * from stock join stockstate on stock.id = stock_id", new BeanPropertyRowMapper<>(StockStateDTO.class));
    }

    public void save(StockStateDTO stockStateDTO) {
        try {

            String select = "SELECT id FROM stock WHERE name = (?)";
            int id = this.jdbcTemplate.queryForObject(select, Integer.class, stockStateDTO.getName());

            String insert = "INSERT INTO stockstate (price, date, stock_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(insert, stockStateDTO.getPrice(), stockStateDTO.getDate(), id);
        } catch (NullPointerException e) {
            e.getMessage();
        }
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM stockstate WHERE id=?", id);
    }

    public void update(StockStateDTO stockStateDTO, int id) {
        jdbcTemplate.update("UPDATE stockstate SET price=?, date=? WHERE id=?", stockStateDTO.getPrice(), stockStateDTO.getDate(), id);
    }

    public StockStateDTO getByState(String name, LocalDate date, int price) {
        try {
            return jdbcTemplate.queryForObject("select stockstate.id,price,date,stock_id from stock join stockstate on stock.id = stockstate.stock_id where stock.name = ? and stockstate.date=? and stockstate.price=?",
                    new Object[]{name, date, price}, new BeanPropertyRowMapper<>(StockStateDTO.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }


    }

}
