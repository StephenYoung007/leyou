package tech.ityoung.upload.controller;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tech.ityoung.upload.service.UploadService;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private UploadService service;

    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = service.uploadImage(file);
        if (StringUtils.isBlank(url)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    private final String ROOT = "C:\\Users\\XPS\\Desktop\\Java\\leyou\\leyou-upload\\src\\main\\resources\\static";

    @GetMapping(value = "imageget")
    public ResponseEntity<?> download(String imageurl) throws IOException, URISyntaxException {
        String filepath = "C:\\Users\\XPS\\Desktop\\Java\\leyou\\leyou-upload\\src\\main\\resources\\static\\" + imageurl;
        File file = new File(filepath);
        HttpHeaders headers = new HttpHeaders();
        if (!file.exists()) {
            URI location = new URI(filepath);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(location);
            responseHeaders.set("MyResponseHeader", "MyValue");
            return new ResponseEntity<String>("文件路径不正确！", responseHeaders, HttpStatus.NOT_FOUND);
            // return ResponseEntity.noContent().build();
            // return ResponseEntity.notFound().build();
        }
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        byte[] bytes = FileUtils.readFileToByteArray(file);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.CREATED);

    }

    /*@GetMapping("imageget")
    public ResponseEntity downloadImage(String imageurl) throws IOException {
        UrlResource image = null;
        try {
            image = service.getImage(imageurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        if (!image.exists()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(image.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(image.getFilename(), "UTF-8"))
                .body(image);
    }*/
}
