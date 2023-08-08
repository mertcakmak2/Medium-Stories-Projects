package main

import (
	"grpc_example/hello"
	"log"
	"net"

	"github.com/gin-gonic/gin"
	"google.golang.org/grpc"
)

func main() {

	go createGrpcServer()

	router := gin.Default()
	router.GET("/health", func(c *gin.Context) {
		c.String(200, "health")
	})

	router.Run(":8080")

}

func createGrpcServer() {
	lis, err := net.Listen("tcp", ":9090")
	defer lis.Close()
	if err != nil {
		log.Fatalf("Failed to listen: %+v", err)
	}

	grpcServer := grpc.NewServer()
	helloServer := hello.Server{}

	hello.RegisterHelloServiceServer(grpcServer, &helloServer)
	if err := grpcServer.Serve(lis); err != nil {
		log.Fatalf("Failed to serve: %+v", err)
	}
}
