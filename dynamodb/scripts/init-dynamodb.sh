#!/bin/sh
set -e

ENDPOINT="http://dynamodb-local:8000"
REGION="ap-northeast-1"

echo "Waiting for DynamoDB Local to be ready..."
until aws dynamodb list-tables --endpoint-url $ENDPOINT --region $REGION > /dev/null 2>&1; do
  sleep 1
done

echo "Creating users table..."
aws dynamodb create-table \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name users \
  --attribute-definitions \
    AttributeName=user_id,AttributeType=N \
    AttributeName=email,AttributeType=S \
  --key-schema \
    AttributeName=user_id,KeyType=HASH \
  --global-secondary-indexes \
    '[{
      "IndexName": "email-index",
      "KeySchema": [{"AttributeName": "email", "KeyType": "HASH"}],
      "Projection": {"ProjectionType": "ALL"}
    }]' \
  --billing-mode PAY_PER_REQUEST \
  2>/dev/null || echo "users table already exists."

echo "Inserting seed data into users table..."
# password: "password123" のbcryptハッシュ
aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name users \
  --item '{
    "user_id": {"N": "1"},
    "email": {"S": "test@example.com"},
    "password_hash": {"S": "$2b$10$mtaWEu5waQT1IFVXLok1gOH96lsACBJOEtH6ICGQCzagdQjc6CfIW"},
    "user_name": {"S": "テストユーザー"},
    "created_at": {"N": "1700000000000"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name users \
  --item '{
    "user_id": {"N": "2"},
    "email": {"S": "user2@example.com"},
    "password_hash": {"S": "$2b$10$mtaWEu5waQT1IFVXLok1gOH96lsACBJOEtH6ICGQCzagdQjc6CfIW"},
    "user_name": {"S": "ユーザー2"},
    "created_at": {"N": "1700000000000"}
  }'

echo "Seed data inserted successfully."
