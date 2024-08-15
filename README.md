Start the cluster in aks

az account show
Login ---- az login --use-device-code
az aks get-credentials --resource-group ecomm-azure-resourcegroup --name ecomm-azure-aks-cluster
Delete the existing aws-secrets ----- kubectl delete secrets aws-secrets

create a new secrets using
kubectl create secret generic cosmosdb-secrets \
 --from-literal=endpoint="https://shopclues-cosmosdb.documents.azure.com:443/" \
 --from-literal=key="tHd5gEAZOyGtwhygEr2agd0UsR2f0u24cgfWcRO7z2JDwGCy1grSl3AawFyj0cFfIv1DFwRlZiyAACDbBAMy2Q==" \
 --from-literal=databaseName="ShopCluesDB"

create a new secrets using kubectl generic --from-
kubectl rollout restart deployment/backend-shopclues-app
