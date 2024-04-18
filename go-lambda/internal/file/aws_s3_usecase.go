package file

import "github.com/aws/aws-sdk-go/service/s3"

type AwsS3UseCase interface {
	PutObject(fileName string, buffer []byte) (string, error)
	GetObject(fileName string) (string, error)
	DeleteObject(fileName string) (*s3.DeleteObjectOutput, error)
}
