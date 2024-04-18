package usecase

import (
	"encoding/base64"
	"fmt"
	"github.com/aws/aws-sdk-go/service/s3"
	"go-lambda/internal/file"
	"io"
)

type awsS3UseCase struct {
	awsS3Repository file.AwsS3Repository
	bucketName      string
	viewUrl         string
}

func NewAwsS3UseCase(awsS3Repository file.AwsS3Repository, bucketName string, viewUrl string) file.AwsS3UseCase {
	return &awsS3UseCase{awsS3Repository: awsS3Repository, bucketName: bucketName, viewUrl: viewUrl}
}

func (u *awsS3UseCase) PutObject(fileName string, buffer []byte) (string, error) {
	_, err := u.awsS3Repository.PutObject(fileName, buffer, u.bucketName)
	if err != nil {
		return "", err
	}

	viewUrl := fmt.Sprintf(u.viewUrl, fileName)
	return viewUrl, nil
}

func (u *awsS3UseCase) GetObject(fileName string) (string, error) {
	getObject, err := u.awsS3Repository.GetObject(fileName, u.bucketName)
	if err != nil {
		return "", err
	}
	defer getObject.Body.Close()

	content, err := io.ReadAll(getObject.Body)
	if err != nil {
		return "", err
	}

	base64Content := base64.StdEncoding.EncodeToString(content)
	return base64Content, nil
}

func (u *awsS3UseCase) DeleteObject(fileName string) (*s3.DeleteObjectOutput, error) {
	delObj, err := u.awsS3Repository.DeleteObject(fileName, u.bucketName)
	if err != nil {
		return nil, err
	}
	return delObj, nil
}
