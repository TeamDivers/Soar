package com.konkuk.soar.member.service;

import com.konkuk.soar.common.domain.File;
import com.konkuk.soar.common.dto.file.response.FileResponseDto;
import com.konkuk.soar.common.service.AwsS3Service;
import com.konkuk.soar.common.service.FileService;
import com.konkuk.soar.global.exception.NotFoundException;
import com.konkuk.soar.member.domain.Member;
import com.konkuk.soar.member.dto.request.MemberUpdateRequestDto;
import com.konkuk.soar.member.dto.response.MemberResponseDto;
import com.konkuk.soar.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class SimpleMemberService implements MemberService {

  private final MemberRepository memberRepository;
  private final AwsS3Service awsS3Service;
  private final FileService fileService;

  @Override
  @Transactional
  public void registerMember(Member member) {
    if (memberRepository.existsByEmail(member.getEmail())) {
      return;
    }

    member.setName("임시유저");
    memberRepository.save(member);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Member> findMemberByEmail(String email) {
    return memberRepository.getByEmail(email);
  }

  @Override
  @Transactional(readOnly = true)
  public MemberResponseDto getMemberById(Long id) {
    Member member = memberRepository.findById(id)
        .orElseThrow(() -> NotFoundException.MEMBER_NOT_FOUND);
    String thumbnail;
    if (member.getThumbnail() == null){
      thumbnail = null;
    } else {
      thumbnail = member.getThumbnail().getUrl();
    }

    return MemberResponseDto.builder()
        .id(member.getId())
        .name(member.getName())
        .email(member.getEmail())
        .phoneNumber(member.getPhoneNumber())
        .education(member.getEducation())
        .career(member.getCareer())
        .thumbnail(thumbnail)
        .build();
  }

  @Override
  @Transactional
  public MemberResponseDto updateMember(MemberUpdateRequestDto updateMember) {
    Member member = memberRepository.findById(updateMember.getId())
        .orElseThrow(() -> NotFoundException.MEMBER_NOT_FOUND);
    member.setName(updateMember.getName());
    member.setCareer(updateMember.getCareer());
    member.setEducation(updateMember.getEducation());
    member.setPhoneNumber(updateMember.getPhoneNumber());

    return MemberResponseDto.builder()
        .name(member.getName())
        .email(member.getEmail())
        .phoneNumber(member.getPhoneNumber())
        .education(member.getEducation())
        .career(member.getCareer())
        .build();
  }

  @Override
  @Transactional
  public MemberResponseDto uploadProfile(Long memberId, MultipartFile multipartFile){
    FileResponseDto uploadResponse = awsS3Service.uploadFile(memberId+"/profile", multipartFile);
    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> NotFoundException.MEMBER_NOT_FOUND);
    File file = fileService.findById(uploadResponse.getFileId())
        .orElseThrow(()->NotFoundException.FILE_NOT_FOUND);
    member.setThumbnail(file);

    return MemberResponseDto.builder()
        .id(member.getId())
        .name(member.getName())
        .email(member.getEmail())
        .phoneNumber(member.getPhoneNumber())
        .education(member.getEducation())
        .career(member.getCareer())
        .thumbnail(file.getUrl())
        .build();
  }

  @Override
  @Transactional
  public Optional<Member> findById(Long id) {
    return memberRepository.findById(id);
  }

  @Override
  @Transactional
  public List<MemberResponseDto> searchByKeyword(String keyword, int size) {
    List<Member> list = memberRepository.findAllByNameContains(keyword, Pageable.ofSize(size));
    return list.stream()
        .map(member -> MemberResponseDto.builder()
            .name(member.getName())
            .email(member.getEmail())
            .phoneNumber(member.getPhoneNumber())
            .education(member.getEducation())
            .career(member.getCareer())
            .build())
        .toList();
  }
}