package it.mrt.bills.controllers.energy;

import it.mrt.bills.dtos.energy.EnergyConsumptionDTO;
import it.mrt.bills.entities.energy.EnergyConsumption;
import it.mrt.bills.mappers.energy.EnergyConsumptionMapper;
import it.mrt.bills.services.energy.EnergyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("energy-consumptions")
public class EnergyConsumptionController {

    @Autowired
    private EnergyConsumptionService energyConsumptionService;

    @Autowired
    private EnergyConsumptionMapper mapper;

    @PostMapping("save")
    public ResponseEntity<EnergyConsumptionDTO> save(@RequestBody EnergyConsumptionDTO dto) {
        EnergyConsumption energyConsumption = energyConsumptionService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(energyConsumption));
    }

    @GetMapping("{id}")
    public ResponseEntity<EnergyConsumptionDTO> getOne(@PathVariable UUID id) {
        EnergyConsumption energyConsumption = energyConsumptionService.findById(id);

        if (energyConsumption == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(energyConsumption));
    }

    @GetMapping
    public ResponseEntity<Collection<EnergyConsumptionDTO>> getAll() {
        List<EnergyConsumptionDTO> energyConsumptions = energyConsumptionService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(energyConsumptions);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EnergyConsumptionDTO> delete(@PathVariable UUID id) {
        EnergyConsumption energyConsumption = energyConsumptionService.deleteById(id);

        if (energyConsumption == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(energyConsumption));
    }
}
