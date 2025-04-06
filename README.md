## 프로젝트 설명
- 인스타그램 알림센터를 참고하여 알림센터를 위한 마이크로서비스를 만들어봅니다.
- 알림 종류: 게시물 댓글 알림, 게시물 좋아요 알림, 팔로우 알림

## 사용 기술
- Java, Spring Boot, Kafka, Redis, Docker Compose, Junit5, Test Container

## MongoDB Index
| 인덱스 생성 쿼리                                                                                               | 설명                                                      |
|------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| db.notifications.createIndex({ "deletedAt":1 }, { "name": "ix_deletedAt","expireAfterSeconds": 0 })        | 90일 지난 알림 삭제 시 사용하는 TTL 인덱스                         |
| db.notifications.createIndex({ userId: 1, occurredAt: -1 }, { name: "ix_userId_occurredAt" })              | 유저별 알림 목록 조회 시 사용하는 인덱스                            |
| db.notifications.createIndex({ userId: 1, lastUpdatedAt: -1 }, { name: "ix_userId_lastUpdatedAt" })        | 신규 알림 여부 조회 시 `latestUpdatedAt`을 구하기 위해 사용하는 인덱스 |
| db.notifications.createIndex({ type: 1, postId: 1 }, { name: "ix_type_postId" })                           | 좋아요 알림 생성/삭제 시 사용하는 인덱스                            |
| db.notifications.createIndex({ type: 1, commentId: 1 }, { name: "ix_type_commentId" })                     | 댓글 알림 삭제 시 사용하는 인덱스                                  |
| db.notifications.createIndex({ type: 1, userId: 1, followerId: 1 }, { name: "ix_type_userId_followerId" }) | 팔로우 알림 삭제 시 사용하는 인덱스                               |