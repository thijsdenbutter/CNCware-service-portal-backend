package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.dtos.input.CompanyInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.CompanyListDto;
import cncware.cncwareserviceportalbackend.dtos.output.CompanyOutputDto;
import cncware.cncwareserviceportalbackend.services.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService CompanyService;

    @PostMapping
    public ResponseEntity<CompanyOutputDto> create(@Valid @RequestBody CompanyInputDto dto){
        return ResponseEntity.ok(CompanyService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<CompanyListDto>> getAll(){
        return ResponseEntity.ok(CompanyService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyOutputDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(CompanyService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyOutputDto> update(@PathVariable Integer id, @Valid @RequestBody CompanyInputDto dto){
        return ResponseEntity.ok(CompanyService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        CompanyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
