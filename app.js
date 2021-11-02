const express = require('express');
const app = express();
const mongoClient = require('mongodb').mongoClient

const url = "mongodb://localhost:27017"

app.use(express.json());


app.listen(3000, () => {
    console.log('listening on port 3000')
})