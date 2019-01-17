# ProductsApi

All URIs are relative to *https://localhost:8081*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addProduct**](ProductsApi.md#addProduct) | **POST** /products | Adds a new productBackend
[**deleteProduct**](ProductsApi.md#deleteProduct) | **DELETE** /products/{id} | Deletes a productBackend
[**getProduct**](ProductsApi.md#getProduct) | **GET** /products/{id} | Retrieves a productBackend
[**queryProducts**](ProductsApi.md#queryProducts) | **GET** /products | Queries products. If no parameters is provided all products will be returned


<a name="addProduct"></a>
# **addProduct**
> Product addProduct(productBackend)

Adds a new productBackend



### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.ProductsApi;


ProductsApi apiInstance = new ProductsApi();
Product productBackend = new Product(); // Product | The inserted productBackend
try {
    Product result = apiInstance.addProduct(productBackend);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#addProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **productBackend** | [**Product**](Product.md)| The inserted productBackend |

### Return type

[**Product**](Product.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteProduct"></a>
# **deleteProduct**
> deleteProduct(id)

Deletes a productBackend



### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.ProductsApi;


ProductsApi apiInstance = new ProductsApi();
Long id = 789L; // Long | The id of the to be deleted productBackend
try {
    apiInstance.deleteProduct(id);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#deleteProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| The id of the to be deleted productBackend |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getProduct"></a>
# **getProduct**
> Product getProduct(id)

Retrieves a productBackend



### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.ProductsApi;


ProductsApi apiInstance = new ProductsApi();
Long id = 789L; // Long | The id of the to be retrieved productBackend
try {
    Product result = apiInstance.getProduct(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#getProduct");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| The id of the to be retrieved productBackend |

### Return type

[**Product**](Product.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="queryProducts"></a>
# **queryProducts**
> List&lt;Product&gt; queryProducts(query)

Queries products. If no parameters is provided all products will be returned



### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.ProductsApi;


ProductsApi apiInstance = new ProductsApi();
ProductQuery query = new ProductQuery(); // ProductQuery | The name of the productBackend
try {
    List<Product> result = apiInstance.queryProducts(query);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ProductsApi#queryProducts");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **query** | [**ProductQuery**](ProductQuery.md)| The name of the productBackend | [optional]

### Return type

[**List&lt;Product&gt;**](Product.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

