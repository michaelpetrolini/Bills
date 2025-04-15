package it.mrt.bills.controllers.gas;

import it.mrt.bills.dtos.filters.GasContractFilters;
import it.mrt.bills.dtos.gas.GasContractDTO;
import it.mrt.bills.entities.gas.GasContract;
import it.mrt.bills.mappers.gas.GasContractMapper;
import it.mrt.bills.services.gas.GasContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gas-contracts")
public class GasContractController {

    @Autowired
    private GasContractService gasContractService;

    @Autowired
    private GasContractMapper mapper;

    @PostMapping("save")
    public ResponseEntity<GasContractDTO> save(@RequestBody GasContractDTO dto) {
        GasContract gasContract = gasContractService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(gasContract));
    }

    @GetMapping("{id}")
    public ResponseEntity<GasContractDTO> getOne(@PathVariable UUID id) {
        GasContract gasContract = gasContractService.findById(id);

        if (gasContract == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(mapper.toDto(gasContract));
    }

    @GetMapping
    public ResponseEntity<Collection<GasContractDTO>> getAll() {
        List<GasContractDTO> gasContracts = gasContractService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(gasContracts);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GasContractDTO> delete(@PathVariable UUID id) {
        GasContract gasContract = gasContractService.deleteById(id);

        if (gasContract == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(gasContract));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<GasContractDTO>> filter(@RequestBody GasContractFilters filters) {
        Collection<GasContractDTO> gasContracts = gasContractService.filter(filters).stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(gasContracts);
    }
}
