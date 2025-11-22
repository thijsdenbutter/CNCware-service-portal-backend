package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.dtos.input.IdInputDto;
import cncware.cncwareserviceportalbackend.dtos.input.TicketInputDto;
import cncware.cncwareserviceportalbackend.dtos.list.TicketListDto;
import cncware.cncwareserviceportalbackend.dtos.output.TicketOutputDto;
import cncware.cncwareserviceportalbackend.services.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketOutputDto> create(@Valid @RequestBody TicketInputDto dto){
        return ResponseEntity.ok(ticketService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<TicketListDto>> getAll(){
        return ResponseEntity.ok(ticketService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketOutputDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketOutputDto> update(@PathVariable Integer id, @Valid @RequestBody TicketInputDto dto){
        return ResponseEntity.ok(ticketService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<TicketOutputDto> assignUserToTicket(@PathVariable Integer id, @Valid @RequestBody IdInputDto dto){
        return ResponseEntity.ok(ticketService.assignUserToTicket(id, dto.getId()));
    }

    @PutMapping("/{id}/timer")
    public ResponseEntity<TicketOutputDto> assignTimerToTicket(@PathVariable Integer id, @Valid @RequestBody IdInputDto dto){
        return ResponseEntity.ok(ticketService.assignTimerToTicket(id, dto.getId()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TicketOutputDto> assignStatusToTicket(@PathVariable Integer id, @Valid @RequestBody IdInputDto dto){
        return ResponseEntity.ok(ticketService.assignStatusToTicket(id, dto.getId()));
    }

}
