package repository

import (
	"bytes"
	"errors"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/service/s3"
	"go-lambda/internal/file"
	"net/http"
)

type awsS3Repository struct {
	s3Client *s3.S3
}

func NewAwsS3Repository(s3Client *s3.S3) file.AwsS3Repository {
	return &awsS3Repository{s3Client: s3Client}
}

func (r *awsS3Repository) PutObject(fileName string, buffer []byte, bucket string) (*s3.PutObjectOutput, error) {
	putObject, err := r.s3Client.PutObject(&s3.PutObjectInput{
		Bucket:        aws.String(bucket),
		Key:           aws.String(fileName),
		Body:          bytes.NewReader(buffer),
		ContentType:   aws.String(http.DetectContentType(buffer)),
		ContentLength: aws.Int64(int64(len(buffer))),
	})

	if err != nil {
		return nil, errors.New(err.Error())
	}

	return putObject, nil
}

func (r *awsS3Repository) GetObject(fileName string, bucket string) (*s3.GetObjectOutput, error) {
	getObject, err := r.s3Client.GetObject(&s3.GetObjectInput{
		Bucket: aws.String(bucket),
		Key:    aws.String(fileName),
	})

	if err != nil {
		return nil, errors.New(err.Error())
	}

	return getObject, nil
}

func (r *awsS3Repository) DeleteObject(fileName string, bucket string) (*s3.DeleteObjectOutput, error) {
	delObject, err := r.s3Client.DeleteObject(&s3.DeleteObjectInput{
		Bucket: aws.String(bucket),
		Key:    aws.String(fileName),
	})

	if err != nil {
		return nil, errors.New(err.Error())
	}

	return delObject, nil
}
