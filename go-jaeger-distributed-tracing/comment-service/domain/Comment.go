package domain

type Comment struct {
	ID      string `json:"id"`
	Content string `json:"content"`
	PostID  string `json:"postId"`
}
