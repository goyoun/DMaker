package dmaker.goyoun.exception;

import lombok.*;

/**
 * Create by GoYoun 2022/09/23.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DMakerErrorResponse {
  private DMakerErrorCode errorCode;
  private String errorMessage;
  
}
