package dev.maicol.graphqlCRUD.controller;

import dev.maicol.graphqlCRUD.repository.OneTimePasswordRepository;
import dev.maicol.graphqlCRUD.util.TotpUtil;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class TotpController {

    @Autowired
    TotpUtil totpUtil;

    @QueryMapping
    public String totpGen() {
        return totpUtil.generateOneTimePassword().getCode().toString();
    }

    @MutationMapping
    public boolean verifyTotp( @Argument String code) {
        return totpUtil.verifyOneTimePassword(Integer.parseInt(code));
    }
}