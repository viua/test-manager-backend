
kubectl delete configmap test-manager-configmap
kubectl delete secret test-manager-secret
kubectl delete service test-manager-backend test-manager-database
kubectl delete deployment test-manager-backend test-manager-database
kubectl delete pvc test-manager-database-pv

# kubectl delete deployment focus-booster-frontend focus-booster focus-booster-database