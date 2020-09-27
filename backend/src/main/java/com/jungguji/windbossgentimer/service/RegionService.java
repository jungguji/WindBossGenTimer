package com.jungguji.windbossgentimer.service;

import com.jungguji.windbossgentimer.domain.region.Region;
import com.jungguji.windbossgentimer.domain.region.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RegionService {

    private final RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }
}
