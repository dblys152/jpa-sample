package com.ys.adapter.coupon.out.persistence;

import com.ys.domain.coupon.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
@ToString
public class CouponEntity {

    @Id
    private String id;
    private CouponType couponType;
    private DiscountType discountType;
    private Integer discountValue;
    private String description;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;
    @Version
    private Long version;

    public static CouponEntity fromDomain(Coupon coupon) {
        DiscountInfo discountInfo = coupon.getDiscountInfo();
        return new CouponEntity(
                coupon.getId().getId(),
                coupon.getCouponType(),
                discountInfo.getDiscountType(),
                discountInfo.getDiscountValue(),
                coupon.getDescription(),
                coupon.getCreatedAt(),
                coupon.getModifiedAt(),
                coupon.getDeletedAt(),
                coupon.getVersion()
        );
    }

    public Coupon toDomain() {
        return Coupon.of(
                CouponId.of(this.id),
                this.couponType,
                DiscountInfo.of(this.discountType, this.discountValue),
                this.description,
                this.createdAt,
                this.modifiedAt,
                this.deletedAt,
                this.version
        );
    }
}
