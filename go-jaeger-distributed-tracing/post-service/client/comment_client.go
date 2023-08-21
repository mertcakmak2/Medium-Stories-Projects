package client

import (
	"context"
	"encoding/json"
	"fmt"
	"net/http"
	"post-service/config"
	"post-service/model"
	"post-service/request"

	"github.com/opentracing/opentracing-go"
)

func GetCommentsByPostId(ctx context.Context, postId string) ([]model.Comment, error) {
	span, _ := opentracing.StartSpanFromContext(ctx, "post-service client /GetComments func")
	defer span.Finish()

	span.LogEvent(fmt.Sprintf("GetCommentsByPostId method. Post ID: %s", postId))

	url := fmt.Sprintf("http://localhost:9090/api/v1/comments?postId=%s", postId)
	req, err := http.NewRequest("GET", url, nil)
	if err != nil {
		return nil, err
	}

	if err := config.Inject(span, req); err != nil {
		return nil, err
	}

	var comments []model.Comment
	data, _ := request.Do(req)
	json.Unmarshal(data, &comments)

	return comments, nil
}
