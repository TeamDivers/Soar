package com.konkuk.soar.common.controller;

import com.konkuk.soar.common.dto.BaseResponse;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.common.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/aws")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class AwsController {
  private final AwsS3Service awsS3Service;

  @PostMapping("/upload")
  public BaseResponse<FileResponseDto> uploadPortfolioFile(@RequestParam("file") MultipartFile multipartFile) {
    FileResponseDto responseDto = awsS3Service.uploadFile("portfolio", multipartFile);
    BaseResponse<FileResponseDto> response = BaseResponse.success(responseDto);
    return response;
  }

}
