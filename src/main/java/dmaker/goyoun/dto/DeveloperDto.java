package dmaker.goyoun.dto;

import dmaker.goyoun.entity.Developer;
import dmaker.goyoun.type.DeveloperLevel;
import dmaker.goyoun.type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Create by GoYoun 2022/09/22.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDto {
  private DeveloperLevel developerLevel;
  private DeveloperSkillType developerSkillType;
  private String memberId;
  
  public static DeveloperDto fromEntity(Developer developer) {
    return DeveloperDto.builder()
      .developerLevel(developer.getDeveloperLevel())
      .developerSkillType(developer.getDeveloperSkillType())
      .memberId(developer.getMemberId())
      .build();
  }
}
