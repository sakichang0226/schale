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

echo "Inserting seed data into orders table..."
aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name orders \
  --item '{
    "user_id": {"N": "1"},
    "order_id": {"N": "1001"},
    "created_at": {"N": "1700000100000"},
    "sub_order_ids": {"NS": ["1", "2", "3"]}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name orders \
  --item '{
    "user_id": {"N": "1"},
    "order_id": {"N": "1002"},
    "created_at": {"N": "1700000200000"},
    "sub_order_ids": {"NS": ["4"]}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name orders \
  --item '{
    "user_id": {"N": "2"},
    "order_id": {"N": "1003"},
    "created_at": {"N": "1700000300000"},
    "sub_order_ids": {"NS": ["5", "6"]}
  }'

echo "Inserting seed data into sub_orders table..."
aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sub_orders \
  --item '{
    "order_id": {"N": "1001"},
    "sub_order_id": {"N": "1"},
    "user_id": {"N": "1"},
    "shop_id": {"N": "10001"},
    "product_id": {"N": "1"},
    "product_name": {"S": "プレミアムコットンTシャツ"},
    "price": {"N": "2480"},
    "order_num": {"N": "2"},
    "created_at": {"N": "1700000100000"},
    "delivery_status": {"S": "ED"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sub_orders \
  --item '{
    "order_id": {"N": "1001"},
    "sub_order_id": {"N": "2"},
    "user_id": {"N": "1"},
    "shop_id": {"N": "10001"},
    "product_id": {"N": "2"},
    "product_name": {"S": "ワイヤレスイヤホン Pro"},
    "price": {"N": "8980"},
    "order_num": {"N": "1"},
    "created_at": {"N": "1700000100000"},
    "delivery_status": {"S": "ED"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sub_orders \
  --item '{
    "order_id": {"N": "1001"},
    "sub_order_id": {"N": "3"},
    "user_id": {"N": "1"},
    "shop_id": {"N": "10002"},
    "product_id": {"N": "3"},
    "product_name": {"S": "UVカットキャップ"},
    "price": {"N": "990"},
    "order_num": {"N": "2"},
    "created_at": {"N": "1700000100000"},
    "delivery_status": {"S": "ED"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sub_orders \
  --item '{
    "order_id": {"N": "1002"},
    "sub_order_id": {"N": "4"},
    "user_id": {"N": "1"},
    "shop_id": {"N": "10001"},
    "product_id": {"N": "1"},
    "product_name": {"S": "プレミアムコットンTシャツ"},
    "price": {"N": "2480"},
    "order_num": {"N": "1"},
    "created_at": {"N": "1700000200000"},
    "delivery_status": {"S": "PR"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sub_orders \
  --item '{
    "order_id": {"N": "1003"},
    "sub_order_id": {"N": "5"},
    "user_id": {"N": "2"},
    "shop_id": {"N": "10001"},
    "product_id": {"N": "1"},
    "product_name": {"S": "プレミアムコットンTシャツ"},
    "price": {"N": "2480"},
    "order_num": {"N": "3"},
    "created_at": {"N": "1700000300000"},
    "delivery_status": {"S": "PR"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sub_orders \
  --item '{
    "order_id": {"N": "1003"},
    "sub_order_id": {"N": "6"},
    "user_id": {"N": "2"},
    "shop_id": {"N": "10002"},
    "product_id": {"N": "2"},
    "product_name": {"S": "ワイヤレスイヤホン Pro"},
    "price": {"N": "8980"},
    "order_num": {"N": "2"},
    "created_at": {"N": "1700000300000"},
    "delivery_status": {"S": "ED"}
  }'

echo "Inserting seed data into sequences table..."
aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sequences \
  --item '{
    "sequence_name": {"S": "order_id"},
    "current_value": {"N": "1003"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name sequences \
  --item '{
    "sequence_name": {"S": "sub_order_id"},
    "current_value": {"N": "6"}
  }'

echo "All seed data inserted successfully."
