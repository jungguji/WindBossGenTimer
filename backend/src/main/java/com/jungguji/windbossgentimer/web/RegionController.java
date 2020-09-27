package com.jungguji.windbossgentimer.web;

import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.service.RegionService;
import com.jungguji.windbossgentimer.web.dto.RegionDTO.MainViewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/region")
    public List<MainViewResponse> find() {
        List<Region> regions = this.regionService.findAll();
        return new MainViewResponse().convertRegionListToMainViewResponseList(regions);
    }

}
