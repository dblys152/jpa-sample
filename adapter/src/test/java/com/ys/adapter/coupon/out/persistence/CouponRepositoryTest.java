package com.ys.adapter.coupon.out.persistence;

import com.ys.adapter.common.config.DataJpaConfig;
import com.ys.adapter.fixture.SupportedCouponEntityFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DataJpaConfig.class)
@Sql(scripts = "classpath:create.sql")
class CouponRepositoryTest extends SupportedCouponEntityFixture {

    @Autowired
    private CouponRepository repository;

    @Test
    void save() {
        CouponEntity couponEntity = new CouponEntity(
                ANY_COUPON_ID, ANY_COUPON_TYPE,ANY_DISCOUNT_TYPE, ANY_DISCOUNT_VALUE, ANY_DESCRIPTION, NOW, NOW, null, null
        );
        CouponEntity actual = repository.save(couponEntity);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull()
        );
    }
}