# LogParser

A simple Spring Boot service that analyses raw logs using an LLM (via [LangChain4j](https://docs.langchain4j.dev/) + [Ollama](https://ollama.com/)).

## Requirements

- Java 25
- Access to a Kubernetes cluster with `kubectl` configured for it

## Running locally

1. Deploy Ollama to Kubernetes and port-forward it — see [Running Ollama in Kubernetes](#running-ollama-in-kubernetes) below.

2. Build and run the application:

```bash
./mvnw spring-boot:run
```

The app starts on port `8080` by default, under the `/logparser/api` context path.

3. Example request:

```bash
curl -X POST http://localhost:8080/logparser/api/v1/analyser \
  -H "Content-Type: application/json" \
  -d '{"rawLog": "2026-09-03 ERROR NullPointerException at ..."}'
```

## Running Ollama in Kubernetes

The [infra](infra) directory contains a Helm chart that deploys Ollama and Qdrant to the cluster.

1. Install the chart:

```bash
helm install ollama-dev infra
```

This creates the Ollama `Deployment`, `PersistentVolumeClaim` and `Service`, along with the Qdrant `StatefulSet` and `Service`. Chart defaults (image tags, storage sizes, replica counts, etc.) can be overridden with `--set` or a custom values file, e.g.:

```bash
helm install ollama-dev infra --set ollama.persistence.storage=10Gi
```

To apply changes after editing the chart:

```bash
helm upgrade ollama-dev infra
```

To remove the release:

```bash
helm uninstall ollama-dev
```

2. Ollama is exposed as a `ClusterIP` service, so it's not reachable from outside the cluster — you need to port-forward it:

```bash
kubectl port-forward svc/ollama-dev-ollama-service 11434:11434
```

Once that's running, Ollama will be available locally at `http://localhost:11434`, matching the configuration in `application.yaml`.
