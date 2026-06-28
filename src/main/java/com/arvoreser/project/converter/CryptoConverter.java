package com.arvoreser.project.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    // Chave AES de 128 bits (16 caracteres) para o escopo do projeto acadêmico.
    // (Em produção corporativa real, isso viria de uma variável de ambiente do sistema operacional).
    private static final String CHAVE_SECRETA = "ArvoreSer2026Key";
    private static final String ALGORITMO = "AES";

    @Override
    public String convertToDatabaseColumn(String textoHumano) {
        if (textoHumano == null) return null;
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE_SECRETA.getBytes(), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            return Base64.getEncoder().encodeToString(cipher.doFinal(textoHumano.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Erro de segurança: Falha ao criptografar o prontuário", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String textoCriptografadoNoBanco) {
        if (textoCriptografadoNoBanco == null) return null;
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE_SECRETA.getBytes(), ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, chave);
            return new String(cipher.doFinal(Base64.getDecoder().decode(textoCriptografadoNoBanco)));
        } catch (Exception e) {
            throw new RuntimeException("Erro de segurança: Falha ao descriptografar o prontuário", e);
        }
    }
}