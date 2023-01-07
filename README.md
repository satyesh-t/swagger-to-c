# swagger-to-c
Swagger/ Open API doc to C structures and validation functions

This project aims to read the swagger yaml/json and create/generate C header file with structure of the pojo
and generate validation functions .
In future C code to convert the structure to json will also be generate:

Example the petstore yaml that is used as an example on https://editor.swagger.io Produces below output:

```
#ifndef _PET_STORE_H

#define #ifndef _PET_STORE_H


	#ifndef _COMMON_ENUM
		#define _COMMON_ENUM
		typedef char * str;
		typedef enum boolean {NO,YES} bool;
	#endif

typedef struct order_st {
	// eg: 10
	long  id;
	// eg: 198772
	long  petId;
	// eg: 7
	int  quantity;
	str  shipDate;
	// eg: approved
	str  status;
	bool complete;
}order_t ;

typedef struct customer_st {
	// eg: 100000
	long  id;
	// eg: fehguy
	str  username;
	address_t  * address;
	// size of array address;
	int addressSize;
}customer_t ;

typedef struct address_st {
	// eg: 437 Lytton
	str  street;
	// eg: Palo Alto
	str  city;
	// eg: CA
	str  state;
	// eg: 94301
	str  zip;
}address_t ;

typedef struct category_st {
	// eg: 1
	long  id;
	// eg: Dogs
	str  name;
}category_t ;

typedef struct user_st {
	// eg: 10
	long  id;
	// eg: theUser
	str  username;
	// eg: John
	str  firstName;
	// eg: James
	str  lastName;
	// eg: john@email.com
	str  email;
	// eg: 12345
	str  password;
	// eg: 12345
	str  phone;
	// eg: 1
	int  userStatus;
}user_t ;

typedef struct tag_st {
	long  id;
	str  name;
}tag_t ;

typedef struct pet_st {
	// eg: 10
	long  id;
	// eg: doggie
	str  name;
	category_t  category;
	str  * photoUrls;
	// size of array photoUrls;
	int photoUrlsSize;
	tag_t  * tags;
	// size of array tags;
	int tagsSize;
	str  status;
}pet_t ;

typedef struct apiresponse_st {
	int  code;
	str  type;
	str  message;
}apiresponse_t ;

#endif
````
