package file

import "github.com/aws/aws-sdk-go/service/s3"

type AwsS3Repository interface {
	PutObject(fileName string, buffer []byte, bucket string) (*s3.PutObjectOutput, error)
	GetObject(fileName string, bucket string) (*s3.GetObjectOutput, error)
	DeleteObject(fileName string, bucket string) (*s3.DeleteObjectOutput, error)
}
