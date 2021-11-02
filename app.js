const express = require('express');
const app = express();
const mongoClient = require('mongodb').MongoClient

const url = "mongodb://localhost:27017"

app.use(express.json());

mongoClient.connect(url, (err, db) => {
    if(err) {
        console.log("Error while connecting to mongo client")
    }
    else {
        const myDb = db.db('myDb')
        const usersCollection = myDb.collection('users')

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
            console.log("This is working");
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
    }
});

app.listen(3000, () => {
    console.log('listening on port 3000')
})