package dmaker.goyoun.dto;

import dmaker.goyoun.entity.Developer;
import dmaker.goyoun.type.DeveloperLevel;
import dmaker.goyoun.type.DeveloperSkillType;
import lombok.*;

import javax.persistence.SequenceGenerators;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Create by GoYoun 2022/09/21.
 */
public class CreateDeveloper {
  
  
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @ToString
  public static class Request {
    @NotNull
    private DeveloperLevel developerLevel;
    @NotNull
    private DeveloperSkillType developerSkillType;
    
    @NotNull
    @Min(0)
    @Max(20)
    
    private Integer experienceYears;
    
    @NotNull
    @Size(min = 3, max = 50, message = "memberId Size must 3~50")
    private String memberId;
    @NotNull
    @Size(min = 3, max = 20, message = "Name Size must 3~50")
    private String name;
    
    @Min(18)
    private Integer age;
  }
  
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  public static class Response {
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private Integer experienceYears;
    
    private String memberId;
    
    public static Response fromEntity(Developer developer) {
      return Response.builder()
        .developerLevel(developer.getDeveloperLevel())
        .developerSkillType(developer.getDeveloperSkillType())
        .experienceYears(developer.getExperienceYears())
        .memberId(developer.getMemberId())
        .build();
    }
  }
  
  
}
