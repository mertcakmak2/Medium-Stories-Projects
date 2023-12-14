docker build -t spring-app .

docker run --memory=1024M --cpus=2 --name spring-app -d -p 8080:8080 spring-app