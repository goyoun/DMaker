package dmaker.goyoun.dto;

import dmaker.goyoun.entity.Developer;
import dmaker.goyoun.type.DeveloperLevel;
import dmaker.goyoun.type.DeveloperSkillType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Create by GoYoun 2022/09/21.
 */
public class EditDeveloper {
  
  
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


  }
  
  
}
