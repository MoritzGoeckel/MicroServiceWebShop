# CategoriesApi

All URIs are relative to *https://localhost:8083*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addCategory**](CategoriesApi.md#addCategory) | **POST** /categories | Adds a new category
[**deleteCategory**](CategoriesApi.md#deleteCategory) | **DELETE** /categories/{id} | Deletes a category
[**getCategory**](CategoriesApi.md#getCategory) | **GET** /categories/{id} | Retrieves a category
[**queryCategories**](CategoriesApi.md#queryCategories) | **GET** /categories | Queries categories. If no name is provided all categories will be returned


<a name="addCategory"></a>
# **addCategory**
> Category addCategory(name)

Adds a new category



### Example
```java
// Import classes:
//import hska.microServiceWebShop.api.ApiException;
//import hska.microServiceWebShop.api.CategoryService.CategoriesApi;


CategoriesApi apiInstance = new CategoriesApi();
String name = "name_example"; // String | The name of the category
try {
    Category result = apiInstance.addCategory(name);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#addCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| The name of the category |

### Return type

[**Category**](Category.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="deleteCategory"></a>
# **deleteCategory**
> deleteCategory(id)

Deletes a category



### Example
```java
// Import classes:
//import hska.microServiceWebShop.api.ApiException;
//import hska.microServiceWebShop.api.CategoryService.CategoriesApi;


CategoriesApi apiInstance = new CategoriesApi();
Long id = 789L; // Long | The id of the to be deleted category
try {
    apiInstance.deleteCategory(id);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#deleteCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| The id of the to be deleted category |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="getCategory"></a>
# **getCategory**
> Category getCategory(id)

Retrieves a category



### Example
```java
// Import classes:
//import hska.microServiceWebShop.api.ApiException;
//import hska.microServiceWebShop.api.CategoryService.CategoriesApi;


CategoriesApi apiInstance = new CategoriesApi();
Long id = 789L; // Long | The id of the to be retrieved category
try {
    Category result = apiInstance.getCategory(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#getCategory");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| The id of the to be retrieved category |

### Return type

[**Category**](Category.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryCategories"></a>
# **queryCategories**
> List&lt;Category&gt; queryCategories(name)

Queries categories. If no name is provided all categories will be returned



### Example
```java
// Import classes:
//import hska.microServiceWebShop.api.ApiException;
//import hska.microServiceWebShop.api.CategoryService.CategoriesApi;


CategoriesApi apiInstance = new CategoriesApi();
String name = "name_example"; // String | The name of the category
try {
    List<Category> result = apiInstance.queryCategories(name);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CategoriesApi#queryCategories");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **name** | **String**| The name of the category | [optional]

### Return type

[**List&lt;Category&gt;**](Category.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

