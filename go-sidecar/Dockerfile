FROM golang:1.22-alpine as builder
WORKDIR /go/app
COPY . .
RUN go build -v -o app cmd/api/main.go
FROM alpine
COPY --from=builder /go/app/ .
EXPOSE 8080
CMD ["/app"]