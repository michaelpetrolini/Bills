package it.mrt.bills.controllers.gas;

import it.mrt.bills.dtos.filters.GasOfferFilters;
import it.mrt.bills.dtos.gas.GasComparisonRequestDTO;
import it.mrt.bills.dtos.gas.GasComparisonResultDTO;
import it.mrt.bills.dtos.gas.GasOfferDTO;
import it.mrt.bills.entities.gas.GasOffer;
import it.mrt.bills.mappers.gas.GasOfferMapper;
import it.mrt.bills.services.gas.GasOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gas-offers")
public class GasOfferController {

    @Autowired
    private GasOfferService gasOfferService;

    @Autowired
    private GasOfferMapper mapper;

    @PostMapping("save")
    public ResponseEntity<GasOfferDTO> save(@RequestBody GasOfferDTO dto) {
        GasOffer gasOffer = gasOfferService.save(dto);
        return ResponseEntity.ok(mapper.toDto(gasOffer));
    }

    @GetMapping("{id}")
    public ResponseEntity<GasOfferDTO> getOne(@PathVariable("id") UUID id) {
        GasOffer gasOffer = gasOfferService.findById(id);

        if (gasOffer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(gasOffer));
    }

    @GetMapping
    public ResponseEntity<Collection<GasOfferDTO>> getAll() {
        List<GasOfferDTO> gasOffers = gasOfferService.findAll().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(gasOffers);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GasOfferDTO> delete(@PathVariable UUID id) {
        GasOffer gasOffer = gasOfferService.deleteById(id);

        if (gasOffer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapper.toDto(gasOffer));
    }

    @PostMapping("filter")
    public ResponseEntity<Collection<GasOfferDTO>> filter(@RequestBody GasOfferFilters filters) {
        Collection<GasOfferDTO> gasOffers = gasOfferService.filter(filters).stream().map(mapper::toDto).toList();

        return ResponseEntity.ok(gasOffers);
    }

    @PostMapping("compare")
    public ResponseEntity<Collection<GasComparisonResultDTO>> compare(@RequestBody GasComparisonRequestDTO dto) {
        Collection<GasComparisonResultDTO> comparisons = gasOfferService.compare(dto);

        return ResponseEntity.ok(comparisons);
    }
}
