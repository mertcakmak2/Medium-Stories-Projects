package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
)

func main() {
	fmt.Println("Api Go!")

	r := gin.Default()
	r.GET("/ping", ping)
	r.Run(":8080")

}

func ping(c *gin.Context) {

	c.JSON(200, gin.H{
		"message": "pong",
	})
}
