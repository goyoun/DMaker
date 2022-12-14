package dmaker.goyoun.entity;

import dmaker.goyoun.status.StatusCode;
import dmaker.goyoun.type.DeveloperLevel;
import dmaker.goyoun.type.DeveloperSkillType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Developer {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;
  
  @Enumerated(EnumType.STRING)
  private DeveloperLevel developerLevel;
  
  @Enumerated(EnumType.STRING)
  private DeveloperSkillType developerSkillType;
  
  private Integer experienceYears;
  private String memberId;
  private String name;
  private Integer age;
  
  @CreatedDate
  private LocalDateTime createdAt;
  
  @LastModifiedDate
  private LocalDateTime updatedAt;
  
  @Enumerated(EnumType.STRING)
  private StatusCode statusCode;



  
}