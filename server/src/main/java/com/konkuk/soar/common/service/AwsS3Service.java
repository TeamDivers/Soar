package com.konkuk.soar.common.service;

import com.konkuk.soar.common.dto.file.request.FileCreateDto;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

  private final S3Client s3Client;
  private final FileService fileService;
  private String awsUrl = "amazonaws.com/";

  @Value("${cloud.aws.s3.bucket}")
  private String bucketName;

  @Value("${cloud.aws.region.static}")
  private String region;

  public FileResponseDto uploadFile(String propertyName, MultipartFile multipartFile) {
    String key = propertyName + "/" + multipartFile.getOriginalFilename();
    try {
      PutObjectRequest putOb = PutObjectRequest.builder()
          .bucket(bucketName)
          .key(key)
          .build();
      s3Client.putObject(
          putOb,
          RequestBody.fromInputStream(multipartFile.getInputStream(), multipartFile.getSize())
      );
    } catch (IOException e) {
      e.printStackTrace();
    }

    String url = "https://" + bucketName + ".s3." + region + "." + awsUrl + key;
    FileCreateDto createDto = FileCreateDto.builder()
        .type(multipartFile.getContentType())
        .file(multipartFile)
        .fileName(multipartFile.getOriginalFilename())
        .build();

    FileResponseDto responseDto = fileService.createFile(createDto, key, url);
    return responseDto;
  }
}
