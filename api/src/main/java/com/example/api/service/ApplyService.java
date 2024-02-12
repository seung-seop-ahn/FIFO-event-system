package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.domain.Coupon;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;

@Service
public class ApplyService {

	private final CouponRepository couponRepository;

	private final CouponCountRepository couponCountRepository;

	public ApplyService(CouponRepository couponRepository, CouponCountRepository couponCountRepository) {
		this.couponRepository = couponRepository;
		this.couponCountRepository = couponCountRepository;
	}

	public void apply(Long userId) {
		Long count = this.couponCountRepository.increment();

		if(count >100) {
			return;
		}

		this.couponRepository.save(new Coupon(userId));
	}
}
