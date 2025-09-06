package com.service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.pqc.crypto.crystals.kyber.*;
import org.bouncycastle.pqc.crypto.crystals.dilithium.*;
import org.springframework.stereotype.Service;

@Service
public class PqcCryptoService {
    private final SecureRandom secureRandom = new SecureRandom();

    // Kyber keys
    private KyberPublicKeyParameters kyberPublicKey;
    private KyberPrivateKeyParameters kyberPrivateKey;

    // Dilithium keys
    private DilithiumPublicKeyParameters dilithiumPublicKey;
    private DilithiumPrivateKeyParameters dilithiumPrivateKey;

    public PqcCryptoService() {
        generateKyberKeys();
        generateDilithiumKeys();
    }

    // 1️⃣ Generate Kyber Key Pair (Encryption)
    private void generateKyberKeys() {
        KyberKeyPairGenerator keyPairGenerator = new KyberKeyPairGenerator();
        keyPairGenerator.init(new KyberKeyGenerationParameters(new SecureRandom(), KyberParameters.kyber1024));
        AsymmetricCipherKeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.kyberPublicKey = (KyberPublicKeyParameters) keyPair.getPublic();
        this.kyberPrivateKey = (KyberPrivateKeyParameters) keyPair.getPrivate();
    }

    // 2️⃣ Generate Dilithium Key Pair (Signing)
    private void generateDilithiumKeys() {
        DilithiumKeyPairGenerator keyPairGenerator = new DilithiumKeyPairGenerator();
        keyPairGenerator.init(new DilithiumKeyGenerationParameters(new SecureRandom(), DilithiumParameters.dilithium5));
        AsymmetricCipherKeyPair keyPair = keyPairGenerator.generateKeyPair();
        this.dilithiumPublicKey = (DilithiumPublicKeyParameters) keyPair.getPublic();
        this.dilithiumPrivateKey = (DilithiumPrivateKeyParameters) keyPair.getPrivate();
    }

    // 3️⃣ Encrypt a message using Kyber + AES-GCM
    public String encryptMessage(String plainText) throws Exception {
        KyberKEMGenerator encapsulator = new KyberKEMGenerator(secureRandom);
        SecretWithEncapsulation secretWithEncapsulation = encapsulator.generateEncapsulated(kyberPublicKey);
        byte[] sharedSecret = secretWithEncapsulation.getSecret();

        // Convert to AES Key
        SecretKey aesKey = new SecretKeySpec(sharedSecret, 0, 32, "AES");

        // AES-GCM Encryption
        byte[] iv = new byte[12];
        secureRandom.nextBytes(iv);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));

        byte[] encryptedData = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Combine IV + Encapsulated Ciphertext + Encrypted Data
        byte[] encapsulatedCiphertext = secretWithEncapsulation.getEncapsulation();
        byte[] combinedData = new byte[iv.length + encapsulatedCiphertext.length + encryptedData.length];

        System.arraycopy(iv, 0, combinedData, 0, iv.length);
        System.arraycopy(encapsulatedCiphertext, 0, combinedData, iv.length, encapsulatedCiphertext.length);
        System.arraycopy(encryptedData, 0, combinedData, iv.length + encapsulatedCiphertext.length, encryptedData.length);

        return Base64.getEncoder().encodeToString(combinedData);
    }

    // 4️⃣ Decrypt the message using Kyber
    public String decryptMessage(String encryptedData) throws Exception {
        byte[] combinedData = Base64.getDecoder().decode(encryptedData);
        int ivLength = 12; 
        int kyberCiphertextLength = 1568;
        int encryptedMessageLength = combinedData.length - ivLength - kyberCiphertextLength;

        if (encryptedMessageLength < 0) {
            throw new IllegalArgumentException("Invalid encrypted data length!");
        }

        byte[] iv = new byte[ivLength];
        byte[] encapsulatedCiphertext = new byte[kyberCiphertextLength];
        byte[] encryptedMessage = new byte[encryptedMessageLength];

        System.arraycopy(combinedData, 0, iv, 0, ivLength);
        System.arraycopy(combinedData, ivLength, encapsulatedCiphertext, 0, kyberCiphertextLength);
        System.arraycopy(combinedData, ivLength + kyberCiphertextLength, encryptedMessage, 0, encryptedMessageLength);

        KyberKEMExtractor kemExtractor = new KyberKEMExtractor(kyberPrivateKey);
        byte[] sharedSecret = kemExtractor.extractSecret(encapsulatedCiphertext);
        SecretKey aesKey = new SecretKeySpec(sharedSecret, 0, 32, "AES");

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));
        byte[] decryptedData = cipher.doFinal(encryptedMessage);

        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    // 5️⃣ Sign a message using Dilithium
    public String signMessage(String message) {
        DilithiumSigner signer = new DilithiumSigner();
        signer.init(true, dilithiumPrivateKey);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] signature = signer.generateSignature(messageBytes);
        return Base64.getEncoder().encodeToString(signature);
    }

    // 6️⃣ Verify a signed message using Dilithium
    public boolean verifySignature(String message, String signature) {
        DilithiumSigner verifier = new DilithiumSigner();
        verifier.init(false, dilithiumPublicKey);
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return verifier.verifySignature(messageBytes, signatureBytes);
    }
}
