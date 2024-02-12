package com.example.api.service;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api.repository.CouponRepository;

@SpringBootTest
class ApplyServiceTest {

	@Autowired
	private ApplyService applyService;

	@Autowired
	private CouponRepository couponRepository;

	@Test
	public void applyOnce() {
		this.applyService.apply(1L);

		long count = this.couponRepository.count();

		assertThat(count).isEqualTo(1);
	}

	@Test
	public void apply() throws InterruptedException {
		int threadCount = 1000;

		// Multi-thread
		ExecutorService executorService = Executors.newFixedThreadPool(32);

		CountDownLatch latch = new CountDownLatch(threadCount);

		for(int i = 0; i < threadCount; i++) {
			long userId = i;
			executorService.submit(() -> {
				try {
					this.applyService.apply(userId);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		long count = couponRepository.count();
		assertThat(count).isEqualTo(100);
	}

}

