## 모델링
### 리뷰(`Review`)
- 리뷰는 식별자 메이트, 리뷰 상태를 가진다.
- 리뷰는 요청 ➜ 완료 순서로 진행된다.
- [X] 메이트가 연결 상태가 아니면 리뷰를 요청할 수 없다.
- [X] 메이트의 모든 리뷰가 완료 상태가 아니면 리뷰 요청이 되지 않는다.
- [X] Github PR 이 없으면 리뷰 요청이 되지 않는다.

### 메이트(`Mate`)
- 메이트는 식별자와 프로젝트 식별자, 리뷰어 식별자, 메이트 상태를 가진다.
