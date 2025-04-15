package it.mrt.bills.controllers.gas;

import it.mrt.bills.dtos.gas.GasConsumptionDTO;
import it.mrt.bills.dtos.filters.GasConsumptionFilters;
import it.mrt.bills.entities.gas.GasConsumption;
import it.mrt.bills.mappers.gas.GasConsumptionMapper;
import it.mrt.bills.services.gas.GasConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gas-consumptions")
public class GasConsumptionController {

    @Autowired
    private GasConsumptionService gasConsumptionService;

    @Autowired
    private GasConsumptionMapper mapper;

    @PostMapping("save")
    public ResponseEntity<GasConsumptionDTO> save(@RequestBody GasConsumptionDTO dto) {
        GasConsumption gasConsumption = gasConsumptionService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(gasConsumption));
    }

    @GetMapping("{id}")
    public ResponseEntity<GasConsumptionDTO> getOne(@PathVariable UUID id) {
        GasConsumption gasConsumption = gasConsumptionService.findById(id);

        if (gasConsumption == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(gasConsumption));
    }

    @GetMapping
    public ResponseEntity<Collection<GasConsumptionDTO>> getAll() {
        List<GasConsumptionDTO> gasConsumptions = gasConsumptionService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(gasConsumptions);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GasConsumptionDTO> delete(@PathVariable UUID id) {
        GasConsumption gasConsumption = gasConsumptionService.deleteById(id);

        if (gasConsumption == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(gasConsumption));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<GasConsumptionDTO>> filter(@RequestBody GasConsumptionFilters filters) {
        Collection<GasConsumptionDTO> gasConsumptions = gasConsumptionService.filter(filters).stream().map(mapper::toDto).toList();

        return ResponseEntity.ok(gasConsumptions);
    }
}
