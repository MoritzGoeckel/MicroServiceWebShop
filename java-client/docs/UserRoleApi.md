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
[**setRoleById**](UserRoleApi.md#setRoleById) | **PUT** /roles/{id} | Set role by Id
[**setUserById**](UserRoleApi.md#setUserById) | **PUT** /users/{id} | Set user by Id


<a name="createRole"></a>
# **createRole**
> createRole(role)

Create role

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


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
> List&lt;Role&gt; getRoles(typ, level)

Get roles

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
String typ = "typ_example"; // String | 
Integer level = 56; // Integer | 
try {
    List<Role> result = apiInstance.getRoles(typ, level);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#getRoles");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **typ** | **String**|  | [optional]
 **level** | **Integer**|  | [optional]

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
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


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
> List&lt;User&gt; getUsers(username, firstname, lastname, roleId)

Get users

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
String username = "username_example"; // String | 
String firstname = "firstname_example"; // String | 
String lastname = "lastname_example"; // String | 
Long roleId = 789L; // Long | 
try {
    List<User> result = apiInstance.getUsers(username, firstname, lastname, roleId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#getUsers");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **username** | **String**|  | [optional]
 **firstname** | **String**|  | [optional]
 **lastname** | **String**|  | [optional]
 **roleId** | **Long**|  | [optional]

### Return type

[**List&lt;User&gt;**](User.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="setRoleById"></a>
# **setRoleById**
> setRoleById(id, role)

Set role by Id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
Long id = 789L; // Long | 
Role role = new Role(); // Role | 
try {
    apiInstance.setRoleById(id, role);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#setRoleById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |
 **role** | [**Role**](Role.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="setUserById"></a>
# **setUserById**
> setUserById(id, user)

Set user by Id

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UserRoleApi;


UserRoleApi apiInstance = new UserRoleApi();
Long id = 789L; // Long | 
User user = new User(); // User | 
try {
    apiInstance.setUserById(id, user);
} catch (ApiException e) {
    System.err.println("Exception when calling UserRoleApi#setUserById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**|  |
 **user** | [**User**](User.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

