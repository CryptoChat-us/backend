package br.com.api.crypto_chat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.crypto_chat.utils.EncryptionUtils;

@Service
public class PasswordEncoder {

    @Autowired
    EncryptionUtils utils;

    public boolean matches(String unEncryptedString, String encryptedString) {
        return utils.encrypt(unEncryptedString).equals(encryptedString);
    }

    public String encode(String data) {
        return utils.encrypt(data);
    }
}
