package it.mrt.bills.controllers;

import it.mrt.bills.dtos.ProviderDTO;
import it.mrt.bills.dtos.filters.ProviderFilters;
import it.mrt.bills.entities.Provider;
import it.mrt.bills.mappers.ProviderMapper;
import it.mrt.bills.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProviderMapper mapper;

    @PostMapping("save")
    public ResponseEntity<ProviderDTO> saveProvider(@RequestBody ProviderDTO dto) {
        Provider provider = providerService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(provider));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProviderDTO> getOne(@PathVariable("id") UUID id) {
        Provider provider = providerService.findById(id);

        if (provider == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(provider));
    }

    @GetMapping
    public ResponseEntity<Collection<ProviderDTO>> getAll() {
        List<ProviderDTO> providers = providerService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(providers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProviderDTO> delete(@PathVariable UUID id) {
        Provider provider = providerService.deleteById(id);

        if (provider == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(provider));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<ProviderDTO>> filter(@RequestBody ProviderFilters filters) {
        Collection<ProviderDTO> providers = providerService.filter(filters).stream().map(mapper::toDto).toList();

        return ResponseEntity.ok(providers);
    }
}
