package com.kevin.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${directory.path}")
    String filePath;

    @RequestMapping(value="fileUpload", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileUpload(@RequestParam("file")MultipartFile file) {
        System.out.println("test fileUpload");
        if(file.isEmpty()) {
           return  ResponseEntity.status(HttpStatus.OK).body("file is empty");
        }
        try {
            String name = file.getOriginalFilename();
            File desFile = new File(filePath + name);
            FileUtils.copyInputStreamToFile(file.getInputStream(), desFile);
            return ResponseEntity.status(HttpStatus.OK).body("file upload done");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("file upload fail" + e.getMessage());
        }
    }

    @RequestMapping(value="fileBatchUpload", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileBatchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        System.out.println(files.size());
        int size = files.size();
        try {
            MultipartFile file;
            String name;
            for (int i = 0; i < size; i++) {
                file = files.get(i);
                name = file.getOriginalFilename();
                File desFile = new File(filePath + name);
                FileUtils.copyInputStreamToFile(file.getInputStream(), desFile);
            }
            return ResponseEntity.status(HttpStatus.OK).body("file upload done");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("file upload fail" + e.getMessage());
        }
    }

    @RequestMapping(value="fileDownload", method = RequestMethod.POST)
    public ResponseEntity<?> handleFileDownload(@RequestParam("filename") String fileName) {
        try {
            String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            File file = new File(filePath + fileName);
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body("file download fail" + e.getMessage());

        }

    }

    @RequestMapping(value = "filePage", method = RequestMethod.GET)
    public ModelAndView handleFilePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/files/filePage");
        return modelAndView;
    }

}
