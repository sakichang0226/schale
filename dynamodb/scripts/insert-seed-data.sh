#!/bin/sh
set -e

ENDPOINT="http://dynamodb-local:8000"
REGION="ap-northeast-1"

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

echo "Inserting seed data into products table..."
aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "1"},
    "product_name": {"S": "プレミアムコットンTシャツ"},
    "description": {"S": "上質なオーガニックコットン100%使用。肌触りが良く、日常使いに最適です。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/1.jpg"},
    "shop_id": {"N": "10001"},
    "category_id": {"N": "32"},
    "price": {"N": "2480"},
    "tax_type": {"S": "I"},
    "rating": {"N": "4.0"},
    "review_count": {"N": "12"},
    "stock": {"N": "50"},
    "status": {"S": "O"},
    "created_at": {"N": "1496918153734"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "2"},
    "product_name": {"S": "ワイヤレスイヤホン Pro"},
    "description": {"S": "ノイズキャンセリング搭載。高音質で長時間バッテリー。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/2.jpg"},
    "shop_id": {"N": "10002"},
    "category_id": {"N": "15"},
    "price": {"N": "8980"},
    "tax_type": {"S": "E"},
    "rating": {"N": "4.5"},
    "review_count": {"N": "38"},
    "stock": {"N": "120"},
    "status": {"S": "O"},
    "created_at": {"N": "1496918200000"}
  }'

echo "All seed data inserted successfully."
