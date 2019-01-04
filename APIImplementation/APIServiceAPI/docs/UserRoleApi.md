# UserRoleApi

All URIs are relative to *https://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createRole**](UserRoleApi.md#createRole) | **POST** /roles | Create role
[**createUser**](UserRoleApi.md#createUser) | **POST** /users | Create user
[**deleteRole**](UserRoleApi.md#deleteRole) | **DELETE** /roles/{id} | Delete role with Id
[**deleteUser**](UserRoleApi.md#deleteUser) | **DELETE** /users/{id} | Delete user with Id
[**getRoleById**](UserRoleApi.md#getRoleById) | **GET** /roles/{id} | Get role by Id
[**getRoles**](UserRoleApi.md#getRoles) | **GET** /roles | Get roles
[**getUserById**](UserRoleApi.md#getUserById) | **GET** /users/{id} | Get user by Id
[**getUsers**](UserRoleApi.md#getUsers) | **GET** /users | Get users


<a name="createRole"></a>
# **createRole**
> createRole(role)

Create role

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
Role role = new Role(); // Role | 
try {
    apiInstance.createRole(role);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#createRole");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **role** | [**Role**](Role.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="createUser"></a>
# **createUser**
> createUser(user)

Create user

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
User user = new User(); // User | 
try {
    apiInstance.createUser(user);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#createUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user** | [**User**](User.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="deleteRole"></a>
# **deleteRole**
> deleteRole(id)

Delete role with Id

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
Long id = 789L; // Long | 
try {
    apiInstance.deleteRole(id);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#deleteRole");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="deleteUser"></a>
# **deleteUser**
> deleteUser(id)

Delete user with Id

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
Long id = 789L; // Long | 
try {
    apiInstance.deleteUser(id);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#deleteUser");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="getRoleById"></a>
# **getRoleById**
> Role getRoleById(id)

Get role by Id

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
Long id = 789L; // Long | 
try {
    Role result = apiInstance.getRoleById(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#getRoleById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

[**Role**](Role.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getRoles"></a>
# **getRoles**
> List&lt;Role&gt; getRoles(query)

Get roles

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
RoleQuery query = new RoleQuery(); // RoleQuery | Parameters of the role
try {
    List<Role> result = apiInstance.getRoles(query);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#getRoles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **query** | [**RoleQuery**](RoleQuery.md)| Parameters of the role | [optional]

### Return type

[**List&lt;Role&gt;**](Role.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getUserById"></a>
# **getUserById**
> User getUserById(id)

Get user by Id

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
Long id = 789L; // Long | 
try {
    User result = apiInstance.getUserById(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#getUserById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |

### Return type

[**User**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getUsers"></a>
# **getUsers**
> List&lt;User&gt; getUsers(query)

Get users

### Example
```java
// Import classes:
//import hska.microServiceWebShop.ApiException;
//import hska.microServiceWebShop.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
UserQuery query = new UserQuery(); // UserQuery | Parameters of the user
try {
    List<User> result = apiInstance.getUsers(query);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#getUsers");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **query** | [**UserQuery**](UserQuery.md)| Parameters of the user | [optional]

### Return type

[**List&lt;User&gt;**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

