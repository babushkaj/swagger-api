# NBRB API Wrapper k8s setup

## Description
The folder contains files to deploy the application in k8s.

See:
- [namespace.yml](./namespace.yml)
- [deployment-service.yml](./deployment-service.yml)

## Prerequisites to run it locally
You need to have installed on your machine:
- [Docker](https://docs.docker.com/desktop/setup/install/windows-install/)
- [Minikube](https://minikube.sigs.k8s.io/docs/start)

## Running locally
Run the next commands from the [root folder](./..) of the project. I use Git Bash to run the commands  on my Windows machine.

1. Open a terminal and start the minikube cluster:
```bash
minikube start --driver docker
```

2. Since the Minikube is run as a Docker container itself it's needed to switch to the docker daemon inside the container:
```bash
eval $(minikube docker-env) 
```
To roll the changes back after all manipulations with the repo/service run the command:
```bash
eval $(minikube docker-env -u)
```

3. Build a Docker Image:
```bash
docker build -t swagger-api .
```

4. Create a k8s namespace:
```bash
kubectl apply -f ./k8s/namespace.yml
```

5. Create a k8s deployment:
```bash
kubectl apply -f ./k8s/deployment-service.yml
```

6. Open another bash window and run the command to create a tunnel for the load balancer service:
```bash
minikube tunnel
```

Now you should have the page available in your browser: [index.html](http://localhost/swagger-ui/index.html)