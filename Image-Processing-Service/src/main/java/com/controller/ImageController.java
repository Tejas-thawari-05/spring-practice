//package com.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.S3.S3ImageService;
//
//@RestController
//@RequestMapping("/api/upload")
//public class ImageController {
//
//    @Autowired
//    private S3ImageService s3Service;
//
//    @PostMapping
//    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
//        try {
//            s3Service.uploadFile(file.getOriginalFilename(), file.getInputStream());
//            return ResponseEntity.ok("Uploaded: " + file.getOriginalFilename());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
//        }
//    }
//}

package com.controller;

import com.S3.S3ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class ImageController {

    @Autowired
    private S3ImageService s3Service;

    @PostMapping("/{operation}")
    public ResponseEntity<String> transformAndUpload(@RequestParam("file") MultipartFile file,
                                                     @PathVariable("operation") String operation) {
        try {
            String url = s3Service.transformAndUpload(file.getInputStream(), file.getOriginalFilename(), operation);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Transformation failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/resize")
    public ResponseEntity<String> resizeImage(@RequestParam("file") MultipartFile file, 
                                               @RequestParam("width") int width, 
                                               @RequestParam("height") int height) {
        try {
            
            s3Service.resizeAndUpload(file.getOriginalFilename(), file.getInputStream(), width, height);
            return ResponseEntity.ok("Image resized and uploaded: " + file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Resize failed: " + e.getMessage());
        }
    }

    
    @PostMapping("/rotate")
    public ResponseEntity<String> rotateImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("angle") int angle) {
        try {
            s3Service.rotateImage(file.getOriginalFilename(), file.getInputStream(), angle);
            return ResponseEntity.ok("Image rotated and uploaded: " + file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Transformation failed: " + e.getMessage());
        }
    }
    
    
    @GetMapping("/list")
    public ResponseEntity<?> listAllImages() {
        return ResponseEntity.ok(s3Service.listAllImages());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getImage(@RequestParam("key") String key,
                                      @RequestParam("format") String format) {
        try {
            String formattedUrl = s3Service.convertFormat(key, format);
            return ResponseEntity.ok(formattedUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to retrieve in new format: " + e.getMessage());
        }
    }

}


