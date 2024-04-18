package main

import (
	"github.com/aws/aws-lambda-go/lambda"
	"go-lambda/config"
	"go-lambda/internal/file/handler"
	"go-lambda/internal/file/repository"
	"go-lambda/internal/file/usecase"
	"go-lambda/pkg/storage"
)

func main() {
	c := config.NewConfig()
	s3Client := storage.NewAwsS3(*c.Aws)
	s3Repo := repository.NewAwsS3Repository(s3Client)
	s3UseCase := usecase.NewAwsS3UseCase(s3Repo, c.Aws.S3ImageBucket, c.Aws.ViewUrl)
	lambdaHandler := handler.NewHandler(s3UseCase)

	lambda.Start(lambdaHandler.View)
}
