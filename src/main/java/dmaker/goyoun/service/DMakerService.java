package dmaker.goyoun.service;

import dmaker.goyoun.dto.CreateDeveloper;
import dmaker.goyoun.dto.DeveloperDetailDto;
import dmaker.goyoun.dto.DeveloperDto;
import dmaker.goyoun.dto.EditDeveloper;
import dmaker.goyoun.entity.Developer;
import dmaker.goyoun.entity.RetiredDeveloper;
import dmaker.goyoun.exception.DMakerException;
import dmaker.goyoun.exception.DMakerErrorResponse;
import dmaker.goyoun.repository.DeveloperRepository;
import dmaker.goyoun.repository.RetiredDeveloperRepository;
import dmaker.goyoun.status.StatusCode;
import dmaker.goyoun.type.DeveloperLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

import static dmaker.goyoun.exception.DMakerErrorCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DMakerService {
  private final DeveloperRepository developerRepository;
  private final RetiredDeveloperRepository retiredDeveloperRepository;
  private final EntityManager em;
  
  @Transactional
  public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
    validateCreateDeveloperRequest(request);
//      business logic start
    Developer developer = Developer.builder()
      .developerLevel(request.getDeveloperLevel())
      .developerSkillType(request.getDeveloperSkillType())
      .experienceYears(request.getExperienceYears())
      .statusCode(StatusCode.EMPLOYED)
      .name(request.getName())
      .age(request.getAge())
      .build();
//      1계좌에서 1만원줄임
    developerRepository.save(developer);
    return CreateDeveloper.Response.fromEntity(developer);
  }
  
  private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {
    validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());
    
    developerRepository.findByMemberId(request.getMemberId())
      .ifPresent((developer -> {
        throw new DMakerException(DUPLICATED_MEMBER_ID);
      }));
  }
  
  public List<DeveloperDto> getAllEmployedDevelopers() {
    return developerRepository.findDevelopersStatusCodeEquals(StatusCode.EMPLOYED)
      .stream().map(DeveloperDto::fromEntity)
      .collect(Collectors.toList());
  }
  
  public DeveloperDetailDto getDeveloperDetail(String memberId) {
    return developerRepository.findByMemberId(memberId)
      .map(DeveloperDetailDto::fromEntity).orElseThrow(() -> new DMakerException(NO_DEVELOPER));
  }
  
  @Transactional
  public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
    validateEditDeveloperRequest(request, memberId);
    
    Developer developer = developerRepository.findByMemberId(memberId).orElseThrow(()-> new DMakerException(NO_DEVELOPER));
    
    developer.setDeveloperLevel(request.getDeveloperLevel());
    developer.setDeveloperSkillType(request.getDeveloperSkillType());
    developer.setExperienceYears(request.getExperienceYears());
    
    return DeveloperDetailDto.fromEntity(developer);
  }
  
  private void validateEditDeveloperRequest(EditDeveloper.Request request, String memberId) {
    validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYears());
  }
  
  private void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYears) {
    if (developerLevel == DeveloperLevel.SENIOR && experienceYears < 10) {
      throw new DMakerException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
    }
    if (developerLevel == DeveloperLevel.JUNGNIOR && (experienceYears < 4 || experienceYears > 10)) {
      throw new DMakerException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
    }
    if (developerLevel == DeveloperLevel.JUNIOR && experienceYears > 4) {
      throw new DMakerException(LEVEL_EXPERIENCE_YEAR_NOT_MATCHED);
    }
  }
  
  
  @Transactional
  public DeveloperDetailDto deleteDeveloper(String memberId) {
//    1. EMPLOYED -> RETIRED
    Developer developer = developerRepository.findByMemberId(memberId).orElseThrow(() -> new DMakerException(NO_DEVELOPER));
    developer.setStatusCode(StatusCode.RETIRED);
    
//    2. SAVE INTO RETIREDDEVELOPER
    RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
      .memberId(memberId)
      .name(developer.getName())
      .build();
    retiredDeveloperRepository.save(retiredDeveloper);
    return DeveloperDetailDto.fromEntity(developer);
  }
  

  
}



