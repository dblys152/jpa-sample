package com.ys.adapter.published_coupon.out.persistence;

import com.ys.adapter.common.config.DataJpaConfig;
import com.ys.adapter.coupon.out.persistence.CouponEntity;
import com.ys.adapter.fixture.SupportedCouponEntityFixture;
import com.ys.domain.published_coupon.Status;
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
class PublishedCouponRepositoryTest extends SupportedCouponEntityFixture {

    private static final String ANY_USER_ID = "ANY_USER_ID";
    private static final String INSERTED_COUPON_ID = "1";

    @Autowired
    private PublishedCouponRepository repository;

    @Test
    void save() {
        CouponEntity ANY_COUPON_ENTITY = new CouponEntity(
                INSERTED_COUPON_ID, ANY_COUPON_TYPE, ANY_DISCOUNT_TYPE, ANY_DISCOUNT_VALUE, ANY_DESCRIPTION, NOW, NOW, null, 0L
        );
        PublishedCouponEntity publishedCouponEntity = new PublishedCouponEntity(
                "ANY_ID", ANY_USER_ID, ANY_COUPON_ENTITY, Status.AVAILABLE, NOW, NOW.plusDays(5), null, null, null
        );

        PublishedCouponEntity actual = repository.save(publishedCouponEntity);
        System.out.println(actual.toString());
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getCouponEntity()).isNotNull()
        );
    }
}