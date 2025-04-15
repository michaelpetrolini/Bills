package it.mrt.bills.controllers.gas;

import it.mrt.bills.dtos.gas.GasBillDTO;
import it.mrt.bills.dtos.filters.GasBillFilters;
import it.mrt.bills.entities.gas.GasBill;
import it.mrt.bills.mappers.gas.GasBillMapper;
import it.mrt.bills.services.gas.GasBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gas-bills")
public class GasBillController {

    @Autowired
    private GasBillService gasBillService;

    @Autowired
    private GasBillMapper mapper;

    @PostMapping("save")
    public ResponseEntity<GasBillDTO> save(@RequestBody GasBillDTO dto) {
        GasBill gasBill = gasBillService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(gasBill));
    }

    @GetMapping("{id}")
    public ResponseEntity<GasBillDTO> getOne(@PathVariable UUID id) {
        GasBill gasBill = gasBillService.findById(id);

        if (gasBill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(gasBill));
    }

    @GetMapping
    public ResponseEntity<Collection<GasBillDTO>> getAll() {
        List<GasBillDTO> gasBills = gasBillService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(gasBills);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GasBillDTO> delete(@PathVariable UUID id) {
        GasBill gasBill = gasBillService.deleteById(id);

        if (gasBill == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(gasBill));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<GasBillDTO>> filter(@RequestBody GasBillFilters filters) {
        Collection<GasBillDTO> gasBills = gasBillService.filter(filters).stream().map(mapper::toDto).toList();

        return ResponseEntity.ok(gasBills);
    }
}
