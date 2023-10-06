package main

import (
	"fmt"
	"net/http"
)

func main() {

	http.HandleFunc("/api/v1/ping", ping)
	fmt.Println("Server Application running.")
	http.ListenAndServe(":8082", nil)
}

func ping(w http.ResponseWriter, r *http.Request) {

	fmt.Fprintf(w, "pong from server-go")
}
