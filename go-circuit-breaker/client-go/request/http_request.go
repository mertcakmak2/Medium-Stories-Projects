package request

import (
	"fmt"
	"github.com/sony/gobreaker"
	"io/ioutil"
	"log"
	"net/http"
)

func NewHttpRequest(cb *gobreaker.CircuitBreaker, client http.Client, url string) *HttpRequest {
	return &HttpRequest{
		cb:     cb,
		client: client,
		url:    url,
	}
}

type HttpRequest struct {
	cb     *gobreaker.CircuitBreaker
	client http.Client
	url    string
}

func (h *HttpRequest) PingToServerApp() ([]byte, error) {
	body, err := h.cb.Execute(func() (interface{}, error) {

		req, err := http.NewRequest(http.MethodGet, h.url, nil)
		if err != nil {
			log.Fatal(err)
		}

		resp, err := h.client.Do(req)
		if err != nil {
			fmt.Println("Errored when sending request to the server")
			return nil, err
		}
		defer resp.Body.Close()

		response, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			return nil, err
		}
		return response, nil
	})
	if err != nil {
		return []byte("pong from client-go"), err
	}

	return body.([]byte), nil
}
