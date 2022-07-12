package com.faltdor.card.controller;

import com.faltdor.card.config.LoanServiceConfig;
import com.faltdor.card.model.Customer;
import com.faltdor.card.model.Loans;
import com.faltdor.card.model.Properties;
import com.faltdor.card.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
public class LoansController {

    private final LoansRepository loansRepository;

    private final LoanServiceConfig loansConfig;

    @PostMapping( "/myLoans" )
    public List<Loans> getLoansDetails( @RequestBody Customer customer ) {

        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc( customer.getCustomerId() );
        if ( loans != null ) {
            return loans;
        } else {
            return null;
        }
    }

    @GetMapping( "/loans/properties" )
    public String getPropertyDetails() throws JsonProcessingException {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties( loansConfig.getMsg(), loansConfig.getBuildVersion(),
              loansConfig.getMailDetails(), loansConfig.getActiveBranches() );
        String jsonStr = ow.writeValueAsString( properties );
        return jsonStr;
    }
}