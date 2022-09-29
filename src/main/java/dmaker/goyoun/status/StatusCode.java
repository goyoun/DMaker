package dmaker.goyoun.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Create by GoYoun 2022/09/22.
 */
@Getter
@AllArgsConstructor
public enum StatusCode {
  EMPLOYED("고용"),
  RETIRED("퇴직");
  
  private final String description;
}
