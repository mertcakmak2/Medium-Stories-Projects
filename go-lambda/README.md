
# Build And Zip Go App for AWS Lambda
https://github.com/aws/aws-lambda-go

```bash
GOOS=linux GOARCH=amd64 go build -o bootstrap main.go
zip lambda-handler.zip bootstrap
```

