package com.papaco.papacoreviewservice.domain.entity;

import com.papaco.papacoreviewservice.domain.vo.MateStatus;
import com.papaco.papacoreviewservice.framework.adapter.input.ReviewStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

import static com.papaco.papacoreviewservice.framework.adapter.input.ReviewStatus.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Review {
    private static final String MATE_STATUS_MUST_BE_JOINED = "메이트가 연결된 상태가 아니면 리뷰를 생성할 수 없습니다.";

    @Id
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "mate_id",
            foreignKey = @ForeignKey(name = "fk_review_to_mate")
    )
    private Mate mate;
    private ReviewStatus status;

    public Review(UUID id, Mate mate) {
        validate(mate);
        this.id = id;
        this.mate = mate;
    }

    private void validate(Mate mate) {
        if (MateStatus.JOINED != mate.getStatus()) {
            throw new IllegalStateException(MATE_STATUS_MUST_BE_JOINED);
        }
    }

    public void demand() {
        this.status = DEMANDED;
    }

    public void complete() {
        this.status = COMPLETED;
    }
}
