package com.doumo.app.controller;

import com.doumo.app.model.Commune;
import com.doumo.app.model.Region;
import com.doumo.app.util.RegionDataUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@CrossOrigin(origins = "*")
public class RegionController {

    @GetMapping
    public ResponseEntity<List<Region>> listRegions() {
        return ResponseEntity.ok(RegionDataUtil.getRegions());
    }

    @GetMapping("/{regionId}/communes")
    public ResponseEntity<List<Commune>> listCommunesByRegion(@PathVariable String regionId) {
        Region region = RegionDataUtil.getRegion(regionId);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(region.getCommunes());
    }
}