# Define the required provider
terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "~> 3.0"
    }
  }
}

provider "azurerm" {
  features {}
}

# Create a Resource Group
resource "azurerm_resource_group" "cold_chain_rg" {
  name     = "rg-coldchain-devops"
  location = "East US"
}

# Provision the Kubernetes Cluster (AKS)
resource "azurerm_kubernetes_cluster" "aks_cluster" {
  name                = "aks-coldchain-cluster"
  location            = azurerm_resource_group.cold_chain_rg.location
  resource_group_name = azurerm_resource_group.cold_chain_rg.name
  dns_prefix          = "coldchain"

  default_node_pool {
    name       = "default"
    node_count = 2 # Supports clustering and scalability
    vm_size    = "Standard_B2s"
  }

  identity {
    type = "SystemAssigned"
  }

  tags = {
    Environment = "Development"
    Project     = "Cold Chain Logistics"
  }
}

# Output the cluster configuration command
output "kube_config_command" {
  value = "az aks get-credentials --resource-group ${azurerm_resource_group.cold_chain_rg.name} --name ${azurerm_kubernetes_cluster.aks_cluster.name}"
}