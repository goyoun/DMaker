package dmaker.goyoun.dto;

import dmaker.goyoun.entity.Developer;
import dmaker.goyoun.status.StatusCode;
import dmaker.goyoun.type.DeveloperLevel;
import dmaker.goyoun.type.DeveloperSkillType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Create by GoYoun 2022/09/22.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDetailDto {
  
  private DeveloperLevel developerLevel;
  private DeveloperSkillType developerSkillType;
  private Integer experienceYears;
  private String memberId;
  private StatusCode statusCode;
  private String name;
  private Integer age;
  
  public static DeveloperDetailDto fromEntity(Developer developer) {
    return DeveloperDetailDto.builder()
      .developerLevel(developer.getDeveloperLevel())
      .developerSkillType(developer.getDeveloperSkillType())
      .experienceYears(developer.getExperienceYears())
      .memberId(developer.getMemberId())
      .statusCode(developer.getStatusCode())
      .name(developer.getName())
      .age(developer.getAge())
      .build();
  }
}
