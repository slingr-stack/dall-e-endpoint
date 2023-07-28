---
title: DALL-E Endpoint
keywords: 
last_updated: June 6, 2023
tags: []
summary: "Detailed description of the API of the DALL-E endpoint."
---

## Overview

The DALL-E endpoint has the following features:
 
- Authorization with API Token

Please make sure you take a look at the documentation from OpenAI as features are based on its API:

- [API Reference](https://platform.openai.com/docs/introduction)

## Quick start

Once you configured the endpoint, you can list current models with this call:

```js
for(var model of app.endpoints.dalle.models.get().data)
{ 
    log(JSON.stringify(model))
}
```

Or you can generate a completion with this call:

```js
var res = app.endpoitns.dalle.images.generations.post({
    prompt: "Once upon a time",
    n: 4,
    size: "1024x1024",
    response_format: "url"
})
```

## Configuration

Before configuring the endpoint you will need to create an account in OpenAI:
[OpenAI login](https://platform.openai.com/signup?launch)

Once you have your account you will be able to configure the endpoint.

### API Token

The API token of the account. This field is required. [API Token list](https://platform.openai.com/account/api-keys)

### Organization ID

The organization ID of the account. This field is optional. [Organization ID](https://platform.openai.com/account/org-settings)

# Javascript API

The Javascript API of the dalle endpoint has three pieces:

- **HTTP requests**: These allow to make regular HTTP requests.
- **Shortcuts**: These are helpers to make HTTP request to the API in a more convenient way.
- **Additional Helpers**: These helpers provide additional features that facilitate or improves the endpoint usage in SLINGR.

## HTTP requests
You can make `GET`,`POST` requests to the [dalle API](https://platform.openai.com/docs/api-reference/images) like this:
```javascript
var response = app.endpoints.dalle.get('/v1/models/:model')
var response = app.endpoints.dalle.post('/v1/moderations', body)
var response = app.endpoints.dalle.post('/v1/moderations')
```

Please take a look at the documentation of the [HTTP endpoint](https://github.com/slingr-stack/http-endpoint#javascript-api)
for more information about generic requests.

## Shortcuts

Instead of having to use the generic HTTP methods, you can (and should) make use of the helpers provided in the endpoint:
<details>
    <summary>Click here to see all the helpers</summary>

<br>

* API URL: '/v1/models'
* HTTP Method: 'GET'
* More info: https://platform.openai.com/docs/api-reference
```javascript
app.endpoints.dalle.models.get()
```
---
* API URL: '/v1/models/:model'
* HTTP Method: 'GET'
* More info: https://platform.openai.com/docs/api-reference
```javascript
app.endpoints.dalle.models.get()
```
---
* API URL: '/v1/images/generations'
* HTTP Method: 'POST'
* More info: https://platform.openai.com/docs/api-reference
```javascript
app.endpoints.dalle.images.generations.post(body, callbackData, callbacks)
```
---
* API URL: '/v1/images/edits'
* HTTP Method: 'POST'
* More info: https://platform.openai.com/docs/api-reference
```javascript
app.endpoints.dalle.images.edits.post(body, callbackData, callbacks)
```
---
* API URL: '/v1/images/variations'
* HTTP Method: 'POST'
* More info: https://platform.openai.com/docs/api-reference
```javascript
app.endpoints.dalle.images.variations.post(body, callbackData, callbacks)
```
---
* API URL: '/v1/moderations'
* HTTP Method: 'POST'
* More info: https://platform.openai.com/docs/api-reference
```javascript
app.endpoints.dalle.moderations.post(body)
```
---

</details>

## Flow Step

As an alternative option to using scripts, you can make use of Flows and Flow Steps specifically created for the endpoint:
<details>
    <summary>Click here to see the Flow Steps</summary>

<br>



### Generic Flow Step

Generic flow step for full use of the entire endpoint and its services.

<h3>Inputs</h3>

<table>
    <thead>
    <tr>
        <th>Label</th>
        <th>Type</th>
        <th>Required</th>
        <th>Default</th>
        <th>Visibility</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>URL (Method)</td>
        <td>choice</td>
        <td>yes</td>
        <td> - </td>
        <td>Always</td>
        <td>
            This is the http method to be used against the endpoint. <br>
            Possible values are: <br>
            <i><strong>GET,POST</strong></i>
        </td>
    </tr>
    <tr>
        <td>URL (Path)</td>
        <td>choice</td>
        <td>yes</td>
        <td> - </td>
        <td>Always</td>
        <td>
            The url to which this endpoint will send the request. This is the exact service to which the http request will be made. <br>
            Possible values are: <br>
            <i><strong>/v1/models<br>/v1/models/{model}<br>/v1/images/generations<br>/v1/images/edits<br>/v1/images/variations<br>/v1/moderations<br></strong></i>
        </td>
    </tr>
    <tr>
        <td>Headers</td>
        <td>keyValue</td>
        <td>no</td>
        <td> - </td>
        <td>Always</td>
        <td>
            Used when you want to have a custom http header for the request.
        </td>
    </tr>
    <tr>
        <td>Query Params</td>
        <td>keyValue</td>
        <td>no</td>
        <td> - </td>
        <td>Always</td>
        <td>
            Used when you want to have a custom query params for the http call.
        </td>
    </tr>
    <tr>
        <td>Body</td>
        <td>json</td>
        <td>no</td>
        <td> - </td>
        <td>Always</td>
        <td>
            A payload of data can be sent to the server in the body of the request.
        </td>
    </tr>
    <tr>
        <td>Override Settings</td>
        <td>boolean</td>
        <td>no</td>
        <td> false </td>
        <td>Always</td>
        <td></td>
    </tr>
    <tr>
        <td>Follow Redirect</td>
        <td>boolean</td>
        <td>no</td>
        <td> false </td>
        <td> overrideSettings </td>
        <td>Indicates that the resource has to be downloaded into a file instead of returning it in the response.</td>
    </tr>
    <tr>
        <td>Download</td>
        <td>boolean</td>
        <td>no</td>
        <td> false </td>
        <td> overrideSettings </td>
        <td>If true the method won't return until the file has been downloaded, and it will return all the information of the file.</td>
    </tr>
    <tr>
        <td>File name</td>
        <td>text</td>
        <td>no</td>
        <td></td>
        <td> overrideSettings </td>
        <td>If provided, the file will be stored with this name. If empty the file name will be calculated from the URL.</td>
    </tr>
    <tr>
        <td>Full response</td>
        <td> boolean </td>
        <td>no</td>
        <td> false </td>
        <td> overrideSettings </td>
        <td>Include extended information about response</td>
    </tr>
    <tr>
        <td>Connection Timeout</td>
        <td> number </td>
        <td>no</td>
        <td> 5000 </td>
        <td> overrideSettings </td>
        <td>Connect timeout interval, in milliseconds (0 = infinity).</td>
    </tr>
    <tr>
        <td>Read Timeout</td>
        <td> number </td>
        <td>no</td>
        <td> 60000 </td>
        <td> overrideSettings </td>
        <td>Read timeout interval, in milliseconds (0 = infinity).</td>
    </tr>
    </tbody>
</table>

<h3>Outputs</h3>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>response</td>
        <td>object</td>
        <td>
            Object resulting from the response to the endpoint call.
        </td>
    </tr>
    </tbody>
</table>


</details>

For more information about how shortcuts or flow steps works, and how they are generated, take a look at the [slingr-helpgen tool](https://github.com/slingr-stack/slingr-helpgen).

## Additional Flow Step


<details>
    <summary>Click here to see the Customs Flow Steps</summary>

<br>



### Create an Image

This flow step send a prompt to the service to create an image based on the provided parameters.

<h3>Inputs</h3>

<table>
    <thead>
    <tr>
        <th>Label</th>
        <th>Type</th>
        <th>Required</th>
        <th>Default</th>
        <th>Visibility</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Prompt</td>
        <td>text</td>
        <td>no</td>
        <td> - </td>
        <td>Always</td>
        <td>
            The prompt to generate the image.
        </td>
    </tr>
    <tr>
        <td>Url</td>
        <td>boolean</td>
        <td>no</td>
        <td> true </td>
        <td>Always</td>
        <td>
            The posible return of the service.
        </td>
    </tr>
    <tr>
        <td>Size</td>
        <td>choice</td>
        <td>no</td>
        <td> 256x256 </td>
        <td>Always</td>
        <td>
            The size of the resulting image. <br> 256x256 is the default value. <br> 512x512 <br> 1024x1024 is the maximum value.
        </td>
    </tr>
    <tr>
        <td>N</td>
        <td>number</td>
        <td>no</td>
        <td> 1 </td>
        <td>Always</td>
        <td>The number of posible generations.</td>
    </tr>
    <tr>
        <td>Follow Redirect</td>
        <td>boolean</td>
        <td>no</td>
        <td> false </td>
        <td> overrideSettings </td>
        <td>Indicates that the resource has to be downloaded into a file instead of returning it in the response.</td>
    </tr>
    <tr>
        <td>Connection Timeout</td>
        <td> number </td>
        <td>no</td>
        <td> 5000 </td>
        <td> overrideSettings </td>
        <td>Connect timeout interval, in milliseconds (0 = infinity).</td>
    </tr>
    <tr>
        <td>Read Timeout</td>
        <td> number </td>
        <td>no</td>
        <td> 60000 </td>
        <td> overrideSettings </td>
        <td>Read timeout interval, in milliseconds (0 = infinity).</td>
    </tr>
    </tbody>
</table>

<h3>Outputs</h3>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>response</td>
        <td>object</td>
        <td>
            Object resulting from the response to the endpoint call.
        </td>
    </tr>
    </tbody>
</table>


</details>

## Additional Helpers
*MANUALLY ADD THE DOCUMENTATION OF THESE HELPERS HERE...*


## About SLINGR

SLINGR is a low-code rapid application development platform that accelerates development, with robust architecture for integrations and executing custom workflows and automation.

[More info about SLINGR](https://slingr.io)

## License

This endpoint is licensed under the Apache License 2.0. See the `LICENSE` file for more details.
