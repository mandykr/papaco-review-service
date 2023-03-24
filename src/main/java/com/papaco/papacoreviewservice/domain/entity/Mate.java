package com.papaco.papacoreviewservice.domain.entity;

import com.papaco.papacoreviewservice.domain.vo.MateStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Mate {
    @Id
    private UUID id;
    private UUID reviewerId;
    private MateStatus status;

    public Mate(UUID id, UUID reviewerId, MateStatus status) {
        this.id = id;
        this.reviewerId = reviewerId;
        this.status = status;
    }
}
