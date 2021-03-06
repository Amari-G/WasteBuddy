# WasteBuddy

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

# Overview
## Description
WasteBuddy is a tool design to help people learn how to recycle and compost. Users can scan the barcode on an item to search for an item, or search by typing the item name. However, upcycling is even better than recycling, thus WasteBuddy provides a platform for users to upload and share their own DIY projects with the community.

## App Evaluation
- **Category:** Lifestyle/Reference
- **Mobile:** How uniquely mobile is the product experience?
    - WasteBuddy will allow user's to scan barcodes on items using their phone camera, then tell them how to dispose of the product and how the product can be reused. Additionally, users that have created an account, can post images of their DIY projects using their phone camera. The app will also be able to use the user's location to determine best practice for waste disposal, as policies can vary based on location.

- **Story:** How compelling is the story around this app once completed?

    - As more people become aware of the impacts that humans have on the environment, more people gain a sense of responsibility to help mitigate these effects. WasteBuddy is designed to help empower these people and provide a platform for them to share their ideas. Whether you just need to know where to throw something away or you want a platform to share your DIY projects with the world, WasteBuddy is here to help.
    
- **Market:** Market: How large or unique is the market for this app?

    - This application has the potential to scale to billions of users, because all humans produce waste. As the world continues to develop, and more societies begin to acknowledge the importance of effective waste management, people will need an easy-to-use tool to help them adjust to change.
    - There are two groups of users that will gain value from the app. The larger group is the set of users that just need to sort their waste. The niche group includes users who enjoy DIY projects.
    - This app also has the potential to appeal to companies that want to pay for product placement.

- **Habit:** How habit-forming or addictive is this app?

    - **[Average]** Users that are only interested in getting help with sorting waste may open the app as little as once a day. This depends greatly on how educated the user is before downloading the app. No matter their prior knowledge, these users are likely to use the app less over time as they start to remember how to sort things.
    - **[Niche]** Users that are interested in DIY may open the app as little as once a day for sorting trash, but will be more likely to stay on the app as they begin to browse DIY projects. Ideally, these users will be converted into creators when they decide to share their own DIY projects on the platform


- **Scope:** How well-formed is the scope for this app?
    - It would be very challenging to implement all of the features of this app, however a stripped down version of the app is still interesting to build and still valuable to users. The product that I want to build is crystal clear.



# Product Spec

## 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User can register
* User can search for items
* User can view item details
* User can scan barcode to search
* User can browse DIY projects
* User can view other users' profiles

* User (registered) can add item to database
* User (registered) can post DIY project
* User (registered) can follow another user

**Optional Nice-to-have Stories**

* User can view DIY projects that use an item
* User profile shows their DIY projects
* User (registered) can like DIY project
* User (registered)can link items that were used in their DIY projects
* Provide item information based on user's locale

## 2. Screen Archetypes

* Login Screen
	* User can login/register
	* User can continue as guest
* Home
    * User can browse DIY projects
    * User can view other users' profiles

* Search
	* User can search for items
    * User can scan barcode to search

* Item
	* User can view item details

* Project
	* User can view DIY project details

* Creation
	* User can post a new DIY project to their feed

* User
	* User can follow another user
	* 
## 3. Navigation

**Tab Navigation** (Tab to Screen)

* Home Feed
* Search

**Flow Navigation** (Screen to Screen)

* Login Screen
	=> Home

* Home
	=> Search
	=> Project
	=> User
	
* Search
	=> Item
	=> Future version should allow for searching of projects

* Item
	=> None, but future version will allow user to see projects that use this item and navigate to those projects
	
* Creation
	=> Home

* User
	=> Project

# Wireframes

<img src="https://github.com/Amari-G/WasteBuddy/blob/master/WasteBuddy%20Wireframe.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 

### Models

#### User

| Property           | Type           | Description                                       |
|:------------------ |:-------------- |:------------------------------------------------- |
| objectId           | String         | unique id for the user (default field)            |
| email              | String         | email user has set for account                    |
| name               | String         | display name of the user                          |
| reputation         | Number         | total reputation points of the user               |
| following          | Array\<String> | list of pointers to users they are following      |
| followers          | Array\<String> | list of pointers to users that are following them |
| createdProjects    | Array\<String> | list of pointers to Projects they have created    |
| bookmarkedProjects | Array\<String> | list of pointers to Projects they have bookmarked |
| createdAt          | DateTime       | date when user is created (default field)         |
| updatedAt          | DateTime       | date when user is last updated (default field)    |

---

#### Item

| Property    | Type            | Description                                   |
| ----------- |:--------------- |:--------------------------------------------- |
| objectId    | String          | unique id for the item                        |
| barcodeId   | String          | unique barcode id for the item                |
| name        | String          | display name of the item                      |
| disposal    | String          | disposal method for this item                 |
| description | String          | description of the item                       |
| imageUrl    | String          | url for the image of the item                 |
| author      | Pointer to User | objectId of user that submitted this item     |
| createdAt   | DateTime        | date when user is created(default field)      |
| updatedAt   | DateTime        | date when user is last updated(default field) |

---

#### Project

| Property     | Type            | Description                                             |
| ------------ |:--------------- |:------------------------------------------------------- |
| objectId     | String          | unique id for the project                               |
| name         | String          | display name of the project                             |
| description  | String          | description of the project                              |
| imageUrl     | String          | url for the image of the project                        |
| videoUrl     | String          | url for the video tutorial of the project               |
| likesCount   | Number          | number of likes for the project                         |
| difficulty   | Number          | difficulty rating of the project                        |
| projectSteps | Array\<String>  | array of Strings representing each step in project      |
| itemsUsed    | Array\<String>  | array of pointers to Item that are used in this project |
| author       | Pointer to User | pointer to User that submitted this project             |
| createdAt    | DateTime        | date when user is created (default field)               |
| updatedAt    | DateTime        | date when user is last updated (default field)          |



### Networking

#### List of network requests by screen
- Home Feed Screen
	- (Read/GET) Query top items
	- (Read/GET) Query top projects
- Create Item Screen
	- (Create/POST) Create a new item object

- Create Project Screen
	- (Create/POST) Create a new project object
	- (Read/GET) Query items for autocomplete

- Search Screen
	- (Read/GET) Query commonly searched items

- Results Screen
	- (Read/GET) Query items based on user input

- Scanner Screen
	- (Read/GET) Query item where barcodeId matches input

- Item Screen
	- (Read/GET) Query the item from the database
	- (Read/GET) Query projects that use this item

- Project Screen
	- (Read/GET) Query the project from the database
	- (Update/PUT) Update number of likes
	- (Update/PUT) Update user's saved projects
	- (Update/PUT) Update user profile name

- User Screen
	- (Read/GET) Query logged in user object
	- (Read/GET) Query projects created by logged in user
	- (Update/PUT) Update user profile name
	- (Update/PUT) Update user profile image


- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
