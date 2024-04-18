package handler

import (
	"encoding/json"
	"github.com/aws/aws-lambda-go/events"
	"github.com/grokify/go-awslambda"
	"go-lambda/internal/file"
	"io"
	"net/http"
)

type Handler struct {
	AwsUseCase file.AwsS3UseCase
}

func NewHandler(awsUseCase file.AwsS3UseCase) *Handler {
	return &Handler{AwsUseCase: awsUseCase}
}

func (h *Handler) Upload(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	res := events.APIGatewayProxyResponse{}
	r, err := awslambda.NewReaderMultipart(request)
	if err != nil {
		return res, err
	}
	part, err := r.NextPart()
	if err != nil {
		return res, err
	}
	content, err := io.ReadAll(part)
	if err != nil {
		return res, err
	}

	viewUrl, err := h.AwsUseCase.PutObject(part.FileName(), content)
	if err != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusInternalServerError,
			Body:       err.Error(),
		}, nil
	}

	response := make(map[string]string)
	response["message"] = "File uploaded successfully"
	response["viewUrl"] = viewUrl

	marshal, err := json.Marshal(response)
	if err != nil {
		return events.APIGatewayProxyResponse{}, err
	}

	res = events.APIGatewayProxyResponse{
		StatusCode: 201,
		Headers: map[string]string{
			"Content-Type": "application/json",
		},
		Body: string(marshal)}

	return res, nil

}

func (h *Handler) View(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	res := events.APIGatewayProxyResponse{}
	key := request.QueryStringParameters["filename"]

	base64Content, err := h.AwsUseCase.GetObject(key)
	if err != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusInternalServerError,
			Body:       err.Error(),
		}, nil
	}

	res = events.APIGatewayProxyResponse{
		StatusCode: 200,
		Headers: map[string]string{
			"Content-Type": "image/png",
		},
		Body:            base64Content,
		IsBase64Encoded: true,
	}
	return res, nil
}

func (h *Handler) Delete(request events.APIGatewayProxyRequest) (events.APIGatewayProxyResponse, error) {
	key := request.QueryStringParameters["filename"]

	_, err := h.AwsUseCase.DeleteObject(key)
	if err != nil {
		return events.APIGatewayProxyResponse{
			StatusCode: http.StatusInternalServerError,
			Body:       err.Error(),
		}, nil
	}

	return events.APIGatewayProxyResponse{
		StatusCode: 200,
		Body:       "Deleted successfully!",
	}, nil
}
