#!/bin/bash

echo "Tearing down database: Start"
docker-compose down --rmi all --volumes
rm -rf ./data
echo "Tearing down database: Complete"