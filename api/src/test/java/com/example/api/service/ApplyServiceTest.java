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

		// Failed because the number of coupons is retrieved based on the time data "transmission" is completed.
		// So make thread sleep to pass the test.
		Thread.sleep(10000);

		long count = couponRepository.count();
		assertThat(count).isEqualTo(100);
	}

	@Test
	public void applyPerPerson() throws InterruptedException {
		int threadCount = 1000;

		// Multi-thread
		ExecutorService executorService = Executors.newFixedThreadPool(32);

		CountDownLatch latch = new CountDownLatch(threadCount);

		for(int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					// Apply only one userId(1L)
					this.applyService.apply(1L);
				} finally {
					latch.countDown();
				}
			});
		}

		latch.await();

		// Failed because the number of coupons is retrieved based on the time data "transmission" is completed.
		// So make thread sleep to pass the test.
		Thread.sleep(10000);

		long count = couponRepository.count();
		assertThat(count).isEqualTo(1);
	}

}

