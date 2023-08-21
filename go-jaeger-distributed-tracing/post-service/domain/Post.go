package domain

import "post-service/model"

type Post struct {
	ID       string          `json:"id"`
	Title    string          `json:"title"`
	Comments []model.Comment `json:"comments"`
}
