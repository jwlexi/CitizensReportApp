const express = require('express');
const app = express();
const mongoClient = require('mongodb').MongoClient

const url = "mongodb+srv://lexi:lexi2021@cluster0.1dnvl.mongodb.net/myFirstDatabase?retryWrites=true&w=majority"

app.use(express.json());


const mongoose = require('mongoose');

const blogSchema = new mongoose.Schema({
    title: {
        type: String
    },
    text: {
        type: String
    }
})

const Blog = mongoose.model('Blog', blogSchema);

module.exports = Blog;


mongoClient.connect(url, (err, db) => {
    if(err) {
        console.log("Error while connecting to mongo client")
    }
    else {
        const myDb = db.db('myDb')
        const usersCollection = myDb.collection('users')
        const postsCollection = myDb.collection('posts')

        app.post('/register', (req, res) => {
            const newUser = {
                username: req.body.username,
                password: req.body.password
            }
            const query = {username: newUser.username}
            usersCollection.findOne(query, (err, result) => {
                if(result == null) {
                    usersCollection.insertOne(newUser, (err, result) => {
                        res.status(200).send();
                    })
                }
                else {
                    res.status(400).send();
                }
            })
        })

        app.post('/login', (req, res) => {
            const query = {
                username: req.body.username,
                password: req.body.password
            }

            usersCollection.findOne(query, (err, result) => {
                if (result != null) {
                    const finalobj = {
                        username: result.username
                    }
                    res.status(200).send(JSON.stringify(finalobj));
                }
                else {
                    res.status(404).send();
                }
            })
        })
        app.post('/create', (req, res) => {
            const newPost = {
                title: req.body.title,
                text: req.body.text
            }
            const postQuery = {title: newPost.title}
            postsCollection.findOne(postQuery, (err, result) => {
                if(result == null) {
                    postsCollection.insertOne(newPost, (err, result) => {
                        res.status(200).send();
                    })
                }
                else {
                    res.status(400).send();
                }
            })
        })
        // app.get('/retrievePostData', async (req, res) => {
        //     const query = {
        //         title: req.body.title,
        //         text: req.body.text
        //     }
        //     // for (x of postsCollection) {
        //     //     const finalObj = {
        //     //         title: x.title,
        //     //         text: x.text
        //     //     }
        //     //     res.status(200).send(JSON.stringify(finalobj));
        //     // }
        //     let reportsSql = "select * from postsCollection";
        //     let rows = await executeSQL(reportsSql);
        //     res.status(200).send(JSON.stringify(finalobj));

        // })

        app.get('/retrievePostData', async function(req, res){
            myDb.collection("posts").find({}).toArray(function(err, result) {
                if (err) throw err;
                res.status(200).send(result)
          });

        });

        app.get('/retrieveUserData', async function(req, res){
            myDb.collection("users").find({}).toArray(function(err, result) {
                if (err) throw err;
                res.status(200).send(result)
          });

        });


        async function executeSQL(sql, params) {
          return new Promise (function (resolve, reject) {
            pool.query(sql, params, function (err, rows, fields) {
            if (err) throw err;
              resolve(rows);
            });
          });
}
    }
});

app.listen(3000, () => {
    console.log('listening on port 3000')
})