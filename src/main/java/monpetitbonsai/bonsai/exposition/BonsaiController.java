package monpetitbonsai.bonsai.exposition;

import monpetitbonsai.BonsaiMapper;
import monpetitbonsai.bonsai.domain.BonsaiService;
import monpetitbonsai.bonsai.domain.Pruning;
import monpetitbonsai.bonsai.domain.Repotting;
import monpetitbonsai.bonsai.domain.Watering;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public List<BonsaiDto> findAll() {
        return bonsaiService.findAll().stream().map(BonsaiMapper::toBonsaiDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonsaiDto> findById(@PathVariable UUID id) {
        return bonsaiService.findById(id).map(b -> ResponseEntity.ok(BonsaiMapper.toBonsaiDto(b))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BonsaiDto> create(@RequestBody BonsaiDto bonsai) {
        return new ResponseEntity<>(BonsaiMapper.toBonsaiDto(bonsaiService.create(BonsaiMapper.toBonsai(bonsai))), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BonsaiDto> update(@PathVariable UUID id, @RequestBody BonsaiDto updatedBonsai) {
        return bonsaiService.update(id, BonsaiMapper.toBonsai(updatedBonsai)).map(b -> ResponseEntity.ok(BonsaiMapper.toBonsaiDto(b))).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<BonsaiDto> updateStatus(@PathVariable UUID id, @RequestBody String status) {
        if (status.equals("alive") || status.equals("dead") || status.equals("unknown")) {
            return bonsaiService.updateStatus(id, status).map(b -> ResponseEntity.ok(BonsaiMapper.toBonsaiDto(b))).orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bonsaiService.delete(id);
    }

    @GetMapping("/{id}/watering")
    public ResponseEntity<Watering> getLatestWatering(@PathVariable UUID id) {
        return bonsaiService.getLatestWatering(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/repotting")
    public ResponseEntity<Repotting> getLatestRepotting(@PathVariable UUID id) {
        return bonsaiService.getLatestRepotting(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/pruning")
    public ResponseEntity<Pruning> getLatestPruning(@PathVariable UUID id) {
        return bonsaiService.getLatestPruning(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
