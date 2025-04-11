package it.mrt.bills.controllers;

import it.mrt.bills.dtos.RegionDTO;
import it.mrt.bills.entities.Region;
import it.mrt.bills.mappers.RegionMapper;
import it.mrt.bills.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private RegionMapper mapper;

    @PostMapping("save")
    public ResponseEntity<RegionDTO> save(@RequestBody RegionDTO dto) {
        Region region = regionService.save(dto);

        return ResponseEntity.ok(mapper.toDto(region));
    }

    @GetMapping("{id}")
    public ResponseEntity<RegionDTO> getOne(@PathVariable("id") UUID id) {
        Region region = regionService.findById(id);

        if (region == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(region));
    }

    @GetMapping
    public ResponseEntity<Collection<RegionDTO>> getAll() {
        Collection<RegionDTO> regions = regionService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(regions);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RegionDTO> delete(@PathVariable("id") UUID id) {
        Region region = regionService.deleteById(id);

        if (region == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(region));
    }
}
