const express = require("express")
const app = express()

app.use(express.json())

const port = process.env.PORT || 3000

// 3.0 zwróci wartość binarną czy podana na wejściu liczba jest liczbą
// pierwszą; wykorzysta Promise
function testPrime(number) {
    return new Promise((resolve, reject) => {
        if (typeof number !== 'number' || isNaN(number)) {
            reject("Provided value is not a number")
            return
        }

        if (number < 2) {
            resolve(false)
            return
        }

        if (number === 2) {
            resolve(true)
            return
        }

        for(let i = 2; i <= Math.sqrt(number); i++) {
            if (number % i === 0) {
                resolve(false)
                return
            }
        }

        resolve(true)
    })
}

app.post('/isPrime', (req, res) => {
    const number = req.body.number;

    testPrime(number)
        .then(isPrime => {
            res.json({
                number: number,
                isPrime: isPrime
            })
        })
        .catch(error => {
            res.status(400).json({
                error: error
            })
        })
})


app.listen(port, ()=> {
    console.log("Server is running on port " + port)
})
