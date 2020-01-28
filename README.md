# CentricSoftware Assignement

## Technologies
+ Java 1.8
+ Spring boot 2.2.4
+ Spring Web
+ Spring Data
+ Spring JPA
+ H2 Database

##Test Coverage

| Element       | Coverage      |
| ------------- |:-------------:|
| Class         | 100%          |
| Method        | 87%           |
| Line          | 91%           |

## REST API

### Create a product

**URL** : `/v1/products`

**Method** : `POST`

**Response body**

```json
{
    "id": "f13d9443-1381-4098-be0f-4c87dbbe050d",
    "name": "coco",
    "description": "this is a product",
    "brand": "nike",
    "tags": [
        "momo"
    ],
    "category": "cloth",
    "created_at": "2020-01-28T10:38:47.153"
}
```

### Get all product paged

**URL** : `v1/products?category=cloth&limit=3&page=0`

**Method** : `GET`

**Response body**

```json
[
    {
        "id": "30f8a832-4bc1-458c-a7d1-7ea475b95149",
        "name": "coco",
        "description": "this is a product",
        "brand": "nike",
        "tags": [
            "momo"
        ],
        "category": "cloth",
        "created_at": "2020-01-28T13:32:55.676"
    },
    {
        "id": "1798d42a-7c83-46f6-b3a5-c83593644113",
        "name": "coco",
        "description": "this is a product",
        "brand": "nike",
        "tags": [
            "momo"
        ],
        "category": "cloth",
        "created_at": "2020-01-28T13:32:50.06"
    },
    {
        "id": "e2fd6058-71bf-48da-8c41-7a0bae4c6676",
        "name": "coco",
        "description": "this is a product",
        "brand": "nike",
        "tags": [
            "momo"
        ],
        "category": "cloth",
        "created_at": "2020-01-28T13:32:22.633"
    }
]
```