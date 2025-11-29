package cncware.cncwareserviceportalbackend.controllers;

import cncware.cncwareserviceportalbackend.dtos.input.IdInputDto;
import cncware.cncwareserviceportalbackend.dtos.input.MessageInputDto;
import cncware.cncwareserviceportalbackend.dtos.output.MessageOutputDto;
import cncware.cncwareserviceportalbackend.services.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessagesController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageOutputDto> create(@Valid @RequestBody MessageInputDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<MessageOutputDto>> getAll(){
        return ResponseEntity.ok(messageService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageOutputDto> getByID(@PathVariable Integer id){
        return ResponseEntity.ok(messageService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageOutputDto> update(@PathVariable Integer id, @Valid @RequestBody MessageInputDto dto){
        return ResponseEntity.ok(messageService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/ticket")
    public ResponseEntity<MessageOutputDto> assignTicketToMessage(@PathVariable Integer id, @Valid @RequestBody IdInputDto dto){
        return ResponseEntity.ok(messageService.assignTicketToMessage(id, dto.getId()));
    }

    @PostMapping(path = "/{id}/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MessageOutputDto> uploadFile(
            @PathVariable Integer id,
            @RequestPart("file") MultipartFile file) {

        MessageOutputDto output = messageService.uploadAttachment(id, file);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable Integer id) {

        var entity = messageService.getById(id);

        if (!entity.isHasAttachment()) {
            return ResponseEntity.notFound().build();
        }

        byte[] data = messageService.downloadAttachment(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + entity.getAttachmentName() + "\"")
                .contentType(MediaType.parseMediaType(entity.getAttachmentType()))
                .contentLength(entity.getAttachmentSize())
                .body(data);
    }
}
