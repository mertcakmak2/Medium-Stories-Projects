package config

import (
	"github.com/sony/gobreaker"
	"time"
)

func CircuitBreakerConfig() *gobreaker.CircuitBreaker {
	settings := gobreaker.Settings{
		Name:    "client-circuit-breaker",
		Timeout: 5 * time.Second,
		ReadyToTrip: func(counts gobreaker.Counts) bool {
			return counts.TotalFailures >= 3
		},
	}
	return gobreaker.NewCircuitBreaker(settings)
}
