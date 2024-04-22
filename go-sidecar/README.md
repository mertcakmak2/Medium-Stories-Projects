### Docker Build Api Container

```bash
docker build -t mertcakmak2/go-container .
```

### Docker Build Sidecar Container

```bash
 docker build -f Dockerfile.sidecar -t mertcakmak2/go-sidecar .
```

### Deployment on K8S

```bash
  kubectl apply -f k8s-deployment.yaml
```

### Minikube Go Container Service URL

```bash
  minikube service go-container-sidecar --url
```

### Minikube Dashboard Service URL

```bash
minikube service kubernetes-dashboard --url -n kubernetes-dashboard
```

### Send HTTP Request

```bash
curl localhost:<MINIKUPE_SERVICE_PORT>/ping
```
