Start the cluster in aks

Login ---- az login --use-device-code
Delete the existing aws-secrets ----- kubectl delete secrets aws-secrets
create a new secrets using kubectl generic --from-literal
kubectl rollout restart deployment/backend-shopclues-app
