package dev.maicol.graphqlCRUD.util;

import dev.maicol.graphqlCRUD.entity.OneTimePassword;
import dev.maicol.graphqlCRUD.repository.OneTimePasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class TotpUtil {

    @Autowired
    OneTimePasswordRepository oneTimePasswordRepository;
//    private final Long expiryInterval = 5L * 60 * 1000; // 5 minutes
    private final Long expiryInterval = 1L * 60 * 1000; // 1 minutes
    private final static Integer LENGTH = 4;

    public OneTimePassword generateOneTimePassword() {
        OneTimePassword oneTimePassword = new OneTimePassword();
        oneTimePassword.setCode(createRandomOneTimePassword());
        oneTimePassword.setExpirationDate(new java.util.Date(System.currentTimeMillis() + expiryInterval));
        return oneTimePasswordRepository.save(oneTimePassword);
    }


    private static Integer createRandomOneTimePassword() {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }

            return Integer.parseInt(oneTimePassword.toString());
    }

//    verificar que exista y que no este expirado  y debolver true
    public boolean verifyOneTimePassword(Integer code) {

        Optional<OneTimePassword> oneTimePassword = Optional.ofNullable(oneTimePasswordRepository.findByCode(code));

        if (oneTimePassword.isPresent()) {
            if (oneTimePassword.get().getExpirationDate().after(new java.util.Date())) {
                oneTimePasswordRepository.delete(oneTimePassword.get());
                return true;
            }
        }

        return false;
    }
}
