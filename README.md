# Cold-Chain Logistics: A DevOps-Driven Microservices Platform

## Project Overview
This project implements a cloud-native logistics monitoring system designed for temperature-sensitive cargo. It utilizes a microservices architecture to ensure scalability and high availability, managed through a complete automated DevOps lifecycle.

## Tech Stack
- **Microservices:** Python (FastAPI), Node.js (Express), Java (Spring Boot)
- **Containerization:** Docker & Docker Compose
- **Orchestration:** Kubernetes (Minikube)
- **CI/CD:** GitHub Actions
- **Infrastructure as Code:** Terraform (Azure AKS provider)
- **Configuration Management:** Ansible

## Key DevOps Features
- **Automated CI/CD:** Every push to `main` triggers an automated build and test pipeline via GitHub Actions.
- **Self-Healing:** Kubernetes monitors pod health and automatically restarts or replaces failing containers.
- **Infrastructure as Code:** The entire cloud environment is defined in Terraform, allowing for one-click infrastructure provisioning.
- **Clustering & Scalability:** Deployment is configured with multiple replicas to handle high-traffic sensor data.

## How to Run Locally
1. **Start Kubernetes:** `minikube start`
2. **Load Images:** `minikube image load tracking-service:v2`
3. **Deploy:** `kubectl apply -f k8s-manifests/tracking-deployment.yaml`
4. **Access UI:** `minikube service tracking-service`