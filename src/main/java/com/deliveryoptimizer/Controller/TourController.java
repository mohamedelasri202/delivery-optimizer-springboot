package com.deliveryoptimizer.Controller;

import com.deliveryoptimizer.DTO.TourDTO;
import com.deliveryoptimizer.Service.TourServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tour")
public class TourController {

    private final TourServiceInterface tourService;

    public TourController(TourServiceInterface tourService) {
        this.tourService = tourService;
    }

    @PostMapping
    public ResponseEntity<TourDTO> create(@RequestBody TourDTO dto) {
        return ResponseEntity.ok(tourService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourDTO> update(@PathVariable Integer id, @RequestBody TourDTO dto) {
        dto.setId(id);
        return ResponseEntity.ok(tourService.update(dto));
    }

//    @GetMapping
//    public ResponseEntity<List<TourDTO>> getAll() {
//        return ResponseEntity.ok(tourService.getAll());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<TourDTO> getById(@PathVariable Integer id) {
//        TourDTO dto = tourService.getById(id);
//        if (dto != null) return ResponseEntity.ok(dto);
//        return ResponseEntity.notFound().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Boolean deleted = tourService.delete(id);
        if (deleted) return ResponseEntity.ok("Tour with id " + id + " deleted successfully");
        return ResponseEntity.status(404).body("Tour not found");
    }
}
