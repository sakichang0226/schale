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

echo "Creating orders table..."
aws dynamodb create-table \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name orders \
  --attribute-definitions \
    AttributeName=user_id,AttributeType=N \
    AttributeName=order_id,AttributeType=N \
  --key-schema \
    AttributeName=user_id,KeyType=HASH \
    AttributeName=order_id,KeyType=RANGE \
  --billing-mode PAY_PER_REQUEST \
  2>/dev/null || echo "orders table already exists."

echo "Creating sub_orders table..."
aws dynamodb create-table \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sub_orders \
  --attribute-definitions \
    AttributeName=order_id,AttributeType=N \
    AttributeName=sub_order_id,AttributeType=N \
    AttributeName=shop_id,AttributeType=N \
    AttributeName=created_at,AttributeType=N \
  --key-schema \
    AttributeName=order_id,KeyType=HASH \
    AttributeName=sub_order_id,KeyType=RANGE \
  --global-secondary-indexes \
    '[{
      "IndexName": "shop-id-index",
      "KeySchema": [{"AttributeName": "shop_id", "KeyType": "HASH"}, {"AttributeName": "created_at", "KeyType": "RANGE"}],
      "Projection": {"ProjectionType": "ALL"}
    }]' \
  --billing-mode PAY_PER_REQUEST \
  2>/dev/null || echo "sub_orders table already exists."

echo "Creating sequences table..."
aws dynamodb create-table \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sequences \
  --attribute-definitions \
    AttributeName=sequence_name,AttributeType=S \
  --key-schema \
    AttributeName=sequence_name,KeyType=HASH \
  --billing-mode PAY_PER_REQUEST \
  2>/dev/null || echo "sequences table already exists."

echo "All tables created successfully."
