## Online generators

One can also generate API client or server using the online generators (https://generator.swagger.io)

For example, to generate Ruby API client, simply send the following HTTP request using curl:
```sh
curl -X POST -H "content-type:application/json" -d '{"swaggerUrl":"http://petstore.swagger.io/v2/swagger.json"}' https://generator.swagger.io/api/gen/clients/ruby
```
Then you will receieve a JSON response with the URL to download the zipped code.

To customize the SDK, you can `POST` to `https://generator.swagger.io/gen/clients/{language}` with the following HTTP body:
```json
{
  "options": {},
  "swaggerUrl": "http://petstore.swagger.io/v2/swagger.json"
}
```
in which the `options` for a language can be obtained by submitting a `GET` request to `https://generator.swagger.io/api/gen/clients/{language}`:

For example, `curl https://generator.swagger.io/api/gen/clients/python` returns
```json
{
  "packageName":{
    "opt":"packageName",
    "description":"python package name (convention: snake_case).",
    "type":"string",
    "default":"swagger_client"
  },
  "packageVersion":{
    "opt":"packageVersion",
    "description":"python package version.",
    "type":"string",
    "default":"1.0.0"
  },
  "sortParamsByRequiredFlag":{
    "opt":"sortParamsByRequiredFlag",
    "description":"Sort method arguments to place required parameters before optional parameters.",
    "type":"boolean",
    "default":"true"
  }
}
```
To set package name to `pet_store`, the HTTP body of the request is as follows:
```json
{
  "options": {
    "packageName": "pet_store"
  },
  "swaggerUrl": "http://petstore.swagger.io/v2/swagger.json"
}
```
and here is the curl command:
```sh
curl -H "Content-type: application/json" -X POST -d '{"options": {"packageName": "pet_store"},"swaggerUrl": "http://petstore.swagger.io/v2/swagger.json"}' https://generator.swagger.io/api/gen/clients/python
```

Instead of using `swaggerUrl` with an URL to the OpenAPI/Swagger spec, one can include the spec in the JSON payload with `spec`, e.g.
```json
{
  "options": {},
  "spec": {
    "swagger": "2.0",
    "info": {
      "version": "1.0.0",
      "title": "Test API"
    },
    ...
  }
}
```

e.g.:

```json
{
  "swagger": "2.0",
  "info": {
    "description": "",
    "version": "1.0.0",
    "title": "Product Service",
    "contact": {
      "email": "bhb127@outlook.de"
    }
  },
  "host": "localhost:8082",
  "basePath": "/",
  "tags": [
    {
      "name": "products",
      "description": ""
    }
  ],
  "paths": {
    "/products": {
      "post": {
        "tags": [
          "products"
        ],
        "summary": "Adds a new product",
        "description": "",
        "operationId": "add product",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json"
        ],
        "parameters": [
          {
            "name": "Product",
            "in": "body",
            "description": "The inserted product",
            "required": true,
            "schema": {
              "$ref": "#/definitions/Product"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successful operation",
            "schema": {
              "$ref": "#/definitions/Product"
            }
          },
          "400": {
            "description": "Invalid input",
            "schema": {
              "$ref": "#/definitions/Error"
            }
          },
          "409": {
            "description": "Conflict",
            "schema": {
              "$ref": "#/definitions/Error"
            }
          }
        }
      },
      "get": {
        "tags": [
          "products"
        ],
        "summary": "Queries products. If no parameters is provided all products will be returned",
        "description": "",
        "operationId": "query products",
        "consumes": [
          "application/json"
        ],
        "produces": [
          "application/json"
        ],
        "parameters": [
          {
            "name": "Query",
            "in": "body",
            "description": "The name of the product",
            "required": false,
            "schema": {
              "$ref": "#/definitions/ProductQuery"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "Successful operation",
            "schema": {
              "type": "array",
              "items": {
                "$ref": "#/definitions/Product"
              }
            }
          },
          "400": {
            "description": "bad request",
            "schema": {
              "$ref": "#/definitions/Error"
            }
          }
        }
      }
    },
    "/products/{id}": {
      "delete": {
        "tags": [
          "products"
        ],
        "summary": "Deletes a product",
        "description": "",
        "operationId": "delete product",
        "produces": [
          "application/json"
        ],
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "description": "The id of the to be deleted product",
            "required": true,
            "type": "integer",
            "format": "int64"
          }
        ],
        "responses": {
          "200": {
            "description": "Successful operation"
          },
          "404": {
            "description": "not found",
            "schema": {
              "$ref": "#/definitions/Error"
            }
          }
        }
      },
      "get": {
        "tags": [
          "products"
        ],
        "summary": "Retrieves a product",
        "description": "",
        "operationId": "get product",
        "produces": [
          "application/json"
        ],
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "description": "The id of the to be retrieved product",
            "required": true,
            "type": "integer",
            "format": "int64"
          }
        ],
        "responses": {
          "200": {
            "description": "Successful operation",
            "schema": {
              "$ref": "#/definitions/Product"
            }
          },
          "404": {
            "description": "not found",
            "schema": {
              "$ref": "#/definitions/Error"
            }
          }
        }
      }
    }
  },
  "definitions": {
    "Product": {
      "type": "object",
      "properties": {
        "id": {
          "type": "integer",
          "format": "int64"
        },
        "name": {
          "type": "string"
        },
        "price": {
          "type": "number",
          "format": "double"
        },
        "category": {
          "type": "integer",
          "format": "int64"
        },
        "details": {
          "type": "string"
        }
      }
    },
    "ProductQuery": {
      "type": "object",
      "properties": {
        "text": {
          "type": "string"
        },
        "priceMin": {
          "type": "number",
          "format": "double"
        },
        "priceMax": {
          "type": "number",
          "format": "double"
        },
        "category": {
          "type": "integer",
          "format": "int64"
        }
      }
    },
    "Error": {
      "type": "object",
      "properties": {
        "description": {
          "type": "string"
        }
      }
    }
  }
}
```