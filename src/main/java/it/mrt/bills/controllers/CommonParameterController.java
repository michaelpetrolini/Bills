package it.mrt.bills.controllers;

import it.mrt.bills.dtos.CommonParameterDTO;
import it.mrt.bills.entities.CommonParameter;
import it.mrt.bills.mappers.CommonParameterMapper;
import it.mrt.bills.services.CommonParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/common-parameters")
public class CommonParameterController {

    @Autowired
    private CommonParameterService commonParameterService;

    @Autowired
    private CommonParameterMapper mapper;

    @PostMapping("save")
    public ResponseEntity<CommonParameterDTO> save(@RequestBody CommonParameterDTO dto) {
        CommonParameter commonParameter = commonParameterService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(commonParameter));
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonParameterDTO> getOne(@PathVariable("id") UUID id) {
        CommonParameter commonParameter = commonParameterService.findById(id);

        if (commonParameter == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(commonParameter));
    }

    @GetMapping
    public ResponseEntity<Collection<CommonParameterDTO>> getAll() {
        Collection<CommonParameterDTO> commonParameters = commonParameterService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(commonParameters);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CommonParameterDTO> delete(@PathVariable("id") UUID id) {
        CommonParameter commonParameter = commonParameterService.deleteById(id);

        if (commonParameter == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(commonParameter));
    }
}
