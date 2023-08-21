package main

import (
	"comment-service/config"
	"comment-service/domain"
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/opentracing/opentracing-go"
	"log"
	"net/http"
)

func main() {
	tracer, closer, _ := config.InitJaeger()
	defer closer.Close()
	opentracing.SetGlobalTracer(tracer)

	router := gin.Default()
	router.GET("/api/v1/comments", getCommentsByPostId)

	router.Run(":9090")
}

func getCommentsByPostId(c *gin.Context) {
	log.Println("comment-service /getCommentsByPostId method")
	span := config.StartSpanFromRequest(config.Tracer, c.Request, "comment-service handle /getCommentsByPostId")
	defer span.Finish()

	postId := c.Query("postId")
	comment1 := domain.Comment{ID: "4d13441a-3c10-11ee-be56-0242ac120002", Content: "Good performance...", PostID: postId}
	comment2 := domain.Comment{ID: "fd7dbe48-1f88-4eba-880a-8ca8197f8a72", Content: "Good performance...", PostID: postId}

	comments := []domain.Comment{
		comment1,
		comment2,
	}

	span.LogEvent(fmt.Sprintf("Comments fetched by post id. Post ID: %s", postId))

	c.JSON(http.StatusOK, comments)
}
