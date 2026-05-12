#!/bin/sh
set -e

ENDPOINT="http://dynamodb-local:8000"
REGION="ap-northeast-1"

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

echo "Creating products table..."
aws dynamodb create-table \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --attribute-definitions \
    AttributeName=product_id,AttributeType=N \
    AttributeName=shop_id,AttributeType=N \
    AttributeName=category_id,AttributeType=N \
    AttributeName=created_at,AttributeType=N \
  --key-schema \
    AttributeName=product_id,KeyType=HASH \
  --global-secondary-indexes \
    '[{
      "IndexName": "shop-product-index",
      "KeySchema": [{"AttributeName": "shop_id", "KeyType": "HASH"}, {"AttributeName": "product_id", "KeyType": "RANGE"}],
      "Projection": {"ProjectionType": "ALL"}
    },
    {
      "IndexName": "category-product-index",
      "KeySchema": [{"AttributeName": "category_id", "KeyType": "HASH"}, {"AttributeName": "created_at", "KeyType": "RANGE"}],
      "Projection": {"ProjectionType": "ALL"}
    }]' \
  --billing-mode PAY_PER_REQUEST \
  2>/dev/null || echo "products table already exists."

echo "All tables created successfully."
