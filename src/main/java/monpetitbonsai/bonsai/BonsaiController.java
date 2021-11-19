package monpetitbonsai.bonsai;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bonsais")
public class BonsaiController {
    private final BonsaiDao bonsaiDao;

    public BonsaiController(BonsaiDao bonsaiDao) {
        this.bonsaiDao = bonsaiDao;
    }

    @GetMapping
    public List<BonsaiEntity> getAll() {
        return bonsaiDao.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BonsaiEntity> getById(@PathVariable UUID id) {
        return bonsaiDao.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BonsaiEntity> add(@RequestBody BonsaiEntity bonsai) {
        return new ResponseEntity<>(bonsaiDao.save(bonsai), HttpStatus.CREATED);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<BonsaiEntity> modify(@PathVariable UUID id, @RequestBody BonsaiEntity bonsai) {
//
//    }

//    @GetMapping("/{name}")
//    public BonsaiEntity getBonsai(@PathVariable String name) {
//        return bonsaiDao.findFromName(name);
//    }
}
