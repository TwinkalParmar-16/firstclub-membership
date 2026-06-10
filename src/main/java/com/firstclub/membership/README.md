# Membership Program

## Tech Stack

- Java 21
- Spring Boot 3
- Maven

## Design Patterns

### Factory Pattern

MembershipPlanFactory creates plans.

### Strategy Pattern

Tier evaluation uses:

- OrderCountStrategy
- OrderValueStrategy
- CohortStrategy

## Concurrency

ConcurrentHashMap used for
thread-safe in-memory storage.

putIfAbsent used to avoid
duplicate subscriptions.

## APIs

GET /api/memberships/plans

POST /api/memberships/subscriptions

GET /api/memberships/subscriptions/{userId}

PUT /api/memberships/subscriptions/{userId}/tier

DELETE /api/memberships/subscriptions/{userId}

POST /api/memberships/evaluate-tier