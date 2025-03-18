package it.mrt.bills.controllers.energy;

import it.mrt.bills.dtos.energy.EnergyContractDTO;
import it.mrt.bills.dtos.filters.EnergyContractFilters;
import it.mrt.bills.entities.energy.EnergyContract;
import it.mrt.bills.mappers.energy.EnergyContractMapper;
import it.mrt.bills.services.energy.EnergyContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/energy-contracts")
public class EnergyContractController {

    @Autowired
    private EnergyContractService energyContractService;

    @Autowired
    private EnergyContractMapper mapper;

    @PostMapping("save")
    public ResponseEntity<EnergyContractDTO> save(@RequestBody EnergyContractDTO dto) {
        EnergyContract energyContract = energyContractService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(energyContract));
    }

    @GetMapping("{id}")
    public ResponseEntity<EnergyContractDTO> getOne(@PathVariable UUID id) {
        EnergyContract energyContract = energyContractService.findById(id);

        if (energyContract == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(mapper.toDto(energyContract));
    }

    @GetMapping
    public ResponseEntity<Collection<EnergyContractDTO>> getAll() {
        List<EnergyContractDTO> energyContracts = energyContractService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(energyContracts);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EnergyContractDTO> delete(@PathVariable UUID id) {
        EnergyContract energyContract = energyContractService.deleteById(id);

        if (energyContract == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(energyContract));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<EnergyContractDTO>> filter(@RequestBody EnergyContractFilters filters) {
        Collection<EnergyContractDTO> energyContracts = energyContractService.filter(filters).stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(energyContracts);
    }
}
