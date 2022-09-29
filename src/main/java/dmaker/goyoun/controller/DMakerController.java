package dmaker.goyoun.controller;


import dmaker.goyoun.dto.CreateDeveloper;
import dmaker.goyoun.dto.DeveloperDetailDto;
import dmaker.goyoun.dto.DeveloperDto;
import dmaker.goyoun.dto.EditDeveloper;
import dmaker.goyoun.exception.DMakerErrorResponse;
import dmaker.goyoun.exception.DMakerException;
import dmaker.goyoun.service.DMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author GoYoun
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class DMakerController {
  
  private final DMakerService dMakerService;
  
  @GetMapping("/developers")
  public List<DeveloperDto> getAllDevelopers() {
    log.info("Get /developers HTTP/1.1");
    
    return dMakerService.getAllEmployedDevelopers();
  }
  
  @GetMapping("/developer/{memberId}")
  public DeveloperDetailDto getDeveloperDetail(@PathVariable String memberId) {
    dMakerService.getDeveloperDetail(memberId);
    return dMakerService.getDeveloperDetail(memberId);
  }
  
  @PostMapping("/create-developer")
  public CreateDeveloper.Response createDevelopers(
    @Valid @RequestBody CreateDeveloper.Request request
  ) {
    log.info("request : {} ", request);
    
    dMakerService.createDeveloper(request);
   
    return dMakerService.createDeveloper(request);
  }
  
  @PutMapping("developer/{memberId}")
  public DeveloperDetailDto editDeveloper(
    @PathVariable String memberId,
    @Valid @RequestBody EditDeveloper.Request request
  ) {
    log.info("GET/ developers HTTP/1.1");
      return dMakerService.editDeveloper(memberId, request);
  }
  
  @DeleteMapping("/developer/{memberId}")
  public DeveloperDetailDto deleteDeveloper(String memberId) {
    
    return dMakerService.deleteDeveloper(memberId);
  }
  

  
}
