package com.example.api.service;

import org.springframework.stereotype.Service;

import com.example.api.producer.CouponCreateProducer;
import com.example.api.repository.AppliedUserRepository;
import com.example.api.repository.CouponCountRepository;
import com.example.api.repository.CouponRepository;

@Service
public class ApplyService {

	private final CouponRepository couponRepository;

	private final CouponCountRepository couponCountRepository;

	private final CouponCreateProducer couponCreateProducer;

	private final AppliedUserRepository appliedUserRepository;

	public ApplyService(
		CouponRepository couponRepository,
		CouponCountRepository couponCountRepository,
		CouponCreateProducer couponCreateProducer,
		AppliedUserRepository appliedUserRepository
	) {
		this.couponRepository = couponRepository;
		this.couponCountRepository = couponCountRepository;
		this.couponCreateProducer = couponCreateProducer;
		this.appliedUserRepository = appliedUserRepository;
	}

	public void apply(Long userId) {
		Long isApplied = this.appliedUserRepository.add(userId);

		if (isApplied != 1) {
			return;
		}

		Long count = this.couponCountRepository.increment();

		if (count > 100) {
			return;
		}

		this.couponCreateProducer.create(userId);
	}
}
