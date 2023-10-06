package main

import (
	"client-go/config"
	"client-go/handler"
	"client-go/request"
	"fmt"
	"net/http"
)

func main() {

	circuitBreaker := config.CircuitBreakerConfig()
	request := request.NewHttpRequest(circuitBreaker, http.Client{}, "http://localhost:8082/api/v1/ping")
	handler := handler.NewHandler(*request)

	http.HandleFunc("/api/v1/ping", handler.Ping)
	fmt.Println("Client Application running .")
	http.ListenAndServe(":8080", nil)
}
