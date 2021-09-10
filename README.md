# API Documentation

## CREATE Product
Request :
- Method: POST
- Endpoint: `api/products`
- Header:
  - Content-Type: application/json
  - Accept: application/json
- Body

```json
{
  "name" : "string",
  "price" : "long",
  "quantity" : "integer"
}
```
Response

```json
{
  "status": "string",
  "message": "string",
  "data": {
    "id": "string, unique",
    "name": "string",
    "price": "long",
    "quantity": "integer",
    "created_at": "date",
    "updated_at": "date"
  }
}
```

## READ Product
Request :
- Method: GET
- Endpoint: `api/products/{id_product}`
- Header:
  - Accept: application/json

```json
{
  "status": "string",
  "message": "string",
  "data": {
    "id": "string, unique",
    "name": "string",
    "price": "long",
    "quantity": "integer",
    "created_at": "date",
    "updated_at": "date"
  }
}
```

## UPDATE Product
Request :
- Method: PUT
- Endpoint: `api/products/{id_product}`
- Header:
  - Content-Type: application/json
  - Accept: application/json
- Body

```json
{
  "name" : "string",
  "price" : "long",
  "quantity" : "integer"
}
```
Response

```json
{
  "status": "string",
  "message": "string",
  "data": {
    "id": "string, unique",
    "name": "string",
    "price": "long",
    "quantity": "integer",
    "created_at": "date",
    "updated_at": "date"
  }
}
```

## DELETE Product
Request :
- Method: DELETE
- Endpoint: `api/products/{id_product}`
- Header:
  - Content-Type: application/json
  - Accept: application/json

Response

```json
{
  "status": "string",
  "message": "string"
}
```

## LIST Product
Request :
- Method: GET
- Endpoint: `api/products`
- Header:
  - Accept: application/json
- Query Param:
  - size: number,
  - page: number

```json
{
  "status": "string",
  "message": "string",
  "data": [
    {
      "id": "string, unique",
      "name": "string",
      "price": "long",
      "quantity": "integer",
      "created_at": "date",
      "updated_at": "date"
    },
    {
      "id": "string, unique",
      "name": "string",
      "price": "long",
      "quantity": "integer",
      "created_at": "date",
      "updated_at": "date"
    }
  ]
}
```

