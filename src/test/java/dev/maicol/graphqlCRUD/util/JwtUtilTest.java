package dev.maicol.graphqlCRUD.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    @Test
    public void generateJwtTokenAndVerify() {

        String token = new JwtUtil().generateJwtToken("maicol", List.of("ADMIN"));

        assertNotNull(token);

        var claims = new JwtUtil().validateToken(token);

        assertEquals(claims.getSubject(), "maicol");

    }


}