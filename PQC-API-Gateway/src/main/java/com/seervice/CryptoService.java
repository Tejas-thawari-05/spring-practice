//package com.seervice;
//
//import java.nio.charset.StandardCharsets;
//import java.security.SecureRandom;
//import java.util.Base64;
//
//import javax.crypto.Cipher;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.GCMParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
//import org.bouncycastle.crypto.SecretWithEncapsulation;
//import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyGenerationParameters;
//import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyPairGenerator;
//import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
//import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
//import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
//import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumSigner;
//import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMGenerator;
//import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyGenerationParameters;
//import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyPairGenerator;
//import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
//import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
//import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPublicKeyParameters;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CryptoService {
//    private final SecureRandom secureRandom = new SecureRandom();
//
//    private KyberPublicKeyParameters kyberPublicKey;
//    private KyberPrivateKeyParameters kyberPrivateKey;
//    
//    private DilithiumPrivateKeyParameters dilithiumPrivateKey;
//    private DilithiumPublicKeyParameters dilithiumPublicKey;
//
//    public CryptoService() {
//        generateKyberKeys();
//        generateDilithiumKeys();
//    }
//
//    // Generate Kyber Key Pair
//    private void generateKyberKeys() {
//        KyberKeyPairGenerator keyPairGen = new KyberKeyPairGenerator();
//        keyPairGen.init(new KyberKeyGenerationParameters(secureRandom, KyberParameters.kyber1024));
//        AsymmetricCipherKeyPair keyPair = keyPairGen.generateKeyPair();
//        this.kyberPublicKey = (KyberPublicKeyParameters) keyPair.getPublic();
//        this.kyberPrivateKey = (KyberPrivateKeyParameters) keyPair.getPrivate();
//    }
//
//    // Generate Dilithium Key Pair
//    private void generateDilithiumKeys() {
//        DilithiumKeyPairGenerator keyPairGen = new DilithiumKeyPairGenerator();
//        keyPairGen.init(new DilithiumKeyGenerationParameters(secureRandom, DilithiumParameters.dilithium5));
//        AsymmetricCipherKeyPair keyPair = keyPairGen.generateKeyPair();
//        this.dilithiumPublicKey = (DilithiumPublicKeyParameters) keyPair.getPublic();
//        this.dilithiumPrivateKey = (DilithiumPrivateKeyParameters) keyPair.getPrivate();
//    }
//
//    // Encrypt message using Kyber + AES-GCM
//    public String encryptMessage(String message) throws Exception {
//        KyberKEMGenerator encapsulator = new KyberKEMGenerator(secureRandom);
//        SecretWithEncapsulation encapsulatedSecret = encapsulator.generateEncapsulated(kyberPublicKey);
//        byte[] sharedSecret = encapsulatedSecret.getSecret();
//
//        // Convert to AES Key
//        SecretKey aesKey = new SecretKeySpec(sharedSecret, 0, 32, "AES");
//
//        // AES-GCM Encryption
//        byte[] iv = new byte[12];
//        secureRandom.nextBytes(iv);
//        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//        cipher.init(Cipher.ENCRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));
//
//        byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
//        byte[] encapsulatedCiphertext = encapsulatedSecret.getEncapsulation();
//        byte[] finalData = new byte[iv.length + encapsulatedCiphertext.length + encryptedData.length];
//
//        System.arraycopy(iv, 0, finalData, 0, iv.length);
//        System.arraycopy(encapsulatedCiphertext, 0, finalData, iv.length, encapsulatedCiphertext.length);
//        System.arraycopy(encryptedData, 0, finalData, iv.length + encapsulatedCiphertext.length, encryptedData.length);
//
//        return Base64.getEncoder().encodeToString(finalData);
//    }
//
//    // Generate Signature using Dilithium
//    public String generateSignature(String message) {
//        DilithiumSigner signer = new DilithiumSigner();
//        signer.init(true, dilithiumPrivateKey);
//        byte[] signature = signer.generateSignature(message.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(signature);
//    }
//}


package com.seervice;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.SecretWithEncapsulation;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumPublicKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.dilithium.DilithiumSigner;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMExtractor;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKEMGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberPublicKeyParameters;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {
    private final SecureRandom secureRandom = new SecureRandom();

    private KyberPublicKeyParameters kyberPublicKey;
    private KyberPrivateKeyParameters kyberPrivateKey;
    
    private DilithiumPrivateKeyParameters dilithiumPrivateKey;
    private DilithiumPublicKeyParameters dilithiumPublicKey;

    public CryptoService() {
        generateKyberKeys();
        generateDilithiumKeys();
    }

    // Generate Kyber Key Pair
    private void generateKyberKeys() {
        KyberKeyPairGenerator keyPairGen = new KyberKeyPairGenerator();
        keyPairGen.init(new KyberKeyGenerationParameters(secureRandom, KyberParameters.kyber1024));
        AsymmetricCipherKeyPair keyPair = keyPairGen.generateKeyPair();
        this.kyberPublicKey = (KyberPublicKeyParameters) keyPair.getPublic();
        this.kyberPrivateKey = (KyberPrivateKeyParameters) keyPair.getPrivate();
    }

    // Generate Dilithium Key Pair
    private void generateDilithiumKeys() {
        DilithiumKeyPairGenerator keyPairGen = new DilithiumKeyPairGenerator();
        keyPairGen.init(new DilithiumKeyGenerationParameters(secureRandom, DilithiumParameters.dilithium5));
        AsymmetricCipherKeyPair keyPair = keyPairGen.generateKeyPair();
        this.dilithiumPublicKey = (DilithiumPublicKeyParameters) keyPair.getPublic();
        this.dilithiumPrivateKey = (DilithiumPrivateKeyParameters) keyPair.getPrivate();
    }

    // Encrypt message using Kyber + AES-GCM
    public String encryptMessage(String message) throws Exception {
        KyberKEMGenerator encapsulator = new KyberKEMGenerator(secureRandom);
        SecretWithEncapsulation encapsulatedSecret = encapsulator.generateEncapsulated(kyberPublicKey);
        byte[] sharedSecret = encapsulatedSecret.getSecret();

        // Convert to AES Key
        SecretKey aesKey = new SecretKeySpec(sharedSecret, 0, 32, "AES");

        // AES-GCM Encryption
        byte[] iv = new byte[12];
        secureRandom.nextBytes(iv);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));

        byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        byte[] encapsulatedCiphertext = encapsulatedSecret.getEncapsulation();
        byte[] finalData = new byte[iv.length + encapsulatedCiphertext.length + encryptedData.length];

        System.arraycopy(iv, 0, finalData, 0, iv.length);
        System.arraycopy(encapsulatedCiphertext, 0, finalData, iv.length, encapsulatedCiphertext.length);
        System.arraycopy(encryptedData, 0, finalData, iv.length + encapsulatedCiphertext.length, encryptedData.length);

        return Base64.getEncoder().encodeToString(finalData);
    }

    // Decrypt message using Kyber private key
    public String decryptMessage(String encryptedMessage) throws Exception {
        byte[] decodedData = Base64.getDecoder().decode(encryptedMessage);

        byte[] iv = new byte[12];
        System.arraycopy(decodedData, 0, iv, 0, iv.length);

        byte[] encapsulatedCiphertext = new byte[KyberParameters.kyber1024.getSessionKeySize()];
        System.arraycopy(decodedData, iv.length, encapsulatedCiphertext, 0, encapsulatedCiphertext.length);

        byte[] encryptedData = new byte[decodedData.length - iv.length - encapsulatedCiphertext.length];
        System.arraycopy(decodedData, iv.length + encapsulatedCiphertext.length, encryptedData, 0, encryptedData.length);

        // Extract shared secret using Kyber private key
        KyberKEMExtractor kemExtractor = new KyberKEMExtractor(kyberPrivateKey);
        byte[] sharedSecret = kemExtractor.extractSecret(encapsulatedCiphertext);

        SecretKey aesKey = new SecretKeySpec(sharedSecret, 0, 32, "AES");

        // AES-GCM Decryption
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, aesKey, new GCMParameterSpec(128, iv));
        byte[] decryptedData = cipher.doFinal(encryptedData);

        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    // Generate Signature using Dilithium
    public String generateSignature(String message) {
        DilithiumSigner signer = new DilithiumSigner();
        signer.init(true, dilithiumPrivateKey);
        byte[] signature = signer.generateSignature(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(signature);
    }

    // Verify Signature using Dilithium public key
    public boolean verifySignature(String message, String signature) {
        DilithiumSigner verifier = new DilithiumSigner();
        verifier.init(false, dilithiumPublicKey);

        byte[] signatureBytes = Base64.getDecoder().decode(signature);
        return verifier.verifySignature(message.getBytes(StandardCharsets.UTF_8), signatureBytes);
    }
}

