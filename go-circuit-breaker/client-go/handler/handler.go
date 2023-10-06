package handler

import (
	"client-go/request"
	"encoding/json"
	"net/http"
)

func NewHandler(httpRequest request.HttpRequest) *Handler {
	return &Handler{httpRequest}
}

type Handler struct {
	httpRequest request.HttpRequest
}

func (h *Handler) Ping(w http.ResponseWriter, r *http.Request) {

	res, err := h.httpRequest.PingToServerApp()
	data := map[string]string{
		"message": "Message",
		"data":    string(res),
	}

	byteData, _ := json.Marshal(data)
	if err != nil {
		w.Write(byteData)
		return
	}
	w.Write(byteData)
}
