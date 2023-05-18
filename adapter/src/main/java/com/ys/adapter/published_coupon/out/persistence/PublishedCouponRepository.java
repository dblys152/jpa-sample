package com.ys.adapter.published_coupon.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishedCouponRepository extends JpaRepository<PublishedCouponEntity, String> {

}
