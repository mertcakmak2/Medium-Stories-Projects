package main

import (
	"context"
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/opentracing/opentracing-go"
	"net/http"
	"post-service/client"
	"post-service/config"
	"post-service/domain"
)

var POST_ID_LIST = []string{"1-ab-2", "2-ab-3", "3-ab-4"}

func main() {
	tracer, closer, _ := config.InitJaeger()
	defer closer.Close()
	opentracing.SetGlobalTracer(tracer)

	router := gin.Default()
	router.GET("/api/v1/posts/:id", findPostById)

	router.Run(":8080")
}

func findPostById(c *gin.Context) {
	span := config.StartSpanFromRequest(config.Tracer, c.Request, "post-service handle /findPostById method")
	defer span.Finish()
	ctx := opentracing.ContextWithSpan(context.Background(), span)

	id := c.Param("id")
	if isPostExist := contains(POST_ID_LIST, id); !isPostExist {
		errMsg := fmt.Sprintf("Post not found. Post ID: %s", id)
		span.SetTag("error", true)
		span.LogEvent(errMsg)
		c.JSON(http.StatusNotFound, gin.H{"message": errMsg})
		return
	}

	comments, _ := client.GetCommentsByPostId(ctx, id)
	post := domain.Post{ID: id, Title: "post title", Comments: comments}

	span.LogEvent(fmt.Sprintf("Post fetched by id with comments. Post ID: %s", id))
	c.JSON(http.StatusOK, gin.H{"data": post})
}

func contains(s []string, str string) bool {
	for _, v := range s {
		if v == str {
			return true
		}
	}
	return false
}
