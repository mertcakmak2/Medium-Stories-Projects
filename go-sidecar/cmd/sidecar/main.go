package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"net/http"
	"net/http/httputil"
	"net/url"
)

func main() {
	fmt.Println("Sidecar Go!")

	r := gin.Default()
	// Reverse Proxy
	r.Any("/*proxyPath", authProxy)
	r.Run(":8081")
}

// Simulate Auth
func authProxy(c *gin.Context) {

	// Bearer Token Check...

	// MAIN CONTAINER URL
	remote, err := url.Parse("http://localhost:8080")
	if err != nil {
		panic(err)
	}

	proxy := httputil.NewSingleHostReverseProxy(remote)
	proxy.Director = func(req *http.Request) {
		req.Header = c.Request.Header
		req.Host = remote.Host
		req.URL.Scheme = remote.Scheme
		req.URL.Host = remote.Host
		req.URL.Path = c.Param("proxyPath")
	}

	proxy.ServeHTTP(c.Writer, c.Request)
}
