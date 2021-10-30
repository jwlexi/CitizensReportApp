# CitizensReportApp

Alexis Sanchez: https://github.com/jwlexi <br />
Andrew Mei: https://github.com/AndrewMei99 <br />
Jeremy Meharg: https://github.com/JeremyMeh <br />
Nayeli De Jesus: https://github.com/nayelimdejesus <br />

<strong> Presentation: </strong> https://docs.google.com/presentation/d/1gTKdX_f3LIyOEOvmpdMm1UVtcqbnnPuZGOZLRVZV5G4/edit#slide=id.p

<h1> Title: Citizens’ Report </h1>
<h2>
Problem Statement: Citizens will be able to report problems throughout their community.
  
Description: The Citizens Report application would allow users to make posts, see posts, view posts destination, and edit posts.
  
The problem: Citizens throughout a community often lack an ability to communicate with each other effectively. This could cause problems throughout the community such as safety hazards, violence, and a higher crime rate. People need a good way to communicate with each other throughout their city. They also need to be able to pinpoint the exact location of a problem that is being caused around the city.
  
The solution: Citizen's report app will allow users to create posts to notify others of issues who also use the app to keep others updated.
</h2>
<h3>
Tech stack:
<br>
Frontend - Android
<br>
Persistence/Database - MongoDB
<br>
Backend - Node.js
<br>
<br>
Mockup:
<img src = "https://user-images.githubusercontent.com/44917258/139520512-8936cc20-375a-4c58-b4ff-35c505713704.png"></img>
<br>
Tenative API endpoints:
<br>
Log out of account
POST/GET [url]/logout?username={username}
<br>
Delete account
DELETE [url]/logout?username={username}
<br>
View all users
GET: [URL]/users
<br>
Create users
PUT: [URL]/users?username={username}&{...}
<br>
Delete users
DELETE: [URL]/users?username={username}
<br>
Update users
PATCH: [URL]/users?username={username}&{...}
<br>
Add new post
POST [URL]/userpost?post_name={post name}&url={url}
<br>
Remove card
DELETE [URL]/userpost?post_name={post name}
<br>
Update posts
PATCH [URL]/userpost?post_name={post name}
<br>
Admin Login
POST: [url]/login?username={username}&password={password}
<br>
View all users
GET: [URL]/users
<br>
Create Users
PUT: [URL]/users?username={username}&{...}
<br>
Delete users
DELETE: [URL]/users?username={username}
Must ask for confirmation
<br>
Update users
PATCH: [URL]/users?username={username}&{...}

</h3>
