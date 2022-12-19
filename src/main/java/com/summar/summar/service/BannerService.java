package com.summar.summar.service;

import com.summar.summar.domain.Banner;
import com.summar.summar.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BannerService {
    private final BannerRepository bannerRepository;
    public List<Banner> findAllByBanner() {
        return bannerRepository.findAll();
    }
}
