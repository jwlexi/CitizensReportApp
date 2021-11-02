const express = require('express');
const app = express();
const mongoClient = require('mongodb').mongoClient

const url = "mongodb://localhost:27017"

app.use(express.json());

mongoClient.connect(url, (err, db) => {
    if(err) {
        console.log("Error while connecting to mongo client")
    }
    else {
        const myDb = db.db('myDb')
        const collection = myDb.collection('tableC')

        app.post('/register', (req, res) => {
            const newUser = {
                username: req.body.username,
                password: req.body.password
            }
            const query = {username: newUser.username}
            collection.findOne(query, (err, result) => {
                if(result == null) {
                    collection.insertOne(newUser, (err, result) => {
                        res.status(200).send();
                    })
                }
                else {
                    res.status(400).send();
                }
            })
        })

        app.post('/login', (req, res) => {
            const query = {username: req.body.username,
            password: req.body.password}

            collection.findOne(query, (err, result) => {
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