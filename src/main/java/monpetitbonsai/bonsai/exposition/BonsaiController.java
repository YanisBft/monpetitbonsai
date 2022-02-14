package monpetitbonsai.bonsai.exposition;

import monpetitbonsai.bonsai.BonsaiMapper;
import monpetitbonsai.bonsai.domain.*;
import monpetitbonsai.commons.SortType;
import monpetitbonsai.commons.Status;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bonsais")
public class BonsaiController {
    private final BonsaiService bonsaiService;

    public BonsaiController(BonsaiService bonsaiService) {
        this.bonsaiService = bonsaiService;
    }

    @GetMapping
    public List<BonsaiDto> findAll(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false, defaultValue = "-1") int older_than,
            @RequestParam(required = false, defaultValue = "STATUS") SortType sort,
            @RequestParam(required = false, defaultValue = "DESC") Sort.Direction direction
    ) {
        Sort s = Sort.by(direction, sort.name().toLowerCase());
        List<BonsaiDto> list = bonsaiService.findAll(status, older_than, s).stream()
                .map(BonsaiMapper::toBonsaiDto)
                .collect(Collectors.toList());

        list.forEach(b -> {
           b.setLast_watering(bonsaiService.getLatestWatering(b.getId()).map(Watering::getWatering_date).orElse(null));
           b.setLast_repotting(bonsaiService.getLatestRepotting(b.getId()).map(Repotting::getRepotting_date).orElse(null));
           b.setLast_pruning(bonsaiService.getLatestPruning(b.getId()).map(Pruning::getPruning_date).orElse(null));
        });

        return list;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonsaiDto> findById(@PathVariable UUID id) {
        return bonsaiService.findById(id).map(b -> {
            BonsaiDto bonsaiDto = BonsaiMapper.toBonsaiDto(b);
            bonsaiDto.setLast_watering(bonsaiService.getLatestWatering(b.getId()).map(Watering::getWatering_date).orElse(null));
            bonsaiDto.setLast_repotting(bonsaiService.getLatestRepotting(b.getId()).map(Repotting::getRepotting_date).orElse(null));
            bonsaiDto.setLast_pruning(bonsaiService.getLatestPruning(b.getId()).map(Pruning::getPruning_date).orElse(null));
            return ResponseEntity.ok(bonsaiDto);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BonsaiDto> create(@RequestBody BonsaiDto bonsaiDto) {
        return new ResponseEntity<>(BonsaiMapper.toBonsaiDto(bonsaiService.create(BonsaiMapper.toBonsai(bonsaiDto))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PatchMapping("/{id}")
    public ResponseEntity<BonsaiDto> update(@PathVariable UUID id, @RequestBody BonsaiDto updatedBonsai) {
        return bonsaiService.update(id, BonsaiMapper.toBonsai(updatedBonsai))
                .map(b -> ResponseEntity.ok(BonsaiMapper.toBonsaiDto(b)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('STAFF')")
    @PutMapping("/{id}/status")
    public ResponseEntity<BonsaiDto> updateStatus(@PathVariable UUID id, @RequestBody String status) {
        try {
            Status st = Status.valueOf(status);
            return bonsaiService.updateStatus(id, st).map(b -> ResponseEntity.ok(BonsaiMapper.toBonsaiDto(b))).orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bonsaiService.delete(id);
    }

    @GetMapping("/{id}/watering")
    public List<WateringDto> getWaterings(@PathVariable UUID id) {
        return bonsaiService.getWaterings(id).stream().map(BonsaiMapper::toWateringDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}/repotting")
    public List<RepottingDto> getRepottings(@PathVariable UUID id) {
        return bonsaiService.getRepottings(id).stream().map(BonsaiMapper::toRepottingDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}/pruning")
    public List<PruningDto> getPrunings(@PathVariable UUID id) {
        return bonsaiService.getPrunings(id).stream().map(BonsaiMapper::toPruningDto).collect(Collectors.toList());
    }
}
