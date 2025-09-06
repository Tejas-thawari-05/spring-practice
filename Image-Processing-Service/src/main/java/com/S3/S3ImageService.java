//package com.S3;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardCopyOption;
//
//@Service
//public class S3ImageService {
//
//    private final S3Client s3Client;
//
//    @Value("${aws.bucket}")
//    private String bucketName;
//
//    public S3ImageService(@Value("${aws.accessKeyId}") String accessKey,
//                     @Value("${aws.secretKey}") String secretKey,
//                     @Value("${aws.region}") String region) {
//
//        s3Client = S3Client.builder()
//                .region(Region.of(region))
//                .credentialsProvider(
//                        StaticCredentialsProvider.create(
//                                AwsBasicCredentials.create(accessKey, secretKey)
//                        )
//                )
//                .build();
//    }
//
//    public void uploadFile(String key, InputStream inputStream) throws Exception {
//        Path tempFile = Files.createTempFile("upload-", key);
//        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
//
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        s3Client.putObject(putObjectRequest, tempFile);
//    }
//}


package com.S3;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import jakarta.annotation.PostConstruct;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@Service
public class S3ImageService {

    @Value("${aws.bucket}")
    private String bucketName;
    @Value("${aws.accessKeyId}")
    private String accessKey;
    @Value("${aws.secretKey}")
    private String secretKey;
    @Value("${aws.region}")
    private String region;

    private S3Client s3Client;

    @PostConstruct
    public void init() {
        s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(accessKey, secretKey)
                        )
                ).build();
    }

    public String transformAndUpload(InputStream inputStream, String originalName, String operation) throws Exception {
        String transformedKey = UUID.randomUUID() + "-" + operation + "-" + originalName;
        File outputFile = File.createTempFile("transform-", transformedKey);

        switch (operation) {
            case "resize":
                Thumbnails.of(inputStream).size(300, 300).toFile(outputFile);
                break;
            case "rotate":
                Thumbnails.of(inputStream).rotate(90).toFile(outputFile);
                break;
            case "crop":
                Thumbnails.of(inputStream).sourceRegion(100, 100, 200, 200).size(200, 200).toFile(outputFile);
                break;
            case "flip":
                BufferedImage original = Thumbnails.of(inputStream).scale(1).asBufferedImage();
                BufferedImage flipped = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
                for (int i = 0; i < original.getWidth(); i++) {
                    for (int j = 0; j < original.getHeight(); j++) {
                        flipped.setRGB(original.getWidth() - i - 1, j, original.getRGB(i, j));
                    }
                }
                Thumbnails.of(flipped).scale(1).toFile(outputFile);
                break;
            case "grayscale":
                Thumbnails.of(inputStream).scale(1).outputFormat("jpg")
                        .imageType(BufferedImage.TYPE_BYTE_GRAY).toFile(outputFile);
                break;
            case "watermark":
                BufferedImage watermark = ImageIO.read(new File("src/main/resources/watermark.png"));
                Thumbnails.of(inputStream).size(500, 500)
                        .watermark(Positions.BOTTOM_RIGHT, watermark, 0.5f).toFile(outputFile);
                break;
            case "compress":
                Thumbnails.of(inputStream).scale(1).outputQuality(0.5).toFile(outputFile);
                break;
            case "format":
                Thumbnails.of(inputStream).scale(1).outputFormat("png").toFile(outputFile);
                break;
            default:
                throw new IllegalArgumentException("Unsupported operation: " + operation);
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(transformedKey)
                .build();

        s3Client.putObject(putObjectRequest, outputFile.toPath());

        return "https://" + bucketName + ".s3.amazonaws.com/" + transformedKey;
    }
    

        public void resizeAndUpload(String key, InputStream inputStream, int width, int height) throws Exception {
            
            Path tempFile = Files.createTempFile("upload-resized-", key);
            
           
            Thumbnails.of(inputStream)
                      .size(width, height) 
                      .toFile(tempFile.toFile());

                      PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, tempFile);
        }
    
        public void rotateImage(String key, InputStream inputStream, int angle) throws Exception {
            Path tempFile = Files.createTempFile("upload-", key);
            
            Thumbnails.of(inputStream)
                    .rotate(angle)
                    .scale(1)  
                    .toFile(tempFile.toFile());

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            s3Client.putObject(putObjectRequest, tempFile);
        }
        
        
        public String convertFormat(String key, String format) throws Exception {
            GetObjectRequest getReq = GetObjectRequest.builder()
                    .bucket(bucketName).key(key).build();
            InputStream originalStream = s3Client.getObject(getReq);

            File outputFile = File.createTempFile("converted-", "." + format);
            Thumbnails.of(originalStream).scale(1).outputFormat(format).toFile(outputFile);

            String newKey = "converted-" + UUID.randomUUID() + "." + format;
            uploadToS3(newKey, outputFile.toPath());
            return getUrl(newKey);
        }

        public List<Map<String, String>> listAllImages() {
            ListObjectsV2Request listReq = ListObjectsV2Request.builder().bucket(bucketName).build();
            ListObjectsV2Response response = s3Client.listObjectsV2(listReq);

            List<Map<String, String>> results = new ArrayList<>();
            for (S3Object obj : response.contents()) {
                Map<String, String> metadata = new HashMap<>();
                metadata.put("key", obj.key());
                metadata.put("size (KB)", String.valueOf(obj.size() / 1024));
                metadata.put("url", getUrl(obj.key()));
                results.add(metadata);
            }
            return results;
        }

        private void uploadToS3(String key, Path path) {
            PutObjectRequest putReq = PutObjectRequest.builder()
                    .bucket(bucketName).key(key).build();
            s3Client.putObject(putReq, path);
        }

        private String getUrl(String key) {
            return "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + key;
        }
    
}













