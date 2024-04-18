package storage

import (
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/credentials"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/s3"
	"go-lambda/config"
)

func NewAwsS3(c config.Aws) *s3.S3 {
	sess := session.Must(session.NewSession(&aws.Config{
		Region: aws.String(c.S3Region),
		Credentials: credentials.NewStaticCredentials(
			c.AccessKey,
			c.AccessSecretKey,
			""),
	}))

	return s3.New(sess)
}
