package it.mrt.bills.controllers.energy;

import it.mrt.bills.dtos.energy.EnergyBillDTO;
import it.mrt.bills.dtos.filters.EnergyBillFilters;
import it.mrt.bills.entities.energy.EnergyBill;
import it.mrt.bills.mappers.energy.EnergyBillMapper;
import it.mrt.bills.services.energy.EnergyBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/energy-bills")
public class EnergyBillController {

    @Autowired
    private EnergyBillService energyBillService;

    @Autowired
    private EnergyBillMapper mapper;

    @PostMapping("save")
    public ResponseEntity<EnergyBillDTO> save(@RequestBody EnergyBillDTO dto) {
        EnergyBill energyBill = energyBillService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(energyBill));
    }

    @GetMapping("{id}")
    public ResponseEntity<EnergyBillDTO> getOne(@PathVariable UUID id) {
        EnergyBill energyBill = energyBillService.findById(id);

        if (energyBill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(energyBill));
    }

    @GetMapping
    public ResponseEntity<Collection<EnergyBillDTO>> getAll() {
        List<EnergyBillDTO> energyBills = energyBillService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(energyBills);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EnergyBillDTO> delete(@PathVariable UUID id) {
        EnergyBill energyBill = energyBillService.deleteById(id);

        if (energyBill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(energyBill));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<EnergyBillDTO>> filter(@RequestBody EnergyBillFilters filters) {
        Collection<EnergyBillDTO> energyBills = energyBillService.filter(filters).stream().map(mapper::toDto).toList();

        return ResponseEntity.ok(energyBills);
    }
}
