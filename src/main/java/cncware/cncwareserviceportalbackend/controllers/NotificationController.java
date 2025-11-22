package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.dtos.input.IdInputDto;
import cncware.cncwareserviceportalbackend.dtos.input.NotificationInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.NotificationOutputDto;
import cncware.cncwareserviceportalbackend.services.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationOutputDto> create(@Valid @RequestBody NotificationInputDto dto){
        return ResponseEntity.ok(notificationService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<NotificationOutputDto>> getAll(){
        return ResponseEntity.ok(notificationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationOutputDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(notificationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationOutputDto> update(@PathVariable Integer id, @Valid @RequestBody NotificationInputDto dto){
        return ResponseEntity.ok(notificationService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<NotificationOutputDto> assignUserToNotification(@PathVariable Integer id, @Valid @RequestBody IdInputDto dto){
        return  ResponseEntity.ok(notificationService.assignUserToNotification(id, dto.getId()));
    }

}
