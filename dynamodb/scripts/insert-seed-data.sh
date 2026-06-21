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

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "3"},
    "product_name": {"S": "UVカットキャップ"},
    "description": {"S": "紫外線99%カット。軽量で通気性抜群のスポーツキャップ。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/3.jpg"},
    "shop_id": {"N": "10002"},
    "category_id": {"N": "32"},
    "price": {"N": "990"},
    "tax_type": {"S": "I"},
    "rating": {"N": "3.5"},
    "review_count": {"N": "8"},
    "stock": {"N": "200"},
    "status": {"S": "O"},
    "created_at": {"N": "1496918300000"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "4"},
    "product_name": {"S": "ステンレスタンブラー 500ml"},
    "description": {"S": "真空断熱構造で保温保冷6時間。シンプルなデザインで持ち運びに便利。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/4.jpg"},
    "shop_id": {"N": "10003"},
    "category_id": {"N": "45"},
    "price": {"N": "1980"},
    "tax_type": {"S": "I"},
    "rating": {"N": "4.5"},
    "review_count": {"N": "25"},
    "stock": {"N": "80"},
    "status": {"S": "O"},
    "created_at": {"N": "1496918400000"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "5"},
    "product_name": {"S": "レザーミニウォレット"},
    "description": {"S": "本革使用のコンパクト財布。カード6枚収納可能。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/5.jpg"},
    "shop_id": {"N": "10001"},
    "category_id": {"N": "22"},
    "price": {"N": "4980"},
    "tax_type": {"S": "I"},
    "rating": {"N": "4.0"},
    "review_count": {"N": "15"},
    "stock": {"N": "30"},
    "status": {"S": "O"},
    "created_at": {"N": "1700000400000"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "6"},
    "product_name": {"S": "アロマディフューザー"},
    "description": {"S": "超音波式で静音設計。7色LEDライト付きでリラックス空間を演出。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/6.jpg"},
    "shop_id": {"N": "10003"},
    "category_id": {"N": "45"},
    "price": {"N": "3480"},
    "tax_type": {"S": "I"},
    "rating": {"N": "4.0"},
    "review_count": {"N": "20"},
    "stock": {"N": "45"},
    "status": {"S": "O"},
    "created_at": {"N": "1700000500000"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "7"},
    "product_name": {"S": "防水スマホポーチ"},
    "description": {"S": "IPX8防水対応。タッチ操作可能で海やプールに最適。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/7.jpg"},
    "shop_id": {"N": "10002"},
    "category_id": {"N": "15"},
    "price": {"N": "1280"},
    "tax_type": {"S": "I"},
    "rating": {"N": "3.5"},
    "review_count": {"N": "42"},
    "stock": {"N": "150"},
    "status": {"S": "O"},
    "created_at": {"N": "1700000600000"}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name products \
  --item '{
    "product_id": {"N": "8"},
    "product_name": {"S": "オーガニックハンドクリーム"},
    "description": {"S": "天然由来成分95%配合。べたつかず保湿力抜群。"},
    "image_url": {"S": "https://s3.ap-northeast-1.amazonaws.com/bucket/products/8.jpg"},
    "shop_id": {"N": "10001"},
    "category_id": {"N": "50"},
    "price": {"N": "780"},
    "tax_type": {"S": "N"},
    "rating": {"N": "4.5"},
    "review_count": {"N": "33"},
    "stock": {"N": "300"},
    "status": {"S": "O"},
    "created_at": {"N": "1700000700000"}
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
    "detail_ids": {"NS": ["1", "2", "3"]}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name orders \
  --item '{
    "user_id": {"N": "1"},
    "order_id": {"N": "1002"},
    "created_at": {"N": "1700000200000"},
    "detail_ids": {"NS": ["4"]}
  }'

aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name orders \
  --item '{
    "user_id": {"N": "2"},
    "order_id": {"N": "1003"},
    "created_at": {"N": "1700000300000"},
    "detail_ids": {"NS": ["5", "6"]}
  }'

echo "Inserting seed data into order_details table..."
aws dynamodb put-item \
  --endpoint-url $ENDPOINT \
  --region $REGION \
  --table-name order_details \
  --item '{
    "order_id": {"N": "1001"},
    "detail_id": {"N": "1"},
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
  --table-name order_details \
  --item '{
    "order_id": {"N": "1001"},
    "detail_id": {"N": "2"},
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
  --table-name order_details \
  --item '{
    "order_id": {"N": "1001"},
    "detail_id": {"N": "3"},
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
  --table-name order_details \
  --item '{
    "order_id": {"N": "1002"},
    "detail_id": {"N": "4"},
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
  --table-name order_details \
  --item '{
    "order_id": {"N": "1003"},
    "detail_id": {"N": "5"},
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
  --table-name order_details \
  --item '{
    "order_id": {"N": "1003"},
    "detail_id": {"N": "6"},
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
    "sequence_name": {"S": "detail_id"},
    "current_value": {"N": "6"}
  }'

echo "All seed data inserted successfully."
