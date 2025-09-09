package org.puchori.springbootproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.puchori.springbootproject.dto.upload.UploadFileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@Log4j2
public class UpDownController {

  @Value("${org.puchori.upload.path}") // import 시에 springframework으로 시작하는 value
  private String uploadPath;

  @Operation(
          summary = "Upload Post"
          ,description = "Post 방식으로 파일 등록"
  )
  @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public String upload(UploadFileDTO uploadFileDTO){
    log.info(uploadFileDTO);

    if(uploadFileDTO.getFiles() != null) {
      uploadFileDTO.getFiles().forEach(multipartFile -> {

        String originalName = multipartFile.getOriginalFilename();
        log.info(originalName);

        String uuid = UUID.randomUUID().toString();
        Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

        try{
          multipartFile.transferTo(savePath);
        } catch(IOException e) {
          e.printStackTrace();
        }



      }); //end each
    } // end if

    return null;

  }



}
