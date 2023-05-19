package com.ys.adapter.published_coupon.out.persistence;

import com.ys.adapter.coupon.out.persistence.CouponEntity;
import com.ys.domain.coupon.CouponId;
import com.ys.domain.published_coupon.PublishedCoupon;
import com.ys.domain.published_coupon.*;
import com.ys.refs.user.domain.UserId;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "published_coupons")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
@ToString
public class PublishedCouponEntity {

    @Id
    private String id;
    private String userId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "coupon_id")
    private CouponEntity couponEntity;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @Version
    private Long version;

    public PublishedCouponEntity(
            String id,
            String userId,
            CouponEntity couponEntity,
            Status status,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            LocalDateTime createdAt,
            LocalDateTime modifiedAt,
            Long version
    ) {
        this.id = id;
        this.userId = userId;
        this.couponEntity = couponEntity;
        this.status = status;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.version = version;
    }

    public static PublishedCouponEntity fromDomain(PublishedCoupon publishedCoupon) {
        Period period = publishedCoupon.getPeriod();
        return new PublishedCouponEntity(
                publishedCoupon.getId(),
                publishedCoupon.getUserId().getId(),
                CouponEntity.fromDomain(publishedCoupon.getCoupon()),
                publishedCoupon.getStatus(),
                period.getStartedAt(),
                period.getEndedAt(),
                publishedCoupon.getCreatedAt(),
                publishedCoupon.getModifiedAt(),
                publishedCoupon.getVersion()
        );
    }

    public PublishedCoupon toDomain() {
        return PublishedCoupon.of(
                this.id,
                UserId.of(this.userId),
                this.couponEntity.toDomain(),
                this.status,
                Period.of(this.startedAt, this.endedAt),
                this.createdAt,
                this.modifiedAt,
                this.version
        );
    }
}
