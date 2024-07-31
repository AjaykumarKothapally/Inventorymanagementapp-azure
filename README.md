# Setting Up and Deploying Applications on AWS EKS

## Configure EKS Cluster Locally

1. **Install AWS CLI**: Follow [AWS CLI Installation Guide](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-quickstart.html).

2. **Configure AWS CLI**:

   ```bash
   aws configure

   Enter your AWS Access Key ID, Secret Access Key, default region, and output format.
   ```

3. **Install kubectl** : Install kubectl using your package manager or download from Kubernetes Official Site.

4. **Configure kubectl** :

   ```bash
   aws eks --region <region> update-kubeconfig --name <cluster-name>

   Replace <region> with your AWS region and <cluster-name> with your EKS cluster name.
   ```

5. **Create Kubernetes Secrets**: Create Kubernetes secrets to store AWS credentials securely.

   ```bash
   kubectl create secret generic aws-secrets \
   --from-literal=AWS_ACCESS_KEY_ID=<your-access-key-id> \
   --from-literal=AWS_SECRET_ACCESS_KEY=<your-secret-access-key> \
   --from-literal=AWS_SESSION_TOKEN=<your-session-token>
   ```

## Deploy Services (Load Balancers)

6.  **Deploy your applications using Kubernetes service YAML files. Here's an example for a service exposing a backend application**:

    ```bash
    service.yaml

    yaml
    Copy code
    apiVersion: v1
    kind: Service
    metadata:
    name: backend-service
    spec:
    selector:
    app: backend-app
    ports: - protocol: TCP
    port: 80
    targetPort: 8080
    type: LoadBalancer
    ```

7.  **Apply the service YAML**:

    ```bash
    kubectl apply -f service.yaml
    ```

8.  **Retrieve Load Balancer DNS and Update Application Files**
    Retrieve the load balancer DNS of each application's service:

    ```bash
       kubectl get services

       Retrieve all the deployed service
    ```

Update your application files (deployment.yaml, config.yaml, etc.) with the retrieved load balancer DNS.

Commit Changes to GitHub
Commit your changes to your GitHub repository:

git add .
git commit -m "Updated load balancer DNS in application files"
git push origin main
