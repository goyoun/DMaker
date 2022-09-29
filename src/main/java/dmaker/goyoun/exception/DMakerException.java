package dmaker.goyoun.exception;

import lombok.*;

/**
 * Create by GoYoun 2022/09/22.
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
public class DMakerException extends RuntimeException{
  
  private DMakerErrorCode dMakerErrorCode;
  private String detailMessage;
  
  public DMakerException(DMakerErrorCode errorCode) {
    super(errorCode.getMessage());
    this.dMakerErrorCode = errorCode;
    this.detailMessage = errorCode.getMessage();
  }
  
  public DMakerException(DMakerErrorCode errorCode, String detailMessage) {
    super(detailMessage);
    this.dMakerErrorCode = errorCode;
    this.detailMessage = errorCode.getMessage();
  }
  
  
}
