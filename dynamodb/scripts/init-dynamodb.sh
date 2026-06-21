#!/bin/sh
set -e

ENDPOINT="http://dynamodb-local:8000"
REGION="ap-northeast-1"

echo "Waiting for DynamoDB Local to be ready..."
until aws dynamodb list-tables --endpoint-url $ENDPOINT --region $REGION > /dev/null 2>&1; do
  sleep 1
done

/bin/sh /scripts/create-tables.sh
/bin/sh /scripts/insert-seed-data.sh

echo "DynamoDB initialization completed."
