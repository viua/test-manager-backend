#!/usr/bin/env bash

BASEDIR=$(dirname "$0")

kubectl apply -f ${BASEDIR}/test-manager-secret.yml
kubectl apply -f ${BASEDIR}/test-manager-configmap.yml

# database
kubectl apply -f ${BASEDIR}/test-manager-database-pv.yml
kubectl apply -f ${BASEDIR}/test-manager-database.yml

# backend
kubectl apply -f ${BASEDIR}/test-manager-backend.yml

# curl http://192.168.99.100:30007/api/tm/test

# frontend
# k delete service test-manager-backend && k delete deployment test-manager-backend
# kubectl apply -f test-manager-frontend.te deployment yml

# once
# kubectl apply -f test-manager-database-pv.yml
# k get pvc
# test-manager-database-pv
# k delete service test-manager-backend && k delete deployment test-manager-backend