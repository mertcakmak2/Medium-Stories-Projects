package main

import (
	"fmt"

	"github.com/confluentinc/confluent-kafka-go/kafka"
)

func CreateConsumer() *kafka.Consumer {
	c, err := kafka.NewConsumer(&kafka.ConfigMap{
		"bootstrap.servers": "kafka:9092",
		"group.id":          "messageGroup",
		"auto.offset.reset": "earliest",
	})
	if err != nil {
		fmt.Println(err.Error())
	}
	//consumer.Consumer = c
	//return c
	return nil
}
