package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.dtos.input.TimerInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.TimerOutputDto;
import cncware.cncwareserviceportalbackend.dtos.update.TimerUpdateDto;
import cncware.cncwareserviceportalbackend.services.TimerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timers")
@RequiredArgsConstructor
public class TimerController {

    private final TimerService timerService;

    @PostMapping
    public ResponseEntity<TimerOutputDto> create(@Valid @RequestBody TimerInputDto dto){
        return ResponseEntity.ok(timerService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimerOutputDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(timerService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimerOutputDto> update(@PathVariable Integer id, @Valid @RequestBody TimerUpdateDto dto){
        return ResponseEntity.ok(timerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        timerService.delete(id);
        return ResponseEntity.notFound().build();
    }
}

