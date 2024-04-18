build-zip-view:
	GOOS=linux GOARCH=amd64 go build -o bootstrap ./cmd/view/main.go
	zip view-lambda-handler.zip bootstrap

build-zip-upload:
	GOOS=linux GOARCH=amd64 go build -o bootstrap ./cmd/upload/main.go
	zip upload-lambda-handler.zip bootstrap

build-zip-delete:
	GOOS=linux GOARCH=amd64 go build -o bootstrap ./cmd/delete/main.go
	zip delete-lambda-handler.zip bootstrap

build-zip: build-zip-view build-zip-upload build-zip-delete