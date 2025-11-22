package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.dtos.input.StatusInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.StatusOutputDto;
import cncware.cncwareserviceportalbackend.services.StatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuses")
@RequiredArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @PostMapping
    public ResponseEntity<StatusOutputDto> create(@Valid @RequestBody StatusInputDto dto){
        return ResponseEntity.ok(statusService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<StatusOutputDto>> getAll(){
        return ResponseEntity.ok(statusService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusOutputDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(statusService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusOutputDto> update(@PathVariable Integer id, @Valid @RequestBody StatusInputDto dto){
        return ResponseEntity.ok(statusService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        statusService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
