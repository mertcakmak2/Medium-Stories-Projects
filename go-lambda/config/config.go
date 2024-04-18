package config

import (
	"context"
	"github.com/sethvargo/go-envconfig"
	"log"
	"sync"
)

var (
	cfg        appConfig
	configOnce sync.Once
)

func NewConfig() appConfig {
	configOnce.Do(func() {
		ctx := context.Background()
		if err := envconfig.Process(ctx, &cfg); err != nil {
			log.Fatal(err)
		}
		log.Println("Environments initialized.")
	})
	return cfg
}

type appConfig struct {
	Aws *Aws
}

type Aws struct {
	AccessKey       string `env:"AWS_ACCESS"`
	AccessSecretKey string `env:"AWS_ACCESS_SECRET"`
	S3Region        string `env:"AWS_S3_REGION"`
	S3ImageBucket   string `env:"AWS_S3_IMAGE_BUCKET"`
	ViewUrl         string `env:"VIEW_URL"`
}
