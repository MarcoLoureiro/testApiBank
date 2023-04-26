package com.ada.aulajunit.rest;

import com.ada.aulajunit.modelo.DollarQuotation;
import com.ada.aulajunit.service.BancoCentralAPI;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("banco")
public class BancoCentralRest {

    @Autowired
    private BancoCentralAPI bancoCentralAPI;


//    http://localhost:8080/banco/cotacao?startDate=03-22-2022
    @GetMapping("/cotacao")
    public ResponseEntity<?> getDollarQuotation(@RequestParam("startDate") String startDate) {
        //pattern must be like mm-dd-yyyy
        Map <String, Object> map = new HashMap<>();
        try {
            if (bancoCentralAPI.findDollarQuotation(startDate).getSaleQuotation() != null) {
                map.put("resultado",bancoCentralAPI.findDollarQuotation(startDate).toString());
                return ResponseEntity.status(HttpStatus.OK).body(map);
            }
            else
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bancoCentralAPI.findDollarQuotation(startDate));
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //Parâmetros para teste:
//    http://localhost:8080/banco/register
//    {
//        "date": "2022-03-22",
//            "purchaseQuotation": 4.9202,
//            "saleQuotation": 4.9208
//    }

    @PostMapping("/register")
    public ResponseEntity<?> postDollarQuotation(@RequestBody DollarQuotation dollarQuotation) {
        System.out.println(dollarQuotation.getDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoCentralAPI.registerDollarQuotation(dollarQuotation));
    }

//  http://localhost:8080/banco/getQuotationFromDb?date=2022-03-22
    @GetMapping("/getQuotationFromDb")
    public ResponseEntity<DollarQuotation> getQuotationFromDb(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date date){
        return ResponseEntity.status(HttpStatus.OK).body(bancoCentralAPI.findDbDollarQuotationByDate(date));
    }





}
