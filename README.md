# FirstClub Membership Management System

A Spring Boot based Membership Management System that allows users to subscribe to membership plans, manage tiers, evaluate tier upgrades, and view membership benefits.

---

## Tech Stack

- Java 21
- Spring Boot 3
- JUnit
- In-Memory Storage (ConcurrentHashMap)

---

## Features

### Membership Plans

Supported plans:

- MONTHLY
- QUARTERLY
- YEARLY

### Membership Tiers

Supported tiers:

- SILVER
- GOLD
- PLATINUM

### Subsription Management

- Create subscription
- View subscription
- Upgrade/Downgrade membership tier
- Cancel subscription
- View tier benefits

### Tier Evaluation

Users can be evaluated for tier upgrades based on:

- Order Count
- Order Value
- User Cohort

### Membership Benefits

Each tier has configurable benefits:

- Discounts
- Free Delivery
- Early Access To Sales
- Exclusive Deals
- Priority Support

---

# Design Principles

## SOLID Principles


# Design Patterns Used

## 1. Factory Pattern

## 2. Strategy Pattern

## 3. Builder Pattern

# Project Structure

```text
controller
│
├── MembershipController

service
│
├── MembershipService
├── MembershipServiceImpl
├── TierEvaluationService

repository
│
├── MembershipRepository

strategy
│
├── TierEvaluationStrategy
├── OrderCountTierStrategy
├── OrderValueTierStrategy
├── CohortTierStrategy

factory
│
├── MembershipPlanFactory

config
│
├── BenefitConfig

model
│
├── MembershipPlan
├── MembershipSubscription
├── UserMetrics
├── TierBenefit

dto
│
├── SubscribeRequest
├── UpdateTierRequest
├── UserMetricsRequest

exception
│
├── GlobalExceptionHandler
├── BadRequestException
├── NotFoundException

enums
│
├── PlanType
├── TierType
├── SubscriptionStatus
```

---

# Concurrency Considerations

Since the assignment uses in-memory storage, thread safety was considered.

Repository uses:

```java
ConcurrentHashMap
```

Benefits:

- Thread-safe operations
- Supports concurrent API requests
- Better than HashMap for multi-threaded environments

Additionally, user-specific locking can be extended using:

```java
ReentrantLock
```

to avoid concurrent updates for the same user.

---

# APIs

## Get Membership Plans

```
http://localhost:8080/api/memberships/plans
```
```
Response
[
    {
        "type": "MONTHLY",
        "price": 199,
        "durationDays": 30
    },
    {
        "type": "QUARTERLY",
        "price": 499,
        "durationDays": 90
    },
    {
        "type": "YEARLY",
        "price": 1499,
        "durationDays": 365
    }
]

```
---

## Create Subscription

```http
POST http://localhost:8080/api/memberships/subscriptions
```

Request:

```json
{
  "userId":"Bhawna",
  "planType":"YEARLY",
  "tierType":"GOLD"
}
```

---

## Get Subscription

```http
GET http://localhost:8080/api/memberships/subscriptions/{iserId}
```

---

## Upgrade / Downgrade Tier

```http
PUT http://localhost:8080/api/memberships/subscriptions/{iserId}/tier
```

Request:

```json
{
  "tierType": "PLATINUM"
}
```

---

## Cancel Subscription

```http
DELETE http://localhost:8080/api/memberships/subscriptions/{userId}
```

---

## Evaluate Tier Eligibility

```http
POST http://localhost:8080/api/memberships/evaluate-tier
```

Request:

```json
{
  "userId": "Bhawna",
  "orderCount": 90,
  "monthlySpend": 500,
  "cohort": "REGULAR"
}
```

---

## Get Tier Benefits

```http
GET http://localhost:8080/api/memberships/subscriptions/{userId}/benefits
```
Response:
{
"tierType": "GOLD",
"discountPercentage": 10.0,
"freeDeliveryThreshold": 0.0,
"earlyAccessToSales": true,
"exclusiveDeals": true,
"prioritySupport": false,
"description": "2% discount, free delivery on all orders, early access to sales"
}
---



# Exception Handling
# Testing
# Assumptions

- One active subscription per user
- In-memory storage used instead of a database
- Benefits are tier-based and configurable
- Tier evaluation is rule-based using strategies
- Subscription cancellation updates status instead of deleting data


